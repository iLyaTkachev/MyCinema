package ilyatkachev.github.com.mycinema.data.remote.gson;

import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public interface IMovieListParser {

    IMovieList parse() throws Exception;
}
