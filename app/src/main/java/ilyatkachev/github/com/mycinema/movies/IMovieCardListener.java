package ilyatkachev.github.com.mycinema.movies;

import android.view.View;

import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;

public interface IMovieCardListener {

    void onCardClick(IMovie pClickedMovie);

    void onCardOverflowClick(IMovie pClickedMovie, View pView);
}
