package ilyatkachev.github.com.mycinema.data.remote;

import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;

public class CinemaRemoteDataSource implements ICinemaDataSource {

    @Override
    public void getMovies(@NonNull String pPath, @NonNull final LoadMoviesCallback pCallback) {

    }

    @Override
    public void getMovie(@NonNull final String pMovieId, @NonNull final GetMovieCallback pCallback) {

    }
}
