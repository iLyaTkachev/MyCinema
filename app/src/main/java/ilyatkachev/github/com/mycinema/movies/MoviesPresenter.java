package ilyatkachev.github.com.mycinema.movies;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetMovies;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;
import ilyatkachev.github.com.mycinema.util.usecase.UseCaseHandler;

public class MoviesPresenter implements IMoviesContract.Presenter<Movie> {

    private final IMoviesContract.View mView;
    private final GetMovies mGetMovies;

    private final UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    private MoviesFilterType mMoviesFilterType;
    private int mCurrentPage;
    private List<Movie> mMovieList;

    public MoviesPresenter(@NonNull final IMoviesContract.View pView, @NonNull final GetMovies pGetMovies, @NonNull final UseCaseHandler pUseCaseHandler, final MoviesFilterType pMoviesFilterType) {
        mView = pView;
        mGetMovies = pGetMovies;
        mUseCaseHandler = pUseCaseHandler;
        mMoviesFilterType = pMoviesFilterType;

        mCurrentPage = 1;
        mMovieList = new ArrayList<>();

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadMovies(false);
    }

    @Override
    public void loadMovies(boolean forceUpdate) {
        if (forceUpdate){
            mCurrentPage = 1;
        }
        loadMovies(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadMovies(final boolean pForceUpdate, final boolean pShowLoadingUI) {
        if (pShowLoadingUI) {

        }
        final GetMovies.RequestValues requestValues = new GetMovies.RequestValues(mCurrentPage, mMoviesFilterType);

        mUseCaseHandler.execute(mGetMovies, requestValues, new UseCase.UseCaseCallback<GetMovies.ResponseValue>() {

            @Override
            public void onSuccess(GetMovies.ResponseValue pResponse) {
                mMovieList.addAll(pResponse.getMovies());
                mCurrentPage++;

                if (pShowLoadingUI) {

                }
                mView.showMovies(mMovieList);
            }

            @Override
            public void onError() {
                if (pShowLoadingUI) {

                }
                mView.showLoadingError();
            }
        });
    }

    public MoviesFilterType getMoviesFilterType() {
        return mMoviesFilterType;
    }

    @Override
    public List<Movie> getMovieList() {
        return mMovieList;
    }
}
