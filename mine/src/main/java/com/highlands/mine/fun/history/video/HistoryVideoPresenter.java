package com.highlands.mine.fun.history.video;

import com.highlands.common.base.BasePresenter;

/**
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
class HistoryVideoPresenter extends BasePresenter<HistoryVideoContract.View> implements HistoryVideoContract.Presenter {

    HistoryVideoPresenter(HistoryVideoContract.View view) {
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