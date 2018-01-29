package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.NotNull;

/**
 * Concrete implementation to load movies from the data sources.
 */

public class CinemaRepository implements ICinemaDataSource {

    private static CinemaRepository INSTANCE = null;

    private final ICinemaDataSource mMoviesRemoteDataSource;

    private final ICinemaDataSource mMoviesLocalDataSource;

    public static CinemaRepository getInstance(final ICinemaDataSource pMoviesRemoteDataSource,
                                               final ICinemaDataSource pMoviesLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CinemaRepository(pMoviesRemoteDataSource, pMoviesLocalDataSource);
        }
        return INSTANCE;
    }

    private CinemaRepository(@NonNull final ICinemaDataSource tasksRemoteDataSource,
                             @NonNull final ICinemaDataSource tasksLocalDataSource) {
        mMoviesRemoteDataSource = NotNull.check(tasksRemoteDataSource);
        mMoviesLocalDataSource = NotNull.check(tasksLocalDataSource);
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMedia(final BaseMediaResponse pMediaResponse, final String pType, final int pPath, @NonNull final String pFilterType, @NonNull final LoadMediaCallback pCallback) {
        //First check local data source, if there are no movies with such page then use remote data source
        getMediaFromLocalDataSource(pMediaResponse, pType, pPath, pFilterType, pCallback);
    }

    private void getMediaFromLocalDataSource(final BaseMediaResponse pMediaResponse, final String pType, final int pPath, @NonNull final String pFilterType, @NonNull final LoadMediaCallback pCallback) {
        mMoviesLocalDataSource.getMedia(pMediaResponse, pType, pPath, pFilterType, new LoadMediaCallback<BaseMediaResponse>() {

            @Override
            public void onMediaLoaded(final BaseMediaResponse pResponse) {
                pCallback.onMediaLoaded(pResponse);
            }

            @Override
            public void onDataNotAvailable() {
                getMediaFromRemoteDataSource(pMediaResponse, pType, pPath, pFilterType, pCallback);
            }
        });
    }

    private void getMediaFromRemoteDataSource(final BaseMediaResponse pMediaResponse, final String pType, final int pPath, @NonNull final String pFilterType, @NonNull final LoadMediaCallback pCallback) {
        mMoviesRemoteDataSource.getMedia(pMediaResponse, pType, pPath, pFilterType, new LoadMediaCallback<BaseMediaResponse>() {

            @Override
            public void onMediaLoaded(final BaseMediaResponse pResponse) {
                //refreshLocalDataSource();
                pCallback.onMediaLoaded(pResponse);
            }

            @Override
            public void onDataNotAvailable() {
                pCallback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getMedia(@NonNull final String pMovieId, @NonNull final GetMediaCallback pCallback) {

    }

    @Override
    public void getFavoriteMedia(@NonNull final LoadMediaCallback pCallback) {
        mMoviesLocalDataSource.getFavoriteMedia(pCallback);
    }

    @Override
    public void addFavoriteMedia(@NonNull final BaseMediaObject pMediaObject) {
        mMoviesLocalDataSource.addFavoriteMedia(pMediaObject);
    }
}
