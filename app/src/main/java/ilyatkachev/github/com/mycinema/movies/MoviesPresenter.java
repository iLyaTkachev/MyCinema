package ilyatkachev.github.com.mycinema.movies;

import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetMovies;

public class MoviesPresenter implements IMoviesContract.Presenter {

    private final IMoviesContract.View mView;
    private final GetMovies mGetMovies;

    public MoviesPresenter(IMoviesContract.View pView, GetMovies pGetMovies) {
        mView = pView;
        mGetMovies = pGetMovies;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadMovies(boolean forceUpdate) {

    }
}
