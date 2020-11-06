package com.highlands.tianFuFinance.http.request;

import com.highlands.common.util.RxJavaUtil;
import com.highlands.tianFuFinance.base.MainApplication;
import com.highlands.tianFuFinance.constant.Constant;
import com.highlands.tianFuFinance.http.CacheProvider;
import com.highlands.tianFuFinance.http.HttpUrl;
import com.highlands.tianFuFinance.http.RetrofitUtil;
import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.LoginBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.tianFuFinance.http.response.BannerBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;
import com.highlands.common.http.response.VideoBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import timber.log.Timber;

/**
 * @author xll
 * @date 2018/12/4
 */
public class RemoteLoanDataSource implements LoanDataSource {
    @Nullable
    private static RemoteLoanDataSource INSTANCE;
    private RetrofitUtil mRetrofitUtil;
    private final CacheProvider cacheProvider;

    private RemoteLoanDataSource() {
        cacheProvider = new RxCache.Builder()
                .persistence(MainApplication.getInstance().getFilesDir(), new GsonSpeaker())
                .using(CacheProvider.class);
        mRetrofitUtil = new RetrofitUtil();
    }

    public static RemoteLoanDataSource getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new RemoteLoanDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<SmsSendBean> sendSms(String phone, int type) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("phone", phone);
        params.put("type", type);
        Timber.tag("sendSms").d(phone);
        return mRetrofitUtil.getFaceIDService(false).sendSms(HttpUrl.SMS_SEND_URL, params)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<String> getAccessToken(long userId, long shopId, String dbName) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("dbName", dbName);
        params.put("shopId", shopId);
        params.put("userId", userId);
        return mRetrofitUtil.getFaceIDService(false).getAccessToken(HttpUrl.ACCESS_TOKEN_URL, params)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<LoginBean> register(String mobile, String code, String password) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("passwd", password);
        return mRetrofitUtil.getFaceIDService(false).register(HttpUrl.REGISTER_URL, params)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<LoginBean> accountLogin(String account, String password) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("passwd", password);
        params.put("account", account);
        return mRetrofitUtil.getFaceIDService(Constant.LOGIN_URL).accountLogin(HttpUrl.ACCOUNT_LOGIN_URL, params)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<LoginBean> mobileLogin(String mobile, String code) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("mobile", mobile);
        params.put("code", code);
        return mRetrofitUtil.getFaceIDService(false).mobileLogin(HttpUrl.MOBILE_LOGIN_URL, params)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<List<BannerBean>> getBannerList() {
        return mRetrofitUtil.getFaceIDService(false).getBannerList(HttpUrl.BANNER_LIST_URL)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<List<PolicyBean>> getPolicyNews() {
        return mRetrofitUtil.getFaceIDService(false).getPolicyNews(HttpUrl.POLICY_NEWS_URL, Constant.LIMIT)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<List<LiveBean>> getLiveNotices() {
        return mRetrofitUtil.getFaceIDService(false).getLiveNotices(HttpUrl.LIVE_NOTICE_URL,  Constant.LIMIT)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }

    @Override
    public Observable<List<VideoBean>> getVideoNews() {
        return mRetrofitUtil.getFaceIDService(false).getVideoNews(HttpUrl.VIDEO_NEWS_URL,  Constant.LIMIT)
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
    }
}