package com.iwhalecloud.common.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * @author xuliangliang
 * @date 2019-11-05
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class NetWorkUtil {

    @SuppressLint("MissingPermission")
    public static boolean isNetworkAvaiable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) NetWorkManager.getInstance().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        //返回所有网络信息
        NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
        if (infos != null) {
            for (NetworkInfo info : infos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    public static NetType getNetType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) NetWorkManager.getInstance().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return NetType.NONE;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE;
        }
        int netType = networkInfo.getType();
        if (netType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (netType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }
}
