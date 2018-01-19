package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.data.remote.gson.IBaseCinemaObject;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public interface ICinemaDataSource {

    interface LoadMediaCallback<T extends BaseMediaObject> {

        void onMediaLoaded(List<T> pObjects);

        void onDataNotAvailable();
    }

    interface GetMediaCallback<T extends BaseMediaObject> {

        void onMediaLoaded(T pObject);

        void onDataNotAvailable();
    }

    void getMedia(@NonNull int pPath, @NonNull String pType, @NonNull LoadMediaCallback pCallback);

    void getMedia(@NonNull String pMovieId, @NonNull GetMediaCallback pCallback);

    void getFavoriteMedia(@NonNull LoadMediaCallback pCallback);

    void addFavoriteMedia(@NonNull BaseMediaObject pMovie);

}
