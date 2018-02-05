package ilyatkachev.github.com.mycinema.movies.domain.model.movies;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;

@Entity(tableName = "movies")
public class Movie extends BaseMediaObject implements Serializable {

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String mTitle;

    @Ignore
    @SerializedName("video")
    private boolean mIsVideo;
    @Ignore
    @SerializedName("original_title")
    private String mOriginalTitle;
    @Ignore
    @SerializedName("adult")
    private boolean mIsAdult;
    @Ignore
    @SerializedName("release_date")
    private String mReleaseDate;
    @Ignore
    private int mPage;

    @Ignore
    public Movie(final Integer pVoteCount, final Integer pId, final Double pVoteAverage, final Double pPopularity, final String pPosterPath, final String pOriginalLanguage, final List<Integer> pGenreIds, final String pBackdropPath, final String pOverview, final String pTitle, final boolean pIsVideo, final String pOriginalTitle, final boolean pIsAdult, final String pReleaseDate) {
        super(pVoteCount, pId, pVoteAverage, pPopularity, pPosterPath, pOriginalLanguage, pGenreIds, pBackdropPath, pOverview);
        mTitle = pTitle;
        mIsVideo = pIsVideo;
        mOriginalTitle = pOriginalTitle;
        mIsAdult = pIsAdult;
        mReleaseDate = pReleaseDate;
    }

    public Movie() {
    }

    public Movie(final int _id, final String title) {
        super.setId(_id);
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(final String pTitle) {
        mTitle = pTitle;
    }

    public boolean isVideo() {
        return mIsVideo;
    }

    public void setVideo(final boolean pVideo) {
        mIsVideo = pVideo;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(final String pOriginalTitle) {
        mOriginalTitle = pOriginalTitle;
    }

    public boolean isAdult() {
        return mIsAdult;
    }

    public void setAdult(final boolean pAdult) {
        mIsAdult = pAdult;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(final String pReleaseDate) {
        mReleaseDate = pReleaseDate;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(final int pPage) {
        mPage = pPage;
    }

    public String getReleaseYear() {
        return super.getReleaseYear(mReleaseDate);
    }

}
