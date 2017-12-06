package ilyatkachev.github.com.mycinema.data.remote.api;

import java.util.List;

import ilyatkachev.github.com.mycinema.http.HttpClient;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.Constants;
import ilyatkachev.github.com.mycinema.util.SecretConstants;

public class ApiProvider {

    private static final String SECRET_KEY = SecretConstants.MY_SECRET_KEY;

    public ApiProvider() {
    }

    public String getPopularMovieList(int pPage) {
        return getMovieList(pPage, Constants.ApiValues.POPULAR);
    }
    public String getTopRatedMovieList(int pPage) {
        return getMovieList(pPage, Constants.ApiValues.TOP_RATED);
    }
    public String getUpcomingMovieList(int pPage) {
        return getMovieList(pPage, Constants.ApiValues.UPCOMING);
    }
    public String getNowPlayingMovieList(int pPage) {
        return getMovieList(pPage, Constants.ApiValues.NOW_PLAYING);
    }

    private String getMovieList(int pPage, String pType) {
        String url = new StringBuilder().append(Constants.ApiValues.BASE_URL)
                .append(Constants.ApiValues.MOVIE).append(Constants.ApiValues.NOW_PLAYING)
                .append(Constants.ApiValues.API_KEY).append(SECRET_KEY)
                .append(Constants.ApiValues.LANGUAGE).append(Constants.ApiValues.ENGLISH_LANG)
                .append(Constants.ApiValues.PAGE).append(pPage).toString();

        return url;
    }

}
