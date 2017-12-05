package ilyatkachev.github.com.mycinema.http;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class HttpClient implements IHttpClient {

    private static final String HTTPS = "https";
    private final String TAG = this.getClass().getCanonicalName();

    @Override
    public void request(@NonNull final String pUrl, final IResponseListener pListener) {
        HttpURLConnection connection = null;
        //InputStream inputStream = null;

        try {
            if (pUrl.contains(HTTPS)) {
                connection = (HttpsURLConnection) getConnection(pUrl);
            } else {
                connection = (HttpURLConnection) getConnection(pUrl);
            }
            InputStream inputStream = connection.getInputStream();
            //-----------
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            //-----------
            pListener.onResponse(stringBuilder.toString());
            connection.disconnect();
            inputStream.close();
        } catch (final Throwable t) {
            pListener.onError(t);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            /*if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException ex) {
                    Log.e(TAG, "executeRequest: " + ex.getMessage(), ex);
                }
            }*/
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
