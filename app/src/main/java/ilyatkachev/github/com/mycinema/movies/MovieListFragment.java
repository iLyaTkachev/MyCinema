package ilyatkachev.github.com.mycinema.movies;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.movieDetails.MovieDetailsActivity;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.Constants;
import ilyatkachev.github.com.mycinema.util.GridSpacingItemDecoration;
import ilyatkachev.github.com.mycinema.util.RVOnScrollListener;

public class MovieListFragment extends Fragment implements IMoviesContract.View<Movie> {

    private IMoviesContract.Presenter<Movie> mPresenter;

    private RecyclerView mMoviesRecyclerView;
    private MovieAdapter mMovieAdapter;
    private SmoothProgressBar mSmoothProgressBar;

    private RVOnScrollListener mOnScrollListener;

    public static final int VISIBLE_THRESHOLD = 5;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.movies_frag, container, false);

        mSmoothProgressBar = view.findViewById(R.id.smooth_progress_bar);
        mMoviesRecyclerView = view.findViewById(R.id.loaded_movies_recycler_view);
        mMovieAdapter = new MovieAdapter(this.getContext(), mPresenter.getMovieList(), mMovieCardListener);
        mMoviesRecyclerView.setAdapter(mMovieAdapter);
        setupLayoutManager(mMoviesRecyclerView);
        setupScrollListener(mMoviesRecyclerView);

        mPresenter.start();

        setRetainInstance(true);

        return view;
    }

    private void setupLayoutManager(@NonNull final RecyclerView pRecyclerView) {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            pRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            pRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, GridSpacingItemDecoration.dpToPx(10, getContext()), true));
        } else {
            pRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
            pRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4, GridSpacingItemDecoration.dpToPx(10, getContext()), true));
        }
    }

    private void setupScrollListener(@NonNull final RecyclerView pRecyclerView) {
        if (mOnScrollListener == null) {
            mOnScrollListener = new RVOnScrollListener(VISIBLE_THRESHOLD, new RVOnScrollListener.IScrolled() {

                @Override
                public void loadData() {
                    mPresenter.loadMovies(false);
                    mSmoothProgressBar.progressiveStart();
                }
            });
        }
        mMoviesRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    public void setPresenter(@NonNull final IMoviesContract.Presenter pPresenter) {
        //mPresenter = NotNull.check(pPresenter);
        mPresenter = pPresenter;
    }

    @Override
    public void showMovies(final List<Movie> pMovies) {
        mMovieAdapter.notifyDataSetChanged();
        mOnScrollListener.setLoading(false);
        mSmoothProgressBar.progressiveStop();
    }

    @Override
    public void showFavoriteMovies(final List<Movie> movies) {
        Toast.makeText(getContext(), "Favorite = " + movies.get(0).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingError() {
        Toast.makeText(getContext(), "Loading Error", Toast.LENGTH_SHORT).show();
        mSmoothProgressBar.progressiveStop();
        mOnScrollListener.setLoading(false);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        mOnScrollListener.setLoading(active);

    }

    @Override
    public boolean isActive() {
        return false;
    }

    private IMovieCardListener mMovieCardListener = new IMovieCardListener() {

        @Override
        public void onCardClick(final BaseMediaObject pClickedMovie) {
            Toast.makeText(getContext(), "Card clicked", Toast.LENGTH_SHORT).show();
            final Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra(Constants.MOVIE_OBJECT, pClickedMovie);
            startActivity(intent);
        }

        @Override
        public void onCardOverflowClick(final BaseMediaObject pClickedMovie, final View pView) {
            showCardPopupMenu(pClickedMovie, pView);
        }
    };

    private void showCardPopupMenu(final BaseMediaObject pClickedMovie, final View pView) {
        final PopupMenu popup = new PopupMenu(getContext(), pView);
        popup.getMenuInflater().inflate(R.menu.menu_movie_card, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(final MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_like:
                        Toast.makeText(getContext(), "Like item clicked name=" + pClickedMovie.getTitle(), Toast.LENGTH_SHORT).show();
                        mPresenter.addToFavorite((Movie) pClickedMovie);
                        break;
                    case R.id.action_add:
                        mPresenter.getFavoriteList();
                        break;
                }

                return true;
            }
        });
        popup.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMoviesRecyclerView.clearOnScrollListeners();
    }
}
