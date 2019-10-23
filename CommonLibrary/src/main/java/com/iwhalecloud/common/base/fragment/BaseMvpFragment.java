package com.iwhalecloud.common.base.fragment;

import android.view.View;

import com.iwhalecloud.common.base.BasePresenter;
import com.iwhalecloud.common.base.BaseView;

/**
 * @author xuliangliang
 * @date 2019-09-11
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
    protected P mPresenter;

    @Override
    protected void initCommonView(View view) {
        super.initCommonView(view);
        setPresenter();
        if (mPresenter != null) {
            mPresenter.subscriber();
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unSubscriber();
        }
        super.onDestroy();
    }

}
