package com.highlands.mine.fun.history.live;

import com.highlands.common.base.BasePresenter;

/**
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
class HistoryLivePresenter extends BasePresenter<HistoryLiveContract.View> implements HistoryLiveContract.Presenter {

    HistoryLivePresenter(HistoryLiveContract.View view) {
        super(view);
    }

    /**
     * 刷新数据
     */
    @Override
    public void refreshData() {
        getList(1);
    }

    /**
     * 加载更多
     */
    @Override
    public void loadMoreData(int page) {
        getList(page);
    }


    public void getList(int page) {

    }
}