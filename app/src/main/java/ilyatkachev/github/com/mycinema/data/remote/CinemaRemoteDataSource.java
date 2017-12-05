package ilyatkachev.github.com.mycinema.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.Gson.MoviesListGsonParser;
import ilyatkachev.github.com.mycinema.http.HttpClient;
import ilyatkachev.github.com.mycinema.http.IResponseListener;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;

public class CinemaRemoteDataSource implements ICinemaDataSource {

    private static CinemaRemoteDataSource INSTANCE;
    private AppExecutors mAppExecutors;

    private CinemaRemoteDataSource(@NonNull AppExecutors pAppExecutors) {
        mAppExecutors = pAppExecutors;
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
    public void getMovies(@NonNull String pPath, @NonNull final LoadMoviesCallback pCallback) {
        for (int i=0;i<10;i++){
            final int index = i;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new HttpClient();
                Log.d("Tag"+"---"+index,"Make a request");
                httpClient.request("https://api.themoviedb.org/3/movie/popular?api_key=ac40e75b91cfb918546f4311f7623a89&language=en-US&page=1", new IResponseListener() {

                    @Override
                    public void onResponse(String pResult) throws Exception {
                        Log.d("Tag"+"---"+index,"On responce");
                        final JSONObject jsonList = new JSONObject(pResult);
                        MoviesListGsonParser parser = new MoviesListGsonParser(jsonList.get("results").toString());
                        final List<Movie> movieList = parser.parse();
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

        mAppExecutors.getNetworkIO().execute(runnable);}
    }

    @Override
    public void getMovie(@NonNull final String pMovieId, @NonNull final GetMovieCallback pCallback) {

    }
}
