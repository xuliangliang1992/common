package com.icloudwhale.cloudpos.base;

import com.iwhalecloud.common.subscriber.HttpObserver;

/**
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface BaseView<T extends BasePresenter> extends HttpObserver {

    /**
     * 设置presenter对象
     *
     * @param presenter
     */
    void setPresenter(T presenter);

}
