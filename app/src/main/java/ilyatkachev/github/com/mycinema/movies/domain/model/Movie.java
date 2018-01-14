package ilyatkachev.github.com.mycinema.movies.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity(tableName = "movies")
public class Movie implements IMovie {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    private int id;

    @Ignore
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("video")
    private boolean isVideo;
    @SerializedName("vote_average")
    private float voteAverage;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("adult")
    private boolean isAdult;
    @SerializedName("overview")
    private String overview;

    @Ignore
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("page")
    private int page;

    public Movie() {
    }

    public Movie(String pTitle) {
        title = pTitle;
    }

    @Override
    public int getVoteСount() {
        return voteCount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isVideo() {
        return isVideo;
    }

    @Override
    public float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public float getPopularity() {
        return popularity;
    }

    @Override
    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @Override
    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public boolean isAdult() {
        return isAdult;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String getReleaseYear() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        try {
            Date releaseDate = sdf1.parse(getReleaseDate());
            return sdf2.format(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getPage() {
        return page;
    }
}
