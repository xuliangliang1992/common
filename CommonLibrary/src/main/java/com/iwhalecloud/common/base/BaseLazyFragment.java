package com.iwhalecloud.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

/**
 * 懒加载
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseLazyFragment<T, P extends BasePresenter> extends BaseRefreshFragment<T, P> {

    /**
     * 标志位，标志fragment是否可见。
     */
    protected boolean isVisible;
    /**
     * 标志位，标志已经初始化完成。
     */
    private boolean isPrepared;
    /**
     * 标志位，标志初次加载。
     */
    private boolean isFirst = true;

    /**
     * 在这里实现Fragment数据的懒加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        //只有Fragment onCreateView好了，才能使用控件
        //另外这里调用一次lazyLoad(）
        onVisible();
    }


    private void onVisible() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        //        showProgressDialog();
        onLazyLoad();
        isFirst = false;
    }

    private void onInvisible() {

    }

    /**
     * 懒加载
     */
    @UiThread
    public abstract void onLazyLoad();
}
