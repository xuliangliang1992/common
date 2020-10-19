package com.highlands.home.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import com.highlands.home.R;
import com.highlands.common.util.SystemUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * item左滑RecyclerView
 *
 * @author xuliangliang
 * @date 2019-10-24
 * copyright(c) Highlands
 */
public class SlideRecyclerView extends RecyclerView {
    private int screenWidth;
    /**
     * 最小移动距离
     */
    private int mTouchSlop;
    private int xDown, yDown, xMove, yMove;
    /**
     * 当前选中的item索引
     */
    private int curSelectPosition;
    private int lastSelectPosition = -1;
    /**
     * 隐藏部分的长度
     */
    private int mHiddenWidth;
    /**
     * 记录连续移动的长度
     */
    private int mMoveWidth = 0;
    /**
     * 是否触发点击事件
     */
    private boolean isClick = true;
    /**
     * item布局
     */
    private ViewGroup mCurItemLayout, mLastItemLayout;
    private Scroller mScroller;
    private SlideClickListener mSlideClickListener;

    public SlideRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public SlideRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context, new LinearInterpolator(context, null));
        screenWidth = SystemUtil.getScreenWidth(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean showDelete = mMoveWidth > 0;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) e.getX();
                yDown = (int) e.getY();

                int firstPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                Rect itemRect = new Rect();
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    if (child.getVisibility() == View.VISIBLE) {
                        child.getHitRect(itemRect);
                        if (itemRect.contains(xDown, yDown)) {
                            curSelectPosition = firstPosition + i;
                        }
                    }
                }
                isClick = true;
                if (showDelete && (curSelectPosition != lastSelectPosition || xDown <= screenWidth - mHiddenWidth)) {
                    scrollRight(mLastItemLayout, 0 - mMoveWidth);
                    isClick = false;
                }
                View item = getChildAt(curSelectPosition - firstPosition);
                if (item != null) {
                    mCurItemLayout = (ViewGroup) getChildViewHolder(item).itemView;
                    mHiddenWidth = mCurItemLayout.findViewById(R.id.ll_hidden).getWidth();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = (int) e.getX();
                yMove = (int) e.getY();
                int dx = xMove - xDown;
                int dy = yMove - yDown;

                //左滑
                if (dx < 0 && Math.abs(dx) > mTouchSlop && Math.abs(dy) < mTouchSlop) {
                    int newScrollX = Math.abs(dx);
                    if (mMoveWidth >= mHiddenWidth) {
                        //超过最大距离，不可移动
                        newScrollX = 0;
                    } else if (mMoveWidth + newScrollX > mHiddenWidth) {
                        //要超了
                        newScrollX = mHiddenWidth - mMoveWidth;
                    }
                    scrollLeft(mCurItemLayout, newScrollX);
                    mMoveWidth = mMoveWidth + newScrollX;
                } else if (dx > 0) {
                    scrollRight(mCurItemLayout, 0 - mMoveWidth);
                }
                isClick = false;
                if (Math.abs(dx) > Math.abs(dy)) {
                    //不让RecyclerView上下滑动
                    return false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mMoveWidth < mTouchSlop) {
                    if (mSlideClickListener != null && isClick) {
                        mSlideClickListener.onClick(curSelectPosition);
                    }
                } else {
                    if (mHiddenWidth > mMoveWidth) {
                        if (mCurItemLayout.getScrollX() > mHiddenWidth / 2) {
                            scrollLeft(mCurItemLayout, mHiddenWidth - mMoveWidth);
                            mMoveWidth = mHiddenWidth;
                        } else {
                            scrollRight(mCurItemLayout, 0 - mMoveWidth);
                        }
                    } else {
                        if (showDelete && lastSelectPosition == curSelectPosition && xDown > screenWidth - mHiddenWidth && xDown < screenWidth) {
                            if (e.getX() > screenWidth - mHiddenWidth) {
                                scrollRight(mCurItemLayout, 0 - mMoveWidth);
                                if (mSlideClickListener != null) {
                                    mSlideClickListener.onDelete(curSelectPosition);
                                }
                            }
                        }
                    }

                }
                mLastItemLayout = mCurItemLayout;
                lastSelectPosition = curSelectPosition;
                return true;
            default:

                break;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mCurItemLayout.scrollBy(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    /**
     * 向左滑动
     */
    private void scrollLeft(View item, int scrollX) {
        item.scrollBy(scrollX, 0);
    }

    /**
     * 向右滑动
     */
    private void scrollRight(View item, int scrollX) {
        if (item != null) {
            item.scrollBy(scrollX, 0);
            mMoveWidth = 0;
        }
    }

    public void setSlideClickListener(SlideClickListener slideClickListener) {
        mSlideClickListener = slideClickListener;
    }

    public interface SlideClickListener {
        /**
         * 删除
         *
         * @param position
         */
        void onDelete(int position);

        /**
         * 点击事件
         *
         * @param position
         */
        void onClick(int position);
    }
}
