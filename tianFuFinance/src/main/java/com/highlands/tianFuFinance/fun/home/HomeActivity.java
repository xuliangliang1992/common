package com.highlands.tianFuFinance.fun.home;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.util.ActivityUtil;
import com.highlands.tianFuFinance.R;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
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


        ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(false).navigationBarColor(R.color.colorPrimary).init();
    }
}