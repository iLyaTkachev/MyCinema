package ilyatkachev.github.com.mycinema.util;

import android.content.Context;
import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.local.CinemaLocalDataSource;
import ilyatkachev.github.com.mycinema.data.local.db.MoviesDatabase;
import ilyatkachev.github.com.mycinema.data.remote.CinemaRemoteDataSource;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.AddFavoriteMovie;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetFavoriteMovies;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetMovies;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;
import ilyatkachev.github.com.mycinema.util.usecase.UseCaseHandler;

public class Injection {

    public static CinemaRepository provideCinemaRepository(@NonNull final Context pContext) {
        final AppExecutors appExecutors = new AppExecutors();
        final MoviesDatabase moviesDatabase = MoviesDatabase.getInstance(pContext);

        return CinemaRepository.getInstance(CinemaRemoteDataSource.getInstance(appExecutors),
                CinemaLocalDataSource.getInstance(appExecutors, moviesDatabase.getMovieDao()));
    }

    public static GetMovies provideGetMovies(@NonNull final Context pContext) {
        return new GetMovies(provideCinemaRepository(pContext));
    }

    public static GetFavoriteMovies provideGetFavoriteMovies(@NonNull final Context pContext) {
        return new GetFavoriteMovies(provideCinemaRepository(pContext));
    }

    public static AddFavoriteMovie provideAddFavoriteMovie(@NonNull final Context pContext) {
        return new AddFavoriteMovie(provideCinemaRepository(pContext));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }
}
