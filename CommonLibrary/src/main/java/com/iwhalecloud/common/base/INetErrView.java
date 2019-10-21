package com.iwhalecloud.common.base;

/**
 * 网络状况
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface INetErrView {
    /**
     * 显示网络错误的View
     */
    void showNetWorkErrView();

    /**
     * 隐藏网络错误的View
     */
    void hideNetWorkErrView();
}
