package com.iwhalecloud.common.view.refresh;

/**
 * <下拉刷新的协议><br>
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface PullContract {
    /**
     * 手指上滑下滑的回调
     *
     * @param enable true 达到下拉刷新的距离
     */
    void onPullEnable(boolean enable);

    /**
     * 手指松开的回调
     */
    void onRefresh();

}
