package ilyatkachev.github.com.mycinema.util;

import android.content.Context;
import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.local.CinemaLocalDataSource;
import ilyatkachev.github.com.mycinema.data.local.db.MoviesDatabase;
import ilyatkachev.github.com.mycinema.data.remote.CinemaRemoteDataSource;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.AddFavoriteMedia;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetFavoriteMedia;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetMedia;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;
import ilyatkachev.github.com.mycinema.util.usecase.UseCaseHandler;

public class Injection {

    public static CinemaRepository provideCinemaRepository(@NonNull final Context pContext) {
        final AppExecutors appExecutors = new AppExecutors();
        final MoviesDatabase moviesDatabase = MoviesDatabase.getInstance(pContext);

        return CinemaRepository.getInstance(CinemaRemoteDataSource.getInstance(appExecutors),
                CinemaLocalDataSource.getInstance(appExecutors, moviesDatabase.getMovieDao()));
    }

    public static GetMedia provideGetMovies(@NonNull final Context pContext) {
        return new GetMedia(provideCinemaRepository(pContext));
    }

    public static GetFavoriteMedia provideGetFavoriteMovies(@NonNull final Context pContext) {
        return new GetFavoriteMedia(provideCinemaRepository(pContext));
    }

    public static AddFavoriteMedia provideAddFavoriteMovie(@NonNull final Context pContext) {
        return new AddFavoriteMedia(provideCinemaRepository(pContext));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }
}
