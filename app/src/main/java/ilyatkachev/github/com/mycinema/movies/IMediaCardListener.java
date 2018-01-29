package ilyatkachev.github.com.mycinema.movies;

import android.view.View;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;

public interface IMediaCardListener {

    void onCardClick(BaseMediaObject pClickedMedia);

    void onCardOverflowClick(BaseMediaObject pClickedMedia, View pView);
}
