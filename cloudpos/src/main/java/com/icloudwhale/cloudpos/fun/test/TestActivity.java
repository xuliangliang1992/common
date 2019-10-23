package com.icloudwhale.cloudpos.fun.test;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.icloudwhale.cloudpos.R;
import com.iwhalecloud.common.base.BaseActivity;
import com.iwhalecloud.common.base.event.EventCode;
import com.iwhalecloud.common.base.event.EventMessage;
import com.iwhalecloud.common.util.ActivityUtil;

import timber.log.Timber;

/**
 * @author xll
 */
class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("TestActivity");
        TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = TestFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        ImmersionBar.with(this)
                //                .statusBarColor(R.color.colorPrimaryDark)
                //                .navigationBarColor(R.color.colorPrimaryDark)
                .init();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event.getCode() == EventCode.EVENT_A) {
            Timber.d(event.toString() + "  TestActivity");
        }
    }
}