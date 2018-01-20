package ilyatkachev.github.com.mycinema.movies;

import java.util.List;

import ilyatkachev.github.com.mycinema.IBasePresenter;
import ilyatkachev.github.com.mycinema.IBaseView;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;

/**
 * This interface specifies contract between View and Presenter.
 */
public interface IMoviesContract {

    interface View<T extends BaseMediaObject> extends IBaseView<Presenter> {

        void showMovies(List<T> movies);

        void showFavoriteMovies(List<T> movies);

        void showLoadingError();

        void setLoadingIndicator(boolean active);

        boolean isActive();
    }

    interface Presenter<T extends BaseMediaObject> extends IBasePresenter {

        void loadMovies(boolean forceUpdate);

        List<T> getMovieList();

        void addToFavorite(T object);

        void getFavoriteList();
    }
}
