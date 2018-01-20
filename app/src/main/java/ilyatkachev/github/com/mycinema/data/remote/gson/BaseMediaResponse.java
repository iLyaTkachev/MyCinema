package ilyatkachev.github.com.mycinema.data.remote.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class BaseMediaResponse {

    @SerializedName("page")
    private Integer mPage;
    @SerializedName("total_pages")
    private Integer mTotalPages;
    @SerializedName("total_results")
    private Integer mTotalResults;

    public BaseMediaResponse(final Integer pPage, final Integer pTotalResults, final Integer pTotalPages) {
        this.mPage = pPage;
        this.mTotalResults = pTotalResults;
        this.mTotalPages = pTotalPages;
    }

    public BaseMediaResponse() {
    }

    public abstract List getMediaList();

    public Integer getPage() {
        return mPage;
    }

    public void setPage(final Integer page) {
        this.mPage = page;
    }

    public Integer getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(final Integer totalResults) {
        this.mTotalResults = totalResults;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(final Integer totalPages) {
        this.mTotalPages = totalPages;
    }

}
