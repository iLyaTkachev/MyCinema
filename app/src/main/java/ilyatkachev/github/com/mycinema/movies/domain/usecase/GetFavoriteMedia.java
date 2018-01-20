package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class GetFavoriteMedia extends UseCase<GetFavoriteMedia.RequestValues, GetFavoriteMedia.ResponseValue> {

    private final CinemaRepository mCinemaRepository;

    public GetFavoriteMedia(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    public void executeUseCase(final GetFavoriteMedia.RequestValues pRequestValues) {
        mCinemaRepository.getFavoriteMedia(new ICinemaDataSource.LoadMediaCallback<BaseMediaResponse>() {

            @Override
            public void onMediaLoaded(final BaseMediaResponse pMediaResponse) {
                getUseCaseCallback().onSuccess(new GetFavoriteMedia.ResponseValue(pMediaResponse));
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

        private final BaseMediaResponse mMediaResponse;

        public ResponseValue(@NonNull final BaseMediaResponse pMediaResponse) {
            mMediaResponse = pMediaResponse;
        }

        public List<BaseMediaObject> getMediaList() {
            return mMediaResponse.getMediaList();
        }
    }
}
