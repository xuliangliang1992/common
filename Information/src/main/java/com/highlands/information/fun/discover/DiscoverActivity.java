package com.highlands.information.fun.discover;

import android.os.Bundle;

import com.highlands.information.R;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.util.ActivityUtil;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
 */
public class DiscoverActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DiscoverFragment fragment = (DiscoverFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = DiscoverFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }
}