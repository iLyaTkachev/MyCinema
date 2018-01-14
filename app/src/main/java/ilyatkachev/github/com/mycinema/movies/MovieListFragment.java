package ilyatkachev.github.com.mycinema.movies;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.data.remote.gson.IBaseCinemaObject;
import ilyatkachev.github.com.mycinema.movieDetails.MovieDetailsActivity;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.GridSpacingItemDecoration;
import ilyatkachev.github.com.mycinema.util.NotNull;

public class MovieListFragment extends Fragment implements IMoviesContract.View<Movie> {

    private IMoviesContract.Presenter<Movie> mPresenter;

    private RecyclerView mMoviesRecyclerView;
    private MovieAdapter mMovieAdapter;
    private SmoothProgressBar mSmoothProgressBar;

    private int previousTotal = 0;
    private final int visibleThreshold = 5;
    private boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_frag, container, false);

        mSmoothProgressBar = (SmoothProgressBar) view.findViewById(R.id.smooth_progress_bar);
        mMoviesRecyclerView = (RecyclerView) view.findViewById(R.id.loaded_movies_recycler_view);
        mMovieAdapter = new MovieAdapter(this.getContext(), mPresenter.getMovieList(), mMovieCardListener);
        mMoviesRecyclerView.setAdapter(mMovieAdapter);
        setupLayoutManager(mMoviesRecyclerView);
        setupScrollListener(mMoviesRecyclerView);

        mPresenter.start();

        setRetainInstance(true);

        return view;
    }

    private void setupLayoutManager(@NonNull RecyclerView pRecyclerView) {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            pRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            pRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, GridSpacingItemDecoration.dpToPx(10, getContext()), true));
        } else {
            pRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
            pRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4, GridSpacingItemDecoration.dpToPx(10, getContext()), true));
        }
    }

    private void setupScrollListener(@NonNull final RecyclerView pRecyclerView){
        mMoviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                GridLayoutManager gridLayoutManager = (GridLayoutManager)pRecyclerView.getLayoutManager();
                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

                if (isLoading) {
                    if (totalItemCount > previousTotal) {

                        previousTotal = totalItemCount;
                    }
                }
                if (!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    mPresenter.loadMovies(false);
                    isLoading = true;
                    mSmoothProgressBar.progressiveStart();
                }

            }
        });
    }

    @Override
    public void setPresenter(@NonNull IMoviesContract.Presenter pPresenter) {
        //mPresenter = NotNull.check(pPresenter);
        mPresenter = pPresenter;
    }

    @Override
    public void showMovies(List<Movie> pMovies) {
        mMovieAdapter.notifyDataSetChanged();
        isLoading = false;
        mSmoothProgressBar.progressiveStop();
    }

    @Override
    public void showLoadingError() {
        Toast.makeText(getContext(), "Loading Error", Toast.LENGTH_SHORT).show();
        mSmoothProgressBar.progressiveStop();
        isLoading = false;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        isLoading = active;

    }

    @Override
    public boolean isActive() {
        return false;
    }

    private IMovieCardListener mMovieCardListener = new IMovieCardListener() {

        @Override
        public void onCardClick(IMovie pClickedMovie) {
            Toast.makeText(getContext(), "Card clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            startActivity(intent);
        }

        @Override
        public void onCardOverflowClick(final IMovie pClickedMovie, final View pView) {
            showCardPopupMenu(pClickedMovie, pView);
        }
    };

    private void showCardPopupMenu(final IMovie pClickedMovie, final View pView) {
        PopupMenu popup = new PopupMenu(getContext(), pView);
        popup.getMenuInflater().inflate(R.menu.menu_movie_card, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_like:
                        Toast.makeText(getContext(), "Like item clicked name="+pClickedMovie.getTitle(), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_add:
                        Toast.makeText(getContext(), "Add item clicked", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
        popup.show();
    }

}
