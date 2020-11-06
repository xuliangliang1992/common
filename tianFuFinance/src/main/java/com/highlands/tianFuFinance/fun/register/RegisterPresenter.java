package com.highlands.tianFuFinance.fun.register;

import com.highlands.common.base.BasePresenter;
import com.highlands.common.http.BaseXllObserver;
import com.highlands.tianFuFinance.http.request.RemoteLoanDataSource;
import com.highlands.common.http.response.LoginBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String mobile, String code, String password) {
        RemoteLoanDataSource.getInstance().register(mobile, code, password)
                .subscribe(new BaseXllObserver<LoginBean>(mView) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {

                        mView.register();
                    }
                });
    }


    @Override
    public void sendSms(String phone) {
        RemoteLoanDataSource.getInstance().sendSms(phone, 2)
                .subscribe(new BaseXllObserver<SmsSendBean>(mView) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SmsSendBean smsSendBean) {
                        mView.sendSmsSuccess(smsSendBean);
                    }

                });
    }
}