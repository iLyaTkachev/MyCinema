package ilyatkachev.github.com.mycinema.util;

import android.content.Context;
import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.local.CinemaLocalDataSource;
import ilyatkachev.github.com.mycinema.data.remote.CinemaRemoteDataSource;
import ilyatkachev.github.com.mycinema.movies.domain.usecase.GetMovies;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;
import ilyatkachev.github.com.mycinema.util.usecase.UseCaseHandler;

public class Injection {

    public static CinemaRepository provideCinemaRepository(@NonNull Context context) {
        AppExecutors appExecutors = new AppExecutors();
        return CinemaRepository.getInstance(CinemaRemoteDataSource.getInstance(appExecutors),
                CinemaLocalDataSource.getInstance(new AppExecutors()));
    }

    public static GetMovies provideGetMovies(@NonNull Context context) {
        return new GetMovies(provideCinemaRepository(context));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }
}
