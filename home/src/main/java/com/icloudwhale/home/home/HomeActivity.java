package com.icloudwhale.home.home;

import android.os.Bundle;

import com.icloudwhale.home.R;
import com.iwhalecloud.common.base.BaseActivity;
import com.iwhalecloud.common.util.ActivityUtil;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = HomeFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }
}