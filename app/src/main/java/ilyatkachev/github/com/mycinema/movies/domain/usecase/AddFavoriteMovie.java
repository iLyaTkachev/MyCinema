package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class AddFavoriteMovie extends UseCase<AddFavoriteMovie.RequestValues, AddFavoriteMovie.ResponseValue>{
    private final CinemaRepository mCinemaRepository;

    public AddFavoriteMovie(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    public void executeUseCase(final AddFavoriteMovie.RequestValues pRequestValues) {
        mCinemaRepository.addFavoriteMovie(pRequestValues.getMovie());
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final Movie mMovie;

        public RequestValues(final Movie pMovie) {
            mMovie = pMovie;
        }

        public Movie getMovie() {
            return mMovie;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}
