package com.icloudwhale.cloudpos.base;

import android.view.View;

import com.iwhalecloud.common.view.refresh.DaisyRefreshLayout;

/**
 * @author xuliangliang
 * @date 2019-09-09
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseRefreshFragment<T,P extends BasePresenter> extends BaseMvpFragment<P> implements BaseRefreshView<T> {

    private DaisyRefreshLayout mRefreshLayout;

    @Override
    protected void initCommonView(View view) {
        super.initCommonView(view);
        mRefreshLayout = view.findViewById(onBindRefreshLayout());
        // 下拉刷新
        mRefreshLayout.setOnRefreshListener(this::onRefreshEvent);
        // 上拉加载
        mRefreshLayout.setOnLoadMoreListener(this::onLoadMoreEvent);
        // 自动加载
        mRefreshLayout.setOnAutoLoadListener(this::onAutoLoadEvent);
    }

    /**
     * refreshLayout
     *
     * @return id
     */
    protected abstract int onBindRefreshLayout();

    /**
     * 是否启用下拉刷新
     *
     * @param b true 启用 默认true
     */
    @Override
    public void enableRefresh(boolean b) {
        mRefreshLayout.setEnableRefresh(b);
    }

    /**
     * 是否启用上拉加载更多
     *
     * @param b true 启用 默认true
     */
    @Override
    public void enableLoadMore(boolean b) {
        mRefreshLayout.setEnableLoadMore(b);
    }

    /**
     * 停止刷新
     */
    @Override
    public void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    /**
     * 停止加载更多
     */
    @Override
    public void stopLoadMore() {
        mRefreshLayout.setLoadMore(false);
    }

    /**
     * 自动加载数据
     */
    @Override
    public void autoLoadData() {
        mRefreshLayout.autoRefresh();
    }

}
