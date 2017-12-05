package ilyatkachev.github.com.mycinema.data.remote.Gson;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public class MoviesListGsonParser implements IMoviesListParser {

    private final String mSource;

    public MoviesListGsonParser(String pSource) {
        mSource = pSource;
    }

    @Override
    public List<Movie> parse() throws Exception {
        final Movie[] result = new Gson().fromJson(mSource, Movie[].class);
        //return new MoviesListFromGsonList(Arrays.asList(result));
        return Arrays.asList(result);
    }
}
