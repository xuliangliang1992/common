package com.highlands.mine.fun.history.live;

import com.highlands.common.base.BaseRefreshContract;
import com.highlands.common.base.BaseRefreshView;
import com.highlands.mine.http.response.PolicyBean;

/**
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
class HistoryLiveContract {

    interface View extends BaseRefreshView<PolicyBean> {
    }

    interface Presenter extends BaseRefreshContract.Presenter {
    }
}