package ilyatkachev.github.com.mycinema.data.remote.gson;

import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public class MovieListGson implements IMovieList {

    private List<Movie> mMovieList;

    public MovieListGson(List<Movie> pMovieList) {
        mMovieList = pMovieList;
    }

    @Override
    public List<Movie> getMovieList() {
        return mMovieList;
    }
}
