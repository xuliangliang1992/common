package com.highlands.tianFuFinance.http.request;

import com.highlands.tianFuFinance.base.MainApplication;
import com.highlands.tianFuFinance.constant.Constant;
import com.highlands.tianFuFinance.http.CacheProvider;
import com.highlands.tianFuFinance.http.HttpUrl;
import com.highlands.tianFuFinance.http.RetrofitUtil;
import com.highlands.tianFuFinance.http.response.LoginBean;
import com.highlands.common.http.HttpFilterFunc;
import com.highlands.common.http.HttpMapToBean;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

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
    public Observable<String> getAccessToken(long userId, long shopId, String dbName) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("dbName", dbName);
        params.put("shopId", shopId);
        params.put("userId", userId);
        return mRetrofitUtil.getFaceIDService(false).getAccessToken(HttpUrl.ACCESS_TOKEN_URL, params)
                .filter(new HttpFilterFunc<>())
                .map(new HttpMapToBean<>());
    }

    /**
     * 登录
     *
     * @param userName 账号
     * @param password 密码
     * @return Observable
     */
    @Override
    public Observable<Reply<LoginBean>> login(String userName, String password) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("login", "+86-" + userName);
        params.put("password", password);
        params.put("login_type", "password");
        return cacheProvider.login(mRetrofitUtil.getFaceIDService(Constant.LOGIN_URL).login(HttpUrl.LOGIN_URL, params));
    }
}