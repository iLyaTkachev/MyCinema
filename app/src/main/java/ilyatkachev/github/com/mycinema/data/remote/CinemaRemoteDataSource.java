package ilyatkachev.github.com.mycinema.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.api.ApiProvider;
import ilyatkachev.github.com.mycinema.data.remote.gson.ResponseParser;
import ilyatkachev.github.com.mycinema.http.HttpClient;
import ilyatkachev.github.com.mycinema.http.IResponseListener;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;

public class CinemaRemoteDataSource implements ICinemaDataSource {

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
    public void getMovies(final int pPage, @NonNull final String pType, @NonNull final LoadObjectsCallback pCallback) {
        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Log.d("Tag" + "---" + pPage, "Make a request");
                mHttpClient.request(mApiProvider.getMovieList(pPage, pType), new IResponseListener() {

                    @Override
                    public void onResponse(final String pResult) throws Exception {
                        Log.d("Tag" + "---" + pPage, "On response");
                        final ResponseParser responseParser = new ResponseParser();
                        final List movieList = responseParser.parse(pResult, new Movie(), "results");
                        mAppExecutors.getMainThread().execute(new Runnable() {

                            @Override
                            public void run() {
                                pCallback.onObjectsLoaded(movieList);
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
    public void getMovie(@NonNull final String pMovieId, @NonNull final GetObjectCallback pCallback) {

    }

    @Override
    public void getFavoriteMovies(@NonNull final LoadObjectsCallback pCallback) {

    }

    @Override
    public void addFavoriteMovie(@NonNull final Movie pMovie) {

    }
}
