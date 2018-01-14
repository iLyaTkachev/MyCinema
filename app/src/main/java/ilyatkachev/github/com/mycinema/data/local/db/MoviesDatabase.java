package ilyatkachev.github.com.mycinema.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    private static MoviesDatabase INSTANCE;

    public abstract MoviesDao getMovieDao();

    private static final Object sLock = new Object();

    public static MoviesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MoviesDatabase.class, "Movies.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
