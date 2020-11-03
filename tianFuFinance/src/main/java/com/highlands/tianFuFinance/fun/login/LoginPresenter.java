package com.highlands.tianFuFinance.fun.login;

import com.highlands.common.base.BasePresenter;
import com.highlands.common.http.BaseXllObserver;
import com.highlands.tianFuFinance.http.request.RemoteLoanDataSource;

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
    public void accountLogin(String phone, String password) {
        RemoteLoanDataSource.getInstance().getAccessToken(1, 2, "3")
                .subscribe(new BaseXllObserver<String>(mView) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }
                });
    }

    @Override
    public void codeLogin(String phone, String code) {

    }

    @Override
    public void getCode(String phone) {

    }
}