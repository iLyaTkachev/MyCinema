package ilyatkachev.github.com.mycinema.movies;

import java.util.List;

import ilyatkachev.github.com.mycinema.IBasePresenter;
import ilyatkachev.github.com.mycinema.IBaseView;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;

/**
 * This interface specifies contract between View and Presenter.
 */
public interface IMediaContract {

    interface View<T extends BaseMediaObject> extends IBaseView<Presenter> {

        void showMediaList(List<T> pMedia);

        void showFavoriteMediaList(List<T> pMedia);

        void showLoadingError();

        void setLoadingIndicator(boolean active);

        boolean isActive();
    }

    interface Presenter<T extends BaseMediaObject> extends IBasePresenter {

        void loadMedia(boolean forceUpdate);

        List<T> getMediaList();

        void addToFavorite(T object);

        void getFavoriteList();
    }
}
