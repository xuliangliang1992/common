package com.icloudwhale.cart.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.icloudwhale.cart.R;
import com.iwhalecloud.common.util.SystemUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * item左滑RecyclerView
 *
 * @author xuliangliang
 * @date 2019-10-24
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class SlideExpandableRecyclerView extends RecyclerView {
    /**
     * 最小移动距离
     */
    private int mTouchSlop;
    private int xDown, yDown, xMove, yMove;
    /**
     * 当前选中的item索引
     */
    private int curSelectPosition;
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
    private boolean clickable = true;
    /**
     * 是否可触发滑动事件
     */
    private boolean slideable = false;
    /**
     * item布局
     */
    private ViewGroup mCurItemLayout, mLastItemLayout;
    private Scroller mScroller;
    private OnChildClickListener mOnChildClickListener;
    private OnGroupClickListener mOnGroupClickListener;
    private int lastSelectPosition = -1;
    private float screenWidth;

    public SlideExpandableRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public SlideExpandableRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideExpandableRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context, new LinearInterpolator(context, null));
        screenWidth = SystemUtil.getScreenWidth(context);
    }

    @SuppressLint("ClickableViewAccessibility")
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
                clickable = true;
                if (showDelete && (curSelectPosition != lastSelectPosition || xDown <= screenWidth - mHiddenWidth)) {
                    scrollRight(mLastItemLayout, 0 - mMoveWidth);
                    clickable = false;
                }
                View item = getChildAt(curSelectPosition - firstPosition);
                if (item != null) {
                    mCurItemLayout = (ViewGroup) getChildViewHolder(item).itemView;

                    LinearLayout mLlHidden = mCurItemLayout.findViewById(R.id.ll_hidden);
                    slideable = mLlHidden != null;
                    if (slideable) {
                        mHiddenWidth = mLlHidden.getWidth();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = (int) e.getX();
                yMove = (int) e.getY();
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                if (slideable) {
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
                    clickable = false;
                }
                if (Math.abs(dx) > Math.abs(dy)) {
                    //不让RecyclerView上下滑动
                    return false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (slideable) {
                    if (mMoveWidth < mTouchSlop) {
                        if (clickable && mOnChildClickListener != null) {
                            mOnChildClickListener.onChildClick(curSelectPosition);
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
                                    if (mOnChildClickListener != null) {
                                        mOnChildClickListener.onDelete(curSelectPosition);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (mOnGroupClickListener != null) {
                        mOnGroupClickListener.onGroupClickListener(curSelectPosition);
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

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        mOnGroupClickListener = onGroupClickListener;
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        mOnChildClickListener = onChildClickListener;
    }

    public interface OnChildClickListener {
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
        void onChildClick(int position);
    }

    public interface OnGroupClickListener {

        void onGroupClickListener(int position);
    }
}
