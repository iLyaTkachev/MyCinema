package ilyatkachev.github.com.mycinema.movies.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie implements IMovie {

    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("id")
    private int id;
    @SerializedName("video")
    private boolean isVideo;
    @SerializedName("vote_average")
    private float voteAverage;
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
    public int getPage() {
        return page;
    }
}
