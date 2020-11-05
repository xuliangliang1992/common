package com.highlands.tianFuFinance.fun.login;

import com.highlands.common.base.BasePresenter;
import com.highlands.common.http.BaseXllObserver;
import com.highlands.tianFuFinance.http.request.RemoteLoanDataSource;
import com.highlands.tianFuFinance.http.response.LoginBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void accountLogin(String account, String password) {
        RemoteLoanDataSource.getInstance().accountLogin(account, password)
                .subscribe(new BaseXllObserver<LoginBean>(mView) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                    }
                });
    }

    @Override
    public void mobileLogin(String phone, String code) {
        RemoteLoanDataSource.getInstance().mobileLogin(phone, code)
                .subscribe(new BaseXllObserver<LoginBean>(mView) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
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