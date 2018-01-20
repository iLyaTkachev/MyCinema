package ilyatkachev.github.com.mycinema.tvshows;

import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaResponse;

public class TVShowsResponse extends BaseMediaResponse{

    public TVShowsResponse(Integer pPage, Integer pTotalResults, Integer pTotalPages) {
        super(pPage, pTotalResults, pTotalPages);
    }

    @Override
    public List getMediaList() {
        return null;
    }
}
