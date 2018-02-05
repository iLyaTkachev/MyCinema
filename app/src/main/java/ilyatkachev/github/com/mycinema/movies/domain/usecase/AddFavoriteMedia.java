package ilyatkachev.github.com.mycinema.movies.domain.usecase;

import android.support.annotation.NonNull;

import ilyatkachev.github.com.mycinema.data.CinemaRepository;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.util.usecase.UseCase;

public class AddFavoriteMedia extends UseCase<AddFavoriteMedia.RequestValues, AddFavoriteMedia.ResponseValue>{
    private final CinemaRepository mCinemaRepository;

    public AddFavoriteMedia(@NonNull final CinemaRepository pCinemaRepository) {
        mCinemaRepository = pCinemaRepository;
    }

    @Override
    public void executeUseCase(final AddFavoriteMedia.RequestValues pRequestValues) {
        mCinemaRepository.addFavoriteMedia(pRequestValues.getMediaObject());
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final BaseMediaObject mMediaObject;

        public RequestValues(final BaseMediaObject pMediaObject) {
            mMediaObject = pMediaObject;
        }

        public BaseMediaObject getMediaObject() {
            return mMediaObject;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}
