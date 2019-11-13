package com.icloudwhale.cloudpos.fun.main;

import android.os.Bundle;

import com.icloudwhale.cloudpos.R;
import com.iwhalecloud.common.base.BaseActivity;
import com.iwhalecloud.common.network.NetChangeObserver;
import com.iwhalecloud.common.network.NetType;
import com.iwhalecloud.common.network.NetWorkManager;
import com.iwhalecloud.common.util.ActivityUtil;
import com.taobao.sophix.SophixManager;

import timber.log.Timber;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class MainActivity extends BaseActivity implements NetChangeObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("Main");
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = MainFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        SophixManager.getInstance().queryAndLoadNewPatch();
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