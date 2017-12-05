package ilyatkachev.github.com.mycinema.data.remote.Gson;

import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public interface IMoviesListParser {

    //IMoviesList parse() throws Exception;
    List<Movie> parse() throws Exception;
}
