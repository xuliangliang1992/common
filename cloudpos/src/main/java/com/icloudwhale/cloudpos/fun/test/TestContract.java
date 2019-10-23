package com.icloudwhale.cloudpos.fun.test;

import com.iwhalecloud.common.base.BaseRefreshContract;
import com.iwhalecloud.common.base.BaseRefreshView;

/**
 * Test控制器
 *
 * @author xll
 */
class TestContract {

    interface View extends BaseRefreshView<User> {
        void loginSuccess();
    }

    interface Presenter extends BaseRefreshContract.Presenter {
        void login();
    }
}