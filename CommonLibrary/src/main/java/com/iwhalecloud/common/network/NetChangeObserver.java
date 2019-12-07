package com.iwhalecloud.common.network;


/**
 * @author xuliangliang
 * @date 2019-11-05
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface NetChangeObserver {

    /**
     * 网络连接且可用
     *
     * @param type 网络类型
     */
    void onConnect(NetType type);

    /**
     * 网络断开或网络不可用
     */
    void onDisConnect();
}
