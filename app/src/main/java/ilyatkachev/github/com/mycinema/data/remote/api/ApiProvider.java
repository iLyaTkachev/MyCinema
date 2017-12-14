package ilyatkachev.github.com.mycinema.data.remote.api;

import android.net.Uri;

import java.net.URI;
import java.net.URL;
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
        Uri uri = Uri.parse(Constants.ApiValues.BASE_URL).buildUpon()
                .appendPath(Constants.ApiValues.MOVIE).appendPath(pType)
                .appendQueryParameter(Constants.ApiValues.API_KEY, SECRET_KEY)
                .appendQueryParameter(Constants.ApiValues.LANGUAGE, Constants.ApiValues.ENGLISH_LANG)
                .appendQueryParameter(Constants.ApiValues.PAGE, String.valueOf(pPage)).build();

        String url = uri.toString();

        return url;
    }

}
