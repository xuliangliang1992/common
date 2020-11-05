package com.highlands.tianFuFinance.fun.test;

import com.highlands.common.base.BasePresenter;

import androidx.databinding.ObservableArrayList;

/**
 * @author xll
 */
class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    TestPresenter(TestContract.View view) {
        super(view);
    }

    /**
     * 刷新数据
     */
    @Override
    public void refreshData() {
        mView.refreshData(new ObservableArrayList<>());
    }

    /**
     * 加载更多
     */
    @Override
    public void loadMoreData(int page) {
        mView.loadMoreData(new ObservableArrayList<>());
    }

    @Override
    public void login() {

    }
}