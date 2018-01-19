package ilyatkachev.github.com.mycinema.data.local;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.local.db.MoviesDao;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;

public class CinemaLocalDataSource implements ICinemaDataSource {

    private static volatile CinemaLocalDataSource INSTANCE;

    private final MoviesDao mMoviesDao;

    private final AppExecutors mAppExecutors;

    private CinemaLocalDataSource(@NonNull final AppExecutors pAppExecutors, @NonNull final MoviesDao pMoviesDao) {
        mAppExecutors = pAppExecutors;
        mMoviesDao = pMoviesDao;
    }

    public static CinemaLocalDataSource getInstance(@NonNull final AppExecutors pAppExecutors, @NonNull final MoviesDao pMoviesDao) {
        if (INSTANCE == null) {
            synchronized (CinemaLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CinemaLocalDataSource(pAppExecutors, pMoviesDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getMedia(@NonNull final int pPath, @NonNull final String pType, @NonNull final LoadMediaCallback pCallback) {
        pCallback.onDataNotAvailable();
    }

    @Override
    public void getMedia(@NonNull final String pMovieId, @NonNull final GetMediaCallback pCallback) {

    }

    @Override
    public void getFavoriteMedia(@NonNull final LoadMediaCallback pCallback) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Movie> movies = mMoviesDao.getMovies();
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            // This will be called if the table is new or just empty.
                            pCallback.onDataNotAvailable();
                        } else {
                            pCallback.onMediaLoaded(movies);
                        }
                    }
                });
            }
        };

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void addFavoriteMedia(@NonNull final Movie pMovie) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mMoviesDao.insertMovie(pMovie);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }
}
