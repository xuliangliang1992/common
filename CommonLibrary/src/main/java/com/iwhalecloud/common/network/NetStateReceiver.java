package com.iwhalecloud.common.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.iwhalecloud.common.constant.BaseConstant;

import timber.log.Timber;


/**
 * @author xuliangliang
 * @date 2019-11-05
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class NetStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetStateReceiver";
    private NetType mNetType;
    private NetChangeObserver mNetChangeObserver;
    private long lastTime = 0;

    public NetStateReceiver() {
        mNetType = NetType.NONE;
    }

    public void setNetChangeObserver(NetChangeObserver netChangeObserver) {
        mNetChangeObserver = netChangeObserver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Timber.tag(TAG).e("异常！ ");
            return;
        }
        if (intent.getAction().equalsIgnoreCase(BaseConstant.ANDROID_NET_CHANGE_ACTION)) {
            long time = System.currentTimeMillis();
            if (time - lastTime > 1000) {
                Timber.tag(TAG).e("监听网络变化！ " + time +" "+ lastTime);
                mNetType = NetWorkUtil.getNetType();
                if (NetWorkUtil.isNetworkAvailable()) {
                    if (mNetChangeObserver != null) {
                        Timber.tag(TAG).e("网络连接成功！ " + mNetType.name());
                        mNetChangeObserver.onConnect(mNetType);
                    }
                } else {
                    if (mNetChangeObserver != null) {
                        Timber.tag(TAG).e("网络断开！ ");
                        mNetChangeObserver.onDisConnect();
                    }
                }
                lastTime = time;
            }
        }
    }
}
