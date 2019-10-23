package com.icloudwhale.home.home;

import com.iwhalecloud.common.base.BasePresenter;

/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    HomePresenter(HomeContract.View view) {
        super(view);
    }

}