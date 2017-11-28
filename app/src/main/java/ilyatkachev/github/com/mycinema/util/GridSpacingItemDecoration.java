package ilyatkachev.github.com.mycinema.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mSpacing;
    private boolean mIncludeEdge;

    public GridSpacingItemDecoration(int pSpanCount, int pSpacing, boolean pIncludeEdge) {
        this.mSpanCount = pSpanCount;
        this.mSpacing = pSpacing;
        this.mIncludeEdge = pIncludeEdge;
    }

    @Override
    public void getItemOffsets(Rect pOutRect, View pView, RecyclerView pParent, RecyclerView.State pState) {
        int position = pParent.getChildAdapterPosition(pView); // item position
        int column = position % mSpanCount; // item column

        if (mIncludeEdge) {
            pOutRect.left = mSpacing - column * mSpacing / mSpanCount;
            pOutRect.right = (column + 1) * mSpacing / mSpanCount;

            if (position < mSpanCount) { // top edge
                pOutRect.top = mSpacing;
            }
            pOutRect.bottom = mSpacing; // item bottom
        } else {
            pOutRect.left = column * mSpacing / mSpanCount;
            pOutRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;
            if (position >= mSpanCount) {
                pOutRect.top = mSpacing; // item top
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(int pDp, Context pContext) {
        Resources r = pContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pDp, r.getDisplayMetrics()));
    }
}