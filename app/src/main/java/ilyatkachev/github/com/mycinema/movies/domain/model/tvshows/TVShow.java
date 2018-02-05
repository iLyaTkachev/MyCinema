package ilyatkachev.github.com.mycinema.movies.domain.model.tvshows;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;

public class TVShow extends BaseMediaObject implements Serializable {
    @SerializedName("original_name")
    private String mOriginalName;
    @SerializedName("name")
    private String mName;
    @SerializedName("first_air_date")
    private String mFirstAirDate;
    @SerializedName("origin_country")
    private List<String> mOriginCountries;

    @Override
    public String getTitle() {
        return mName;
    }

    @Override
    public String getOriginalTitle() {
        return mOriginalName;
    }

    public void setOriginalName(final String pOriginalName) {
        mOriginalName = pOriginalName;
    }

    public void setName(final String pName) {
        mName = pName;
    }

    public String getFirstAirDate() {
        return mFirstAirDate;
    }

    public void setFirstAirDate(final String pFirstAirDate) {
        mFirstAirDate = pFirstAirDate;
    }

    public List<String> getOriginCountries() {
        return mOriginCountries;
    }

    public void setOriginCountries(final List<String> pOriginCountries) {
        mOriginCountries = pOriginCountries;
    }

    @Override
    public String getReleaseYear() {
        return super.getReleaseYear(mFirstAirDate);
    }
}
