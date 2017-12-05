package ilyatkachev.github.com.mycinema.data.remote.Gson;

import java.util.ArrayList;
import java.util.List;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public class MoviesListFromGsonList implements IMoviesList {

    private List<Movie> mMovieList;

    public MoviesListFromGsonList(List<Movie> pMovieList) {
        mMovieList = pMovieList;
    }

    @Override
    public List<IMovie> getMovieList() {
        final List<IMovie> movies = new ArrayList<>();
        for (final Movie movie :
                mMovieList) {
            movies.add(movie);
        }
        return movies;
    }
}
