package com.highlands.tax.fun.splash;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.network.NetChangeObserver;
import com.highlands.common.network.NetType;
import com.highlands.common.network.NetWorkManager;
import com.highlands.common.util.ActivityUtil;
import com.highlands.tax.R;

import timber.log.Timber;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.TAX_SPLASH)
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashFragment fragment = (SplashFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = SplashFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        NetWorkManager.getInstance().setNetChangeObserver(this);
    }


}