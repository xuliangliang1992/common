package com.icloudwhale.login;

import android.view.View;

import com.iwhalecloud.common.base.fragment.BaseFragment;

/**
 * @author xuliangliang
 * @date 2019-10-21
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class LoginFragment extends BaseFragment {
    static LoginFragment newInstance() {
        return new LoginFragment();
    }

    /**
     * 设置布局
     *
     * @return 布局id
     */
    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    /**
     * 初始化控件
     * onViewCreated时调用
     *
     * @param view view
     */
    @Override
    public void initView(View view) {

    }

    /**
     * 初始化监听器
     */
    @Override
    public void initListener() {

    }

    /**
     * 初始化数据
     * onActivityCreated
     */
    @Override
    public void initData() {

    }

    /**
     * 设置presenter对象
     */
    @Override
    public void setPresenter() {

    }
}
