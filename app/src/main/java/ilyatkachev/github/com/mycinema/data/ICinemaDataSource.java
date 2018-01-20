package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;

public interface ICinemaDataSource {

    interface LoadMediaCallback<T extends BaseMediaResponse> {

        void onMediaLoaded(T pResponse);

        void onDataNotAvailable();
    }

    interface GetMediaCallback<T extends BaseMediaObject> {

        void onMediaLoaded(T pObject);

        void onDataNotAvailable();
    }

    void getMedia(@NonNull BaseMediaResponse pResponse, @NonNull int pPath, @NonNull String pType, @NonNull LoadMediaCallback pCallback);

    void getMedia(@NonNull String pMovieId, @NonNull GetMediaCallback pCallback);

    void getFavoriteMedia(@NonNull LoadMediaCallback pCallback);

    void addFavoriteMedia(@NonNull BaseMediaObject pMovie);

}
