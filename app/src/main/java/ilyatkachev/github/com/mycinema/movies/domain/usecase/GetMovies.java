package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.movies.MoviesFilterType;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class GetMovies extends UseCase<GetMovies.RequestValues, GetMovies.ResponseValue> {

    private final CinemaRepository mCinemaRepository;

    public GetMovies(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    public void executeUseCase(final RequestValues pRequestValues) {
        mCinemaRepository.getMovies(pRequestValues.getPage(), pRequestValues.getMoviesFilterType().toApiString(), new ICinemaDataSource.LoadObjectsCallback<Movie>() {

            @Override
            public void onObjectsLoaded(final List<Movie> pMovies) {
                getUseCaseCallback().onSuccess(new ResponseValue(pMovies));
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final int mPage;
        private final MoviesFilterType mMoviesFilterType;

        public RequestValues(final int pPage, @NonNull final MoviesFilterType pMoviesFilterType) {
            mPage = pPage;
            mMoviesFilterType = pMoviesFilterType;
        }

        public int getPage() {
            return mPage;
        }

        public MoviesFilterType getMoviesFilterType() {
            return mMoviesFilterType;
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
