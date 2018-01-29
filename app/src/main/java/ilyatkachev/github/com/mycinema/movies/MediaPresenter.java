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

public class MediaPresenter implements IMediaContract.Presenter<BaseMediaObject> {

    private static final String TAG = "MoviePresenter";

    private final IMediaContract.View mView;
    private final GetMedia mGetMedia;
    private final GetFavoriteMedia mGetFavoriteMedia;
    private final AddFavoriteMedia mAddFavoriteMedia;

    private final UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    private final MediaFilterType mMediaFilterType;
    private final MediaType mMediaType;
    private int mCurrentPage;
    private final List<BaseMediaObject> mMovieList;

    public MediaPresenter(@NonNull final IMediaContract.View pView, @NonNull final GetMedia pGetMedia, final GetFavoriteMedia pGetFavoriteMedia, final AddFavoriteMedia pAddFavoriteMedia, @NonNull final UseCaseHandler pUseCaseHandler, final MediaType pMediaType, final MediaFilterType pMediaFilterType) {
        mView = pView;
        mGetMedia = pGetMedia;
        mGetFavoriteMedia = pGetFavoriteMedia;
        mAddFavoriteMedia = pAddFavoriteMedia;
        mUseCaseHandler = pUseCaseHandler;
        mMediaType = pMediaType;
        mMediaFilterType = pMediaFilterType;

        mCurrentPage = 1;
        mMovieList = new ArrayList<>();

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadMedia(false);
    }

    @Override
    public void loadMedia(final boolean forceUpdate) {
        if (forceUpdate) {
            mCurrentPage = 1;
        }
        loadMedia(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadMedia(final boolean pForceUpdate, final boolean pShowLoadingUI) {
        if (pShowLoadingUI) {

        }
        final GetMedia.RequestValues requestValues = new GetMedia.RequestValues(mMediaType, mCurrentPage, mMediaFilterType);

        mUseCaseHandler.execute(mGetMedia, requestValues, new UseCase.UseCaseCallback<GetMedia.ResponseValue>() {

            @Override
            public void onSuccess(final GetMedia.ResponseValue pResponse) {
                mMovieList.addAll(pResponse.getMediaList());
                mCurrentPage++;

                if (pShowLoadingUI) {

                }
                mView.showMediaList(mMovieList);
            }

            @Override
            public void onError() {
                if (pShowLoadingUI) {

                }
                mView.showLoadingError();
            }
        });
    }

    public MediaFilterType getMediaFilterType() {
        return mMediaFilterType;
    }

    public MediaType getMediaType() {
        return mMediaType;
    }

    @Override
    public List<BaseMediaObject> getMediaList() {
        return mMovieList;
    }

    @Override
    public void addToFavorite(final BaseMediaObject pMediaObject) {
        final AddFavoriteMedia.RequestValues requestValues = new AddFavoriteMedia.RequestValues(pMediaObject);

        mUseCaseHandler.execute(mAddFavoriteMedia, requestValues, new UseCase.UseCaseCallback<AddFavoriteMedia.ResponseValue>() {

            @Override
            public void onSuccess(final AddFavoriteMedia.ResponseValue pResponse) {
                Log.d(TAG, "Favorite movie added to db");
            }

            @Override
            public void onError() {
                Log.d(TAG, "Cant add favorite to db");
            }
        });
    }

    @Override
    public void getFavoriteList() {

        mUseCaseHandler.execute(mGetFavoriteMedia, new GetFavoriteMedia.RequestValues(), new UseCase.UseCaseCallback<GetFavoriteMedia.ResponseValue>() {

            @Override
            public void onSuccess(final GetFavoriteMedia.ResponseValue pResponse) {
                mView.showFavoriteMediaList(pResponse.getMediaList());
            }

            @Override
            public void onError() {
                Log.d(TAG, "Cant load favorite from db");
            }
        });
    }
}
