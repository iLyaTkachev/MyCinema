package ilyatkachev.github.com.mycinema.data.local;

import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;

public class CinemaLocalDataSource implements ICinemaDataSource {

    private static volatile CinemaLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;

    private CinemaLocalDataSource(@NonNull AppExecutors pAppExecutors) {
        mAppExecutors = pAppExecutors;
    }

    public static CinemaLocalDataSource getInstance(@NonNull AppExecutors pAppExecutors) {
        if (INSTANCE == null) {
            synchronized (CinemaLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CinemaLocalDataSource(pAppExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getMovies(@NonNull int pPath, @NonNull String pType, @NonNull final LoadObjectsCallback pCallback) {

    }

    @Override
    public void getMovie(@NonNull final String pMovieId, @NonNull final GetObjectCallback pCallback) {

    }
}
