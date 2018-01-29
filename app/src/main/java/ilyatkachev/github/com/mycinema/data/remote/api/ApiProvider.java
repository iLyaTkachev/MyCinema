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

    public String getMedia(final String pType, final int pPage, final String pFilterType) {
        final Uri uri = Uri.parse(Constants.ApiValues.BASE_URL).buildUpon()
                .appendPath(pType).appendPath(pFilterType)
                .appendQueryParameter(Constants.ApiValues.API_KEY, SECRET_KEY)
                .appendQueryParameter(Constants.ApiValues.LANGUAGE, Constants.ApiValues.ENGLISH_LANG)
                .appendQueryParameter(Constants.ApiValues.PAGE, String.valueOf(pPage)).build();

        final String url = uri.toString();

        return url;
    }
}
