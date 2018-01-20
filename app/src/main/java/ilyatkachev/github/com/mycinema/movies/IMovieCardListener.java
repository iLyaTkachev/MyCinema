package ilyatkachev.github.com.mycinema.movies;

import android.view.View;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;

public interface IMovieCardListener {

    void onCardClick(BaseMediaObject pClickedMovie);

    void onCardOverflowClick(BaseMediaObject pClickedMovie, View pView);
}
