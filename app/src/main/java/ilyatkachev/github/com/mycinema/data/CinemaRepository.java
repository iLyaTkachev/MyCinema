package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.NotNull;

/**
 * Concrete implementation to load movies from the data sources into a cache.
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

    private CinemaRepository(@NonNull ICinemaDataSource tasksRemoteDataSource,
                             @NonNull ICinemaDataSource tasksLocalDataSource) {
        mMoviesRemoteDataSource = NotNull.check(tasksRemoteDataSource);
        mMoviesLocalDataSource = NotNull.check(tasksLocalDataSource);
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMovies(@NonNull final String pPath, @NonNull final LoadMoviesCallback pCallback) {
        mMoviesRemoteDataSource.getMovies(pPath, new LoadMoviesCallback() {

            @Override
            public void onMoviesLoaded(List<Movie> pMovies) {
                //refreshLocalDataSource();
                pCallback.onMoviesLoaded(pMovies);
            }

            @Override
            public void onDataNotAvailable() {
                getMoviesFromLocalDataSource(pPath, pCallback);
            }
        });
    }

    private void getMoviesFromLocalDataSource(@NonNull final String pPath, @NonNull final LoadMoviesCallback pCallback) {
        mMoviesLocalDataSource.getMovies(pPath, new LoadMoviesCallback() {

            @Override
            public void onMoviesLoaded(List<Movie> pMovies) {
                pCallback.onMoviesLoaded(pMovies);
            }

            @Override
            public void onDataNotAvailable() {
                pCallback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getMovie(@NonNull String pMovieId, @NonNull GetMovieCallback pCallback) {

    }
}
