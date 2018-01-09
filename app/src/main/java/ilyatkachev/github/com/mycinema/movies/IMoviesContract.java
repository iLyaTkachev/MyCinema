package ilyatkachev.github.com.mycinema.movies;

import java.util.List;

import ilyatkachev.github.com.mycinema.IBasePresenter;
import ilyatkachev.github.com.mycinema.IBaseView;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;

/**
 * This interface specifies contract between View and Presenter.
 */
public interface IMoviesContract {

    interface View extends IBaseView<Presenter>{

        void showMovies(List<IMovie> movies);
    }

    interface Presenter extends IBasePresenter{

        void loadMovies(boolean forceUpdate);
    }
}
