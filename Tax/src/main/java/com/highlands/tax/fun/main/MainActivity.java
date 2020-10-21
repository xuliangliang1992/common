package com.highlands.tax.fun.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.common.constant.RouterUrl;
import com.highlands.tax.R;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.network.NetChangeObserver;
import com.highlands.common.network.NetType;
import com.highlands.common.network.NetWorkManager;
import com.highlands.common.util.ActivityUtil;

import timber.log.Timber;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.HAND_MAIN)
public class MainActivity extends BaseActivity implements NetChangeObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = MainFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        NetWorkManager.getInstance().setNetChangeObserver(this);
    }

    @Override
    public void onConnect(NetType type) {
        Timber.d("onConnect" + type.name());
    }

    @Override
    public void onDisConnect() {
        Timber.d("onDisConnect");

    }
}