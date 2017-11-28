package ilyatkachev.github.com.mycinema.movies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.util.NotNull;

public class MovieListFragment extends Fragment implements IMoviesContract.View {

    private IMoviesContract.Presenter mPresenter;

    private RecyclerView mMoviesRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_frag, container, false);
        mMoviesRecyclerView = (RecyclerView) view.findViewById(R.id.loaded_movies_recycler_view);
        mMovieAdapter = new MovieAdapter(this.getActivity(), MovieListFactory.createMovieList(100));
        mMoviesRecyclerView.setAdapter(mMovieAdapter);
        //mMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }

        return view;
    }

    @Override
    public void setPresenter(@NonNull IMoviesContract.Presenter pPresenter) {
        mPresenter = NotNull.check(pPresenter);
    }

    @Override
    public void showMovies(List<IMovie> pMovies) {

    }
}
