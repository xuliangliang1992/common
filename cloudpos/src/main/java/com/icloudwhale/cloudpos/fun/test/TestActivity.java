package com.icloudwhale.cloudpos.fun.test;

import android.os.Bundle;

import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.base.BaseActivity;
import com.icloudwhale.cloudpos.base.event.EventCode;
import com.icloudwhale.cloudpos.base.event.EventMessage;
import com.iwhalecloud.common.util.ActivityUtil;

import timber.log.Timber;

/**
 * @author xll
 */
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("TestActivity");
        TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = TestFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event.getCode() == EventCode.EVENT_A) {
            Timber.d(event.toString()+"  TestActivity");
        }
    }
}