package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class GetFavoriteMovies extends UseCase<GetFavoriteMovies.RequestValues, GetFavoriteMovies.ResponseValue> {

    private final CinemaRepository mCinemaRepository;

    public GetFavoriteMovies(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    public void executeUseCase(final GetFavoriteMovies.RequestValues pRequestValues) {
        mCinemaRepository.getFavoriteMedia(new ICinemaDataSource.LoadMediaCallback<Movie>() {

            @Override
            public void onMediaLoaded(final List<Movie> pMovies) {
                getUseCaseCallback().onSuccess(new GetFavoriteMovies.ResponseValue(pMovies));
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        public RequestValues() {
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<Movie> mMovies;

        public ResponseValue(@NonNull final List<Movie> pMovies) {
            mMovies = pMovies;
        }

        public List<Movie> getMovies() {
            return mMovies;
        }
    }
}
