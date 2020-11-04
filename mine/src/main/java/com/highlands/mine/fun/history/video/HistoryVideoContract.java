package com.highlands.mine.fun.history.video;

import com.highlands.common.base.BaseRefreshContract;
import com.highlands.common.base.BaseRefreshView;
import com.highlands.mine.http.response.VideoBean;

/**
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
class HistoryVideoContract {

    interface View extends BaseRefreshView<VideoBean> {
    }

    interface Presenter extends BaseRefreshContract.Presenter {
    }
}