package com.highlands.tianFuFinance.http.request;


import com.highlands.tianFuFinance.http.response.LoginBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;

/**
 * @author xll
 * @date 2018/12/4
 */
public class LoanRepository implements LoanDataSource {

    @Nullable
    private static LoanRepository INSTANCE;

    private RemoteLoanDataSource mRemoteLoanDataSource;

    private LoanRepository(RemoteLoanDataSource remoteLoanDataSource) {
        this.mRemoteLoanDataSource = remoteLoanDataSource;
    }

    public static LoanRepository getInstance(@NonNull RemoteLoanDataSource remoteLoanDataSource) {
        if (null == INSTANCE) {
            INSTANCE = new LoanRepository(remoteLoanDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<SmsSendBean> sendSms(String phone, int type) {
        return mRemoteLoanDataSource.sendSms(phone, type);
    }

    @Override
    public Observable<String> getAccessToken(long userId, long shopId, String dbName) {
        return mRemoteLoanDataSource.getAccessToken(userId, shopId, dbName);
    }

    @Override
    public Observable<LoginBean> register(String mobile, String code, String password) {
        return mRemoteLoanDataSource.register(mobile, code, password);
    }

    @Override
    public Observable<LoginBean> accountLogin(String account, String password) {
        return mRemoteLoanDataSource.accountLogin(account, password);
    }

    @Override
    public Observable<LoginBean> mobileLogin(String mobile, String code) {
        return mRemoteLoanDataSource.mobileLogin(mobile, code);
    }
}
