package com.highlands.tianFuFinance.fun.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.util.ActivityUtil;
import com.highlands.tianFuFinance.R;

/**
 * @author xll
 * @date 2020-11-01
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.TAX_MAIN)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = MainFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }

    @Override
    protected boolean isRegisteredNetChange() {
        return true;
    }
}