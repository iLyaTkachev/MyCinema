package ilyatkachev.github.com.mycinema.movies;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.AddFavoriteMedia;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetFavoriteMedia;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetMedia;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;
import ilyatkachev.github.com.mycinema.util.usecase.UseCaseHandler;

public class MoviesPresenter implements IMoviesContract.Presenter<BaseMediaObject> {

    private static final String TAG = "MoviePresenter";

    private final IMoviesContract.View mView;
    private final GetMedia mGetMedia;
    private final GetFavoriteMedia mGetFavoriteMedia;
    private final AddFavoriteMedia mAddFavoriteMedia;

    private final UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    private final MoviesFilterType mMoviesFilterType;
    private int mCurrentPage;
    private final List<BaseMediaObject> mMovieList;

    public MoviesPresenter(@NonNull final IMoviesContract.View pView, @NonNull final GetMedia pGetMedia, final GetFavoriteMedia pGetFavoriteMedia, final AddFavoriteMedia pAddFavoriteMedia, @NonNull final UseCaseHandler pUseCaseHandler, final MoviesFilterType pMoviesFilterType) {
        mView = pView;
        mGetMedia = pGetMedia;
        mGetFavoriteMedia = pGetFavoriteMedia;
        mAddFavoriteMedia = pAddFavoriteMedia;
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
    public void loadMovies(final boolean forceUpdate) {
        if (forceUpdate){
            mCurrentPage = 1;
        }
        loadMovies(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadMovies(final boolean pForceUpdate, final boolean pShowLoadingUI) {
        if (pShowLoadingUI) {

        }
        final GetMedia.RequestValues requestValues = new GetMedia.RequestValues(mCurrentPage, mMoviesFilterType);

        mUseCaseHandler.execute(mGetMedia, requestValues, new UseCase.UseCaseCallback<GetMedia.ResponseValue>() {

            @Override
            public void onSuccess(final GetMedia.ResponseValue pResponse) {
                mMovieList.addAll(pResponse.getMediaList());
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
    public List<BaseMediaObject> getMovieList() {
        return mMovieList;
    }

    @Override
    public void addToFavorite(final BaseMediaObject pMediaObject) {
        final AddFavoriteMedia.RequestValues requestValues = new AddFavoriteMedia.RequestValues(pMediaObject);

        mUseCaseHandler.execute(mAddFavoriteMedia, requestValues, new UseCase.UseCaseCallback<AddFavoriteMedia.ResponseValue>() {

            @Override
            public void onSuccess(final AddFavoriteMedia.ResponseValue pResponse) {
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

        mUseCaseHandler.execute(mGetFavoriteMedia, new GetFavoriteMedia.RequestValues(), new UseCase.UseCaseCallback<GetFavoriteMedia.ResponseValue>() {

            @Override
            public void onSuccess(final GetFavoriteMedia.ResponseValue pResponse) {
                mView.showFavoriteMovies(pResponse.getMediaList());
            }

            @Override
            public void onError() {
                Log.d(TAG,"Cant load favorite from db");
            }
        });
    }
}
