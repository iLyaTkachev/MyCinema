package ilyatkachev.github.com.mycinema.movies;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.AddFavoriteMovie;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetFavoriteMovies;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetMovies;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;
import ilyatkachev.github.com.mycinema.util.usecase.UseCaseHandler;

public class MoviesPresenter implements IMoviesContract.Presenter<Movie> {

    private static final String TAG = "MoviePresenter";

    private final IMoviesContract.View mView;
    private final GetMovies mGetMovies;
    private final GetFavoriteMovies mGetFavoriteMovies;
    private final AddFavoriteMovie mAddFavoriteMovie;

    private final UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    private MoviesFilterType mMoviesFilterType;
    private int mCurrentPage;
    private List<Movie> mMovieList;

    public MoviesPresenter(@NonNull final IMoviesContract.View pView, @NonNull final GetMovies pGetMovies, GetFavoriteMovies pGetFavoriteMovies, AddFavoriteMovie pAddFavoriteMovie, @NonNull final UseCaseHandler pUseCaseHandler, final MoviesFilterType pMoviesFilterType) {
        mView = pView;
        mGetMovies = pGetMovies;
        mGetFavoriteMovies = pGetFavoriteMovies;
        mAddFavoriteMovie = pAddFavoriteMovie;
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

    @Override
    public void addToFavorite(Movie pMovie) {
        final AddFavoriteMovie.RequestValues requestValues = new AddFavoriteMovie.RequestValues(pMovie);

        mUseCaseHandler.execute(mAddFavoriteMovie, requestValues, new UseCase.UseCaseCallback<AddFavoriteMovie.ResponseValue>() {

            @Override
            public void onSuccess(AddFavoriteMovie.ResponseValue pResponse) {
                Log.d(TAG,"Favorite movie added to db");
            }

            @Override
            public void onError() {
                Log.d(TAG,"Cant add favorite to db");
            }
        });
    }

    @Override
    public void getFavoriteList() {

        mUseCaseHandler.execute(mGetFavoriteMovies, new GetFavoriteMovies.RequestValues(), new UseCase.UseCaseCallback<GetFavoriteMovies.ResponseValue>() {

            @Override
            public void onSuccess(GetFavoriteMovies.ResponseValue pResponse) {
                mView.showFavoriteMovies(pResponse.getMovies());
            }

            @Override
            public void onError() {
                Log.d(TAG,"Cant load favorite from db");
            }
        });
    }
}
