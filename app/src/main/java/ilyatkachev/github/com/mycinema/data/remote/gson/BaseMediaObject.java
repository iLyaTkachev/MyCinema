package ilyatkachev.github.com.mycinema.data.remote.gson;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class BaseMediaObject implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    private Integer mId;

    @Ignore
    @SerializedName("vote_count")
    private Integer mVoteCount;
    @Ignore
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @Ignore
    @SerializedName("popularity")
    private Double mPopularity;
    @Ignore
    @SerializedName("poster_path")
    private String mPosterPath;
    @Ignore
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds;
    @Ignore
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @Ignore
    @SerializedName("overview")
    private String mOverview;

    public BaseMediaObject(final Integer pVoteCount, final Integer pId, final Double pVoteAverage, final Double pPopularity, final String pPosterPath, final String pOriginalLanguage, final List<Integer> pGenreIds, final String pBackdropPath, final String pOverview) {
        mVoteCount = pVoteCount;
        mId = pId;
        mVoteAverage = pVoteAverage;
        mPopularity = pPopularity;
        mPosterPath = pPosterPath;
        mOriginalLanguage = pOriginalLanguage;
        mGenreIds = pGenreIds;
        mBackdropPath = pBackdropPath;
        mOverview = pOverview;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(final Integer pVoteCount) {
        mVoteCount = pVoteCount;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(final Integer pId) {
        mId = pId;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(final Double pVoteAverage) {
        mVoteAverage = pVoteAverage;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(final Double pPopularity) {
        mPopularity = pPopularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(final String pPosterPath) {
        mPosterPath = pPosterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(final String pOriginalLanguage) {
        mOriginalLanguage = pOriginalLanguage;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(final List<Integer> pGenreIds) {
        mGenreIds = pGenreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(final String pBackdropPath) {
        mBackdropPath = pBackdropPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(final String pOverview) {
        mOverview = pOverview;
    }

    protected String getReleaseYear(String pDate){
        final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        try {
            final Date releaseDate = sdf1.parse(pDate);
            return sdf2.format(releaseDate);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return "";
    }



}
