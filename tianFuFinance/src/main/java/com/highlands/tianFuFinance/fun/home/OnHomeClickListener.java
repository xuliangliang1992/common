package com.highlands.tianFuFinance.fun.home;

import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.common.http.response.VideoBean;

/**
 * @author xuliangliang
 * @date 2020/11/6
 * copyright(c) Highlands
 */
public interface OnHomeClickListener {

    void toInformation();
    void toAsk();
    void toTrain();
    void toShare();

    /**
     * 去政策详情
     * @param policyBean 政策
     * @param position position
     */
    void toPolicyDetail(PolicyBean policyBean, int position);

    /**
     * 去直播详情
     * @param liveBean 直播
     * @param position position
     */
    void toLiveDetail(LiveBean liveBean, int position);

    /**
     * 去视频详情
     * @param videoBean 视频
     * @param position position
     */
    void toVideoDetail(VideoBean videoBean, int position);

}
