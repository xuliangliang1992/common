package com.icloudwhale.cloudpos.base;

import android.view.View;

import com.iwhalecloud.common.subscriber.HttpObserver;

/**
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface BaseView extends HttpObserver {

    /**
     * 设置布局
     *
     * @return 布局id
     */
    int setLayout();

    /**
     * 初始化控件
     * onViewCreated时调用
     *
     * @param view view
     */
    void initView(View view);

    /**
     * 初始化监听器
     */
    void initListener();

    /**
     * 初始化数据
     * onActivityCreated
     */
    void initData();

    /**
     * 设置presenter对象
     */
    void setPresenter();

}
