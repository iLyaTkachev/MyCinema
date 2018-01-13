package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.IBaseCinemaObject;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public interface ICinemaDataSource {

    interface LoadObjectsCallback<T extends IBaseCinemaObject> {

        void onObjectsLoaded(List<T> pObjects);

        void onDataNotAvailable();
    }

    interface GetObjectCallback<T extends IBaseCinemaObject> {

        void onMovieLoaded(T pObject);

        void onDataNotAvailable();
    }

    void getMovies(@NonNull int pPath,@NonNull String pType, @NonNull LoadObjectsCallback pCallback);

    void getMovie(@NonNull String pMovieId, @NonNull GetObjectCallback pCallback);

}
