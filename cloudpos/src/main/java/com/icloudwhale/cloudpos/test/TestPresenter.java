package com.icloudwhale.cloudpos.test;

import com.icloudwhale.cloudpos.base.BasePresenter;

import androidx.databinding.ObservableArrayList;

/**
 * @author xll
 */
public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

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
    public void loadMoreData() {
        mView.loadMoreData(new ObservableArrayList<>());
    }
}