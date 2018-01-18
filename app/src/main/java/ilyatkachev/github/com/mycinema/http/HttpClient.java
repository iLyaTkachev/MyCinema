package ilyatkachev.github.com.mycinema.http;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import ilyatkachev.github.com.mycinema.http.stream.HttpStreamProvider;

public class HttpClient implements IHttpClient {

    private static final String HTTPS = "https";
    private final String TAG = this.getClass().getCanonicalName();

    @Override
    public void request(@NonNull final String pUrl, final IResponseListener pListener) {
        HttpStreamProvider.HttpResponse httpResponse = null;
        try {
            final HttpStreamProvider streamProvider = new HttpStreamProvider();
            httpResponse = streamProvider.get(pUrl);
            pListener.onResponse(httpResponse.getInputStream());
        } catch (final Throwable t) {
            pListener.onError(t);
        } finally {
            if (httpResponse != null) {
                httpResponse.closeConnectionAndStream();
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

    @VisibleForTesting
    private String streamToString(final InputStream pInputStream) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(pInputStream));
        final StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

}
