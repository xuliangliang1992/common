package com.iwhalecloud.common.view;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
import android.util.DisplayMetrics;

/**
 * @author xll
 * @date 2019/4/30
 */
public class TopSmoothScroller extends LinearSmoothScroller {
    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 0.2f;
    }

    /**
     * 以下参数以LinearSmoothScroller解释
     * @param viewStart RecyclerView的top位置
     * @param viewEnd RecyclerView的bottom位置
     * @param boxStart Item的top位置
     * @param boxEnd Item的bottom位置
     * @param snapPreference 判断滑动方向的标识（The edge which the view should snap to when entering the visible
     *                       area. One of {@link #SNAP_TO_START}, {@link #SNAP_TO_END} or
     *                       {@link #SNAP_TO_END}.）
     * @return 移动偏移量
     */
    @Override
    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        return boxStart-viewStart;
    }
}
