package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class GetMovies extends UseCase<GetMovies.RequestValues, GetMovies.ResponseValue> {

    private final CinemaRepository mCinemaRepository;

    public GetMovies(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {

    }

    public static final class RequestValues implements UseCase.RequestValues{

    }

    public static final class ResponseValue implements UseCase.ResponseValue{

    }
}
