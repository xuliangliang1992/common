package com.highlands.tianFuFinance.fun.home;

import com.highlands.common.base.BaseView;
import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.common.http.response.VideoBean;
import com.highlands.tianFuFinance.http.response.BannerBean;

import java.util.List;

/**
 * Home控制器
 *
 * @author xll
 * @date 20
 * copyright(c) Highlands
 */
class HomeContract {

    interface View extends BaseView {

        /**
         * banner
         *
         * @param bannerBeans
         */
        void getBanner(List<BannerBean> bannerBeans);

        /**
         * 最新政策解读列表
         *
         * @param policyBeans
         */
        void getPolicyNews(List<PolicyBean> policyBeans);

        /**
         * 直播预告
         *
         * @param liveBeans
         */
        void getLiveNotices(List<LiveBean> liveBeans);

        /**
         * 最新视频
         *
         * @param videoBeans
         */
        void getVideoNews(List<VideoBean> videoBeans);
    }

    interface Presenter {
        /**
         * banner
         */
        void getBannerList();

        /**
         * 最新政策解读列表
         */
        void getPolicyNews();

        /**
         * 直播预告
         */
        void getLiveNotices();

        /**
         * 最新视频
         */
        void getVideoNews();

    }
}