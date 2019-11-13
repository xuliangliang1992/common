package com.iwhalecloud.common.network;

/**
 * @author xuliangliang
 * @date 2019-11-05
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public enum NetType {
    /**
     * 有网络，包括wifi gprs
     */
    AUTO,
    /**
     * wifi
     */
    WIFI,
    /**
     * 电脑 pad
     */
    CMNET,
    /**
     * 手机上网
     */
    CMWAP,
    /**
     * 无网络
     */
    NONE
}
