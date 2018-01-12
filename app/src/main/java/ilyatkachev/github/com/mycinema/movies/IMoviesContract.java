package ilyatkachev.github.com.mycinema.movies;

import java.util.List;

import ilyatkachev.github.com.mycinema.IBasePresenter;
import ilyatkachev.github.com.mycinema.IBaseView;
import ilyatkachev.github.com.mycinema.data.remote.gson.IBaseCinemaObject;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

/**
 * This interface specifies contract between View and Presenter.
 */
public interface IMoviesContract {

    interface View<T extends IBaseCinemaObject> extends IBaseView<Presenter> {

        void showMovies(List<T> movies);

        void showLoadingError();

        boolean isActive();
    }

    interface Presenter<T extends IBaseCinemaObject> extends IBasePresenter {

        void loadMovies(boolean forceUpdate);

        List<T> getMovieList();
    }
}
