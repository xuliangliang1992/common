package com.iwhalecloud.common.base;

import com.iwhalecloud.common.schedulers.BaseSchedulerProvider;
import com.iwhalecloud.common.schedulers.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xuliangliang
 * @date 2019-09-11
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BasePresenter<V extends BaseView> implements IBasePresenter {
    protected CompositeDisposable mCompositeDisposable;
    protected BaseSchedulerProvider mSchedulerProvider;
    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    /**
     * 订阅
     */
    @Override
    public void subscriber() {
        mCompositeDisposable = new CompositeDisposable();
        mSchedulerProvider = SchedulerProvider.getInstance();
    }

    /**
     * 取消订阅
     */
    @Override
    public void unSubscriber() {
        mCompositeDisposable.clear();
    }
}
