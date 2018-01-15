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

    public String getPopularMovieList(final int pPage) {
        return getMovieList(pPage, Constants.ApiValues.POPULAR);
    }

    public String getTopRatedMovieList(final int pPage) {
        return getMovieList(pPage, Constants.ApiValues.TOP_RATED);
    }

    public String getUpcomingMovieList(final int pPage) {
        return getMovieList(pPage, Constants.ApiValues.UPCOMING);
    }

    public String getNowPlayingMovieList(final int pPage) {
        return getMovieList(pPage, Constants.ApiValues.NOW_PLAYING);
    }

    public String getMovieList(final int pPage, final String pType) {
        final Uri uri = Uri.parse(Constants.ApiValues.BASE_URL).buildUpon()
                .appendPath(Constants.ApiValues.MOVIE).appendPath(pType)
                .appendQueryParameter(Constants.ApiValues.API_KEY, SECRET_KEY)
                .appendQueryParameter(Constants.ApiValues.LANGUAGE, Constants.ApiValues.ENGLISH_LANG)
                .appendQueryParameter(Constants.ApiValues.PAGE, String.valueOf(pPage)).build();

        final String url = uri.toString();

        return url;
    }

}
