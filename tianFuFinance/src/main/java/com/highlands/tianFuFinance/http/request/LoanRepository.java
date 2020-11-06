package com.highlands.tianFuFinance.http.request;


import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.LoginBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.tianFuFinance.http.response.BannerBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;
import com.highlands.common.http.response.VideoBean;

import java.util.List;

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

    @Override
    public Observable<List<BannerBean>> getBannerList() {
        return mRemoteLoanDataSource.getBannerList();
    }

    @Override
    public Observable<List<PolicyBean>> getPolicyNews() {
        return mRemoteLoanDataSource.getPolicyNews();
    }

    @Override
    public Observable<List<LiveBean>> getLiveNotices() {
        return mRemoteLoanDataSource.getLiveNotices();
    }

    @Override
    public Observable<List<VideoBean>> getVideoNews() {
        return mRemoteLoanDataSource.getVideoNews();
    }
}
