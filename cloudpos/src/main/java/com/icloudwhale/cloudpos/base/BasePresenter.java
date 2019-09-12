package com.icloudwhale.cloudpos.base;

import com.icloudwhale.cloudpos.http.request.LoanDataSource;
import com.icloudwhale.cloudpos.injection.Injection;
import com.iwhalecloud.common.schedulers.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xuliangliang
 * @date 2019-09-11
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BasePresenter<V extends BaseView> implements IBasePresenter {
    private CompositeDisposable mCompositeDisposable;
    protected BaseSchedulerProvider mSchedulerProvider;
    protected LoanDataSource mLoanDataSource;
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
        mLoanDataSource = Injection.provideLoanRepository();
        mSchedulerProvider = Injection.provideSchedulerProvider();
    }

    /**
     * 取消订阅
     */
    @Override
    public void unSubscriber() {
        mCompositeDisposable.clear();
    }
}
