package ilyatkachev.github.com.mycinema.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.api.ApiProvider;
import ilyatkachev.github.com.mycinema.data.remote.gson.MoviesListGsonParser;
import ilyatkachev.github.com.mycinema.http.HttpClient;
import ilyatkachev.github.com.mycinema.http.IResponseListener;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;

public class CinemaRemoteDataSource implements ICinemaDataSource {

    private static CinemaRemoteDataSource INSTANCE;
    private AppExecutors mAppExecutors;
    private HttpClient mHttpClient;
    private ApiProvider mApiProvider;

    private CinemaRemoteDataSource(@NonNull AppExecutors pAppExecutors) {
        mAppExecutors = pAppExecutors;
        mHttpClient = new HttpClient();
        mApiProvider = new ApiProvider();
    }

    public static CinemaRemoteDataSource getINSTANCE(@NonNull AppExecutors pAppExecutors) {
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
    public void getMovies(@NonNull final int pPage, @NonNull final LoadMoviesCallback pCallback) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Log.d("Tag" + "---" + pPage, "Make a request");
                mHttpClient.request(mApiProvider.getPopularMovieList(pPage), new IResponseListener() {

                    @Override
                    public void onResponse(String pResult) throws Exception {
                        Log.d("Tag" + "---" + pPage, "On responce");
                        final JSONObject jsonList = new JSONObject(pResult);
                        MoviesListGsonParser parser = new MoviesListGsonParser(jsonList.get("results").toString());
                        final List<Movie> movieList = parser.parse().getMovieList();
                        mAppExecutors.getMainThread().execute(new Runnable() {

                            @Override
                            public void run() {
                                pCallback.onMoviesLoaded(movieList);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable pThrowable) {
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

}

    @Override
    public void getMovie(@NonNull final String pMovieId, @NonNull final GetMovieCallback pCallback) {

    }
}
