package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

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
    public void getMovies(@NonNull String pPath, @NonNull LoadMoviesCallback pCallback) {
        //mMoviesRemoteDataSource.getMovies();
    }

    @Override
    public void getMovie(@NonNull String pMovieId, @NonNull GetMovieCallback pCallback) {

    }
}
