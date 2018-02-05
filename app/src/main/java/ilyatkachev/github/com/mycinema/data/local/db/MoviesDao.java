package ilyatkachev.github.com.mycinema.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ilyatkachev.github.com.mycinema.movies.domain.model.movies.Movie;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM Movies")
    List<Movie> getMovies();

    @Query("SELECT * FROM Movies WHERE _id = :pId")
    Movie getMovieById(String pId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie pMovie);

    @Update
    int updateMovie(Movie pMovie);

    @Query("DELETE FROM Movies WHERE _id = :pId")
    int deleteMovieById(String pId);

    @Query("DELETE FROM Movies")
    void deleteAllMovies();
}
