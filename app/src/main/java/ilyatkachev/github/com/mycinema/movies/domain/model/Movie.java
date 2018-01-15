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

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @Ignore
    @SerializedName("vote_count")
    private int voteCount;
    @Ignore
    @SerializedName("video")
    private boolean isVideo;
    @Ignore
    @SerializedName("vote_average")
    private float voteAverage;
    @Ignore
    @SerializedName("popularity")
    private float popularity;
    @Ignore
    @SerializedName("poster_path")
    private String posterPath;
    @Ignore
    @SerializedName("original_language")
    private String originalLanguage;
    @Ignore
    @SerializedName("original_title")
    private String originalTitle;
    @Ignore
    @SerializedName("backdrop_path")
    private String backdropPath;
    @Ignore
    @SerializedName("adult")
    private boolean isAdult;
    @Ignore
    @SerializedName("overview")
    private String overview;
    @Ignore
    @SerializedName("release_date")
    private String releaseDate;

    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @Ignore
    @SerializedName("page")
    private int page;


    public Movie() {
    }

    @Ignore
    public Movie(int _id, String title) {
        id = _id;
        title = title;
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

    public void setId(int pId) {
        id = pId;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }
}
