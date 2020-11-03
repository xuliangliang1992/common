package com.highlands.tianFuFinance.fun.register;

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
class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String code, String password) {
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
    public void getCode(String phone) {

    }
}