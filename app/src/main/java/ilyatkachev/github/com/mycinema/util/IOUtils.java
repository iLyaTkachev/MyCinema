package ilyatkachev.github.com.mycinema.util;

import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

public final class IOUtils {

    public static final String LOG_TAG = IOUtils.class.getSimpleName();

    private IOUtils() {
    }

    public static void closeStream(final Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (final IOException e) {
                Log.e(LOG_TAG, "Could not close stream");
            }
        }
    }

}
