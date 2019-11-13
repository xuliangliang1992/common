package com.iwhalecloud.common.network;


/**
 * @author xuliangliang
 * @date 2019-11-05
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface NetChangeObserver {

   void onConnect(NetType type);

   void onDisConnect();
}
