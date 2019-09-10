package com.icloudwhale.cloudpos.test;

import com.icloudwhale.cloudpos.base.BasePresenter;
import com.icloudwhale.cloudpos.base.BaseView;

/**
 * Test控制器
 *
 * @author xll
 */
class TestContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}