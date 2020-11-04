package com.highlands.mine.fun.test;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.base.event.EventCode;
import com.highlands.common.base.event.EventMessage;
import com.highlands.common.util.ActivityUtil;
import com.highlands.mine.R;

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