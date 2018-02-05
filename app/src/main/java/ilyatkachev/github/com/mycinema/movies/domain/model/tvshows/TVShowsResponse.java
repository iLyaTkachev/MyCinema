package ilyatkachev.github.com.mycinema.movies.domain.model.tvshows;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;

public class TVShowsResponse extends BaseMediaResponse {

    @SerializedName("results")
    private List<TVShow> mTVShowList;

    public TVShowsResponse(final Integer pPage, final Integer pTotalResults, final Integer pTotalPages, final List<TVShow> pTVShowList) {
        super(pPage, pTotalResults, pTotalPages);
        mTVShowList = pTVShowList;
    }

    public TVShowsResponse() {

    }

    @Override
    public List<TVShow> getMediaList() {
        return mTVShowList;
    }
}
