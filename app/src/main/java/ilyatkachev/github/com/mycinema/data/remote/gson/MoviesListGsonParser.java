package ilyatkachev.github.com.mycinema.data.remote.gson;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public class MoviesListGsonParser implements IMovieListParser {

    private final String mSource;

    public MoviesListGsonParser(String pSource) {
        mSource = pSource;
    }

    @Override
    public IMovieList parse() throws Exception {
        final Movie[] result = new Gson().fromJson(mSource, Movie[].class);
        return new MovieListGson(Arrays.asList(result));
    }
}
