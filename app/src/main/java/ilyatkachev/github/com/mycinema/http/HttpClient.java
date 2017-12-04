package ilyatkachev.github.com.mycinema.http;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class HttpClient implements IHttpClient {

    private static final String HTTPS = "https";

    @Override
    public void request(@NonNull final String pUrl, final IResponseListener pListener) {
        HttpURLConnection connection = null;
        final InputStream inputStream;

        try {
            if (pUrl.contains(HTTPS)) {
                connection = (HttpsURLConnection) getConnection(pUrl);
            } else {
                connection = (HttpURLConnection) getConnection(pUrl);
            }
            inputStream = connection.getInputStream();
            pListener.onResponse(inputStream);
            connection.disconnect();
        } catch (final Throwable t) {
            pListener.onError(t);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @VisibleForTesting
    private URLConnection getConnection(@NonNull final String pUrl) throws Exception {

        final URL url = new URL(pUrl);
        final URLConnection connection = url.openConnection();

        if (connection == null) {
            throw new Exception("URLConnection == null");
        }

        return connection;
    }

}
