package ilyatkachev.github.com.mycinema.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RVOnScrollListener extends RecyclerView.OnScrollListener {

    private int mPreviousTotal;
    private int mVisibleThreshold;
    private boolean mIsLoading;
    private final IScrolled mScrolled;

    public RVOnScrollListener(final int pPreviousTotal, final int pVisibleThreshold, final boolean pIsLoading, final IScrolled pScrolled) {
        mPreviousTotal = pPreviousTotal;
        mVisibleThreshold = pVisibleThreshold;
        mIsLoading = pIsLoading;
        mScrolled = pScrolled;
    }

    public RVOnScrollListener(final int pVisibleThreshold, final IScrolled pScrolled) {
        mVisibleThreshold = pVisibleThreshold;
        mScrolled = pScrolled;
        mPreviousTotal = 0;
        mIsLoading = false;
    }

    @Override
    public void onScrolled(final RecyclerView pRecyclerView, final int dx, final int dy) {

        RecyclerView.LayoutManager layoutManager = pRecyclerView.getLayoutManager();

        final int visibleItemCount = layoutManager.getChildCount();
        final int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItem = 0;

        if (layoutManager instanceof GridLayoutManager) {
            firstVisibleItem = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if (mIsLoading) {
            if (totalItemCount > mPreviousTotal) {

                mPreviousTotal = totalItemCount;
            }
        }
        if (!mIsLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold)) {
            mIsLoading = true;
            mScrolled.loadData();
        }
    }

    public void setLoading(boolean pLoading) {
        mIsLoading = pLoading;
    }

    public interface IScrolled {

        void loadData();
    }
}
