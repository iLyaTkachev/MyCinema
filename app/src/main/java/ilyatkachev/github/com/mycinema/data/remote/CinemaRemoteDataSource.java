package ilyatkachev.github.com.mycinema.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.InputStream;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.api.ApiProvider;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;
import ilyatkachev.github.com.mycinema.data.remote.gson.ResponseParser;
import ilyatkachev.github.com.mycinema.http.HttpClient;
import ilyatkachev.github.com.mycinema.http.IResponseListener;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;

public class CinemaRemoteDataSource implements ICinemaDataSource {

    private static final String TAG = "Remote Data Source";

    private static volatile CinemaRemoteDataSource INSTANCE;
    private final AppExecutors mAppExecutors;
    private final HttpClient mHttpClient;
    private final ApiProvider mApiProvider;

    private CinemaRemoteDataSource(@NonNull final AppExecutors pAppExecutors) {
        mAppExecutors = pAppExecutors;
        mHttpClient = new HttpClient();
        mApiProvider = new ApiProvider();
    }

    public static CinemaRemoteDataSource getInstance(@NonNull final AppExecutors pAppExecutors) {
        if (INSTANCE == null) {
            synchronized (CinemaRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CinemaRemoteDataSource(pAppExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getMedia(@NonNull final BaseMediaResponse pResponseObject, @NonNull final String pType, @NonNull final int pPage, @NonNull final String pFilterType, @NonNull final LoadMediaCallback pCallback) {
        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Log.d(TAG + "---" + pPage, "Make a request");
                mHttpClient.request(mApiProvider.getMedia(pType, pPage, pFilterType), new IResponseListener() {

                    @Override
                    public void onResponse(final InputStream pResult) {
                        Log.d(TAG + "---" + pPage, "On response");
                        final BaseMediaResponse mediaResponse = (BaseMediaResponse) new ResponseParser().parse(pResult, pResponseObject);
                        mAppExecutors.getMainThread().execute(new Runnable() {

                            @Override
                            public void run() {
                                pCallback.onMediaLoaded(mediaResponse);
                            }
                        });
                    }

                    @Override
                    public void onError(final Throwable pThrowable) {
                        mAppExecutors.getMainThread().execute(new Runnable() {

                            @Override
                            public void run() {
                                pCallback.onDataNotAvailable();
                            }
                        });
                    }
                });

            }
        };

        mAppExecutors.getNetworkIO().execute(runnable);
    }

    @Override
    public void getMedia(@NonNull final String pMovieId, @NonNull final GetMediaCallback pCallback) {

    }

    @Override
    public void getFavoriteMedia(@NonNull final LoadMediaCallback pCallback) {

    }

    @Override
    public void addFavoriteMedia(@NonNull final BaseMediaObject pMediaObject) {

    }
}
