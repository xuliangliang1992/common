package com.icloudwhale.cloudpos.base;


import com.iwhalecloud.common.subscriber.HttpObserver;

/**
 * @author xll
 * @date 2018/1/1
 */
public interface BaseView<T> extends HttpObserver {

    /**
     * 设置presenter对象
     *
     * @param presenter
     */
    void setPresenter(T presenter);

}
