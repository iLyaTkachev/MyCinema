package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;
import ilyatkachev.github.com.mycinema.movies.MoviesFilterType;
import ilyatkachev.github.com.mycinema.movies.domain.model.MoviesResponse;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class GetMedia extends UseCase<GetMedia.RequestValues, GetMedia.ResponseValue> {

    private final CinemaRepository mCinemaRepository;

    public GetMedia(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    public void executeUseCase(final RequestValues pRequestValues) {
        mCinemaRepository.getMedia(new MoviesResponse(), pRequestValues.getPage(), pRequestValues.getMoviesFilterType().toApiString(), new ICinemaDataSource.LoadMediaCallback<BaseMediaResponse>() {

            @Override
            public void onMediaLoaded(final BaseMediaResponse pMediaResponse) {
                getUseCaseCallback().onSuccess(new ResponseValue(pMediaResponse));
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

        private final BaseMediaResponse mMediaResponse;

        public ResponseValue(@NonNull final BaseMediaResponse pMediaResponse) {
            mMediaResponse = pMediaResponse;
        }

        public List<BaseMediaObject> getMediaList() {
            return mMediaResponse.getMediaList();
        }

    }
}
