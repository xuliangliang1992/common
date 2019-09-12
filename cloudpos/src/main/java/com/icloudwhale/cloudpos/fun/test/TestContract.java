package com.icloudwhale.cloudpos.fun.test;

import com.icloudwhale.cloudpos.base.BaseRefreshContract;
import com.icloudwhale.cloudpos.base.BaseRefreshView;

/**
 * Test控制器
 *
 * @author xll
 */
class TestContract {

    interface View extends BaseRefreshView<User> {

    }

    interface Presenter extends BaseRefreshContract.Presenter {

    }
}