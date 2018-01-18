package ilyatkachev.github.com.mycinema.http.stream;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import ilyatkachev.github.com.mycinema.util.IOUtils;

public class HttpStreamProvider implements StreamProvider<String, HttpStreamProvider.HttpResponse> {

    private static final int READ_TIMEOUT = 10 * 1000;
    private static final int CONNECT_TIMEOUT = 5 * 1000;

    private static final String HTTPS = "https";

    @Override
    public HttpResponse get(final String pUrl) throws IOException {

        final HttpURLConnection connection;
        InputStream inputStream = null;

        if (pUrl.contains(HTTPS)) {
            connection = (HttpsURLConnection) getConnection(pUrl);
        } else {
            connection = (HttpURLConnection) getConnection(pUrl);
        }
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        HttpResponse httpResponse = new HttpResponse(connection.getInputStream(), connection);
        return httpResponse;
    }

    @VisibleForTesting
    private URLConnection getConnection(@NonNull final String pUrl) throws IOException {

        final URL url = new URL(pUrl);
        final URLConnection connection = url.openConnection();
        return connection;
    }

    public class HttpResponse {

        private final InputStream mInputStream;
        private final HttpURLConnection mConnection;

        public HttpResponse(final InputStream pInputStream, final HttpURLConnection pConnection) {
            mInputStream = pInputStream;
            mConnection = pConnection;
        }

        public void closeConnectionAndStream() {
            IOUtils.closeStream(mInputStream);
            mConnection.disconnect();
        }

        public InputStream getInputStream() {
            return mInputStream;
        }

        public HttpURLConnection getConnection() {
            return mConnection;
        }
    }
}
