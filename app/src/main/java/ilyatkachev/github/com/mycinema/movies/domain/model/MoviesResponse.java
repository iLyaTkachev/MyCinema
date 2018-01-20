package ilyatkachev.github.com.mycinema.movies.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;

public class MoviesResponse extends BaseMediaResponse {

    @SerializedName("results")
    private List<Movie> mMovieList;

    public MoviesResponse(final Integer page, final Integer totalResults, final Integer totalPages, final List<Movie> pMovieList) {
        super(page, totalResults, totalPages);
        mMovieList = pMovieList;
    }

    public MoviesResponse() {
    }

    public List<Movie> getMediaList() {
        return mMovieList;
    }

    public void setMovieList(final List<Movie> pMovieList) {
        mMovieList = pMovieList;
    }
}
