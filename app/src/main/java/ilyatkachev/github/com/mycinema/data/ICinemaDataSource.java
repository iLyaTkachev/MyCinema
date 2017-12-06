package ilyatkachev.github.com.mycinema.data;

import android.support.annotation.NonNull;

import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public interface ICinemaDataSource {

    interface LoadMoviesCallback {

        void onMoviesLoaded(List<Movie> pMovies);

        void onDataNotAvailable();
    }

    interface GetMovieCallback {

        void onMovieLoaded(Movie pMovie);

        void onDataNotAvailable();
    }

    void getMovies(@NonNull int pPath, @NonNull LoadMoviesCallback pCallback);

    void getMovie(@NonNull String pMovieId, @NonNull GetMovieCallback pCallback);

}
