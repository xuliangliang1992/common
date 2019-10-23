package com.iwhalecloud.common.base.fragment;

import com.iwhalecloud.common.base.BasePresenter;

import androidx.annotation.UiThread;

/**
 * 懒加载
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseLazyFragment<P extends BasePresenter> extends BaseMvpFragment<P> {

    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            // 将数据加载逻辑放到onResume()方法中
            onLazyLoad();
            isFirstLoad = false;
        }
    }

    /**
     * 懒加载
     */
    @UiThread
    public abstract void onLazyLoad();
}
