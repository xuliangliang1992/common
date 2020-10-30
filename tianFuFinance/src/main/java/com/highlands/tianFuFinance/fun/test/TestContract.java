package com.highlands.tianFuFinance.fun.test;

import com.highlands.common.base.BaseRefreshContract;
import com.highlands.common.base.BaseRefreshView;

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