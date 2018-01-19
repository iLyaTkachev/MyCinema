package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

import java.util.List;

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
    public void getMedia(final int pPath, @NonNull final String pType, @NonNull final LoadMediaCallback pCallback) {
        //First check local data source, if there are no movies with such page then use remote data source
        getMoviesFromLocalDataSource(pPath,pType,pCallback);
    }

    private void getMoviesFromLocalDataSource(final int pPath, @NonNull final String pType, @NonNull final LoadMediaCallback pCallback) {
        mMoviesLocalDataSource.getMedia(pPath, pType, new LoadMediaCallback<Movie>() {

            @Override
            public void onMediaLoaded(final List<Movie> pMovies) {
                pCallback.onMediaLoaded(pMovies);
            }

            @Override
            public void onDataNotAvailable() {
                getMoviesFromRemoteDataSource(pPath, pType, pCallback);
            }
        });
    }

    private void getMoviesFromRemoteDataSource(final int pPath, @NonNull final String pType, @NonNull final LoadMediaCallback pCallback) {
        mMoviesRemoteDataSource.getMedia(pPath, pType, new LoadMediaCallback<Movie>() {

            @Override
            public void onMediaLoaded(final List<Movie> pMovies) {
                //refreshLocalDataSource();
                pCallback.onMediaLoaded(pMovies);
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
    public void addFavoriteMedia(@NonNull final Movie pMovie) {
        mMoviesLocalDataSource.addFavoriteMedia(pMovie);
    }
}
