package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;
import ilyatkachev.github.com.mycinema.movies.MediaFilterType;
import ilyatkachev.github.com.mycinema.movies.MediaType;
import ilyatkachev.github.com.mycinema.movies.domain.model.movies.MoviesResponse;
import ilyatkachev.github.com.mycinema.movies.domain.model.tvshows.TVShowsResponse;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class GetMedia extends UseCase<GetMedia.RequestValues, GetMedia.ResponseValue> {

    private final CinemaRepository mCinemaRepository;

    public GetMedia(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    public void executeUseCase(final RequestValues pRequestValues) {
        BaseMediaResponse mediaResponse = null;
        switch (pRequestValues.mMediaType) {
            case MOVIE:
                mediaResponse = new MoviesResponse();
                break;
            case TV:
                mediaResponse = new TVShowsResponse();
                break;
        }
        mCinemaRepository.getMedia(mediaResponse, pRequestValues.getMediaType().toApiString(), pRequestValues.getPage(), pRequestValues.getMediaFilterType().toApiString(), new ICinemaDataSource.LoadMediaCallback<BaseMediaResponse>() {

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
        private final MediaFilterType mMediaFilterType;
        private final MediaType mMediaType;

        public RequestValues(final MediaType pMediaType, final int pPage, @NonNull final MediaFilterType pMediaFilterType) {
            mMediaType = pMediaType;
            mPage = pPage;
            mMediaFilterType = pMediaFilterType;
        }

        public int getPage() {
            return mPage;
        }

        public MediaFilterType getMediaFilterType() {
            return mMediaFilterType;
        }

        public MediaType getMediaType() {
            return mMediaType;
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
