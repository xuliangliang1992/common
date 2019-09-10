package com.icloudwhale.cloudpos.base;

import androidx.annotation.DrawableRes;

/**
 * 无数据
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface INoDataView {
    /**
     * 显示无数据View
     */
    void showNoDataView();

    /**
     * 隐藏无数据View
     */
    void hideNoDataView();

    /**
     * 显示指定资源的无数据View
     *
     * @param resId 图片id
     */
    void showNoDataView(@DrawableRes int resId);
}
