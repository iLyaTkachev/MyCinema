package ilyatkachev.github.com.mycinema.data.local;

import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;

public class CinemaLocalDataSource implements ICinemaDataSource {

    @Override
    public void getMovies(@NonNull String pPath, @NonNull final LoadMoviesCallback pCallback) {

    }

    @Override
    public void getMovie(@NonNull final String pMovieId, @NonNull final GetMovieCallback pCallback) {

    }
}
