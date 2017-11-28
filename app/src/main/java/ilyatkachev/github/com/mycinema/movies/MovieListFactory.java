package ilyatkachev.github.com.mycinema.movies;

import java.util.ArrayList;
import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public class MovieListFactory {

    public static List<IMovie> createMovieList(int pCount) {
        List<IMovie> crimeList = new ArrayList<>();

        for (int i = 0; i < pCount; i++) {
            crimeList.add(new Movie("movie number " + i));
        }
        return crimeList;
    }
}
