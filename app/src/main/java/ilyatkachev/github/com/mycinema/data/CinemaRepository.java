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

    private CinemaRepository(@NonNull ICinemaDataSource tasksRemoteDataSource,
                             @NonNull ICinemaDataSource tasksLocalDataSource) {
        mMoviesRemoteDataSource = NotNull.check(tasksRemoteDataSource);
        mMoviesLocalDataSource = NotNull.check(tasksLocalDataSource);
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMovies(@NonNull final int pPath, @NonNull final String pType, @NonNull final LoadObjectsCallback pCallback) {
        //First check local data source, if there are no movies with such page then use remote data source
        mMoviesRemoteDataSource.getMovies(pPath, pType, new LoadObjectsCallback<Movie>() {

            @Override
            public void onObjectsLoaded(final List<Movie> pMovies) {
                //refreshLocalDataSource();
                pCallback.onObjectsLoaded(pMovies);
            }

            @Override
            public void onDataNotAvailable() {
                getMoviesFromLocalDataSource(pPath, pType, pCallback);
            }
        });
    }

    private void getMoviesFromLocalDataSource(@NonNull final int pPath, @NonNull String pType, @NonNull final LoadObjectsCallback pCallback) {
        mMoviesLocalDataSource.getMovies(pPath, pType, new LoadObjectsCallback<Movie>() {

            @Override
            public void onObjectsLoaded(List<Movie> pMovies) {
                pCallback.onObjectsLoaded(pMovies);
            }

            @Override
            public void onDataNotAvailable() {
                pCallback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getMovie(@NonNull String pMovieId, @NonNull GetObjectCallback pCallback) {

    }
}
