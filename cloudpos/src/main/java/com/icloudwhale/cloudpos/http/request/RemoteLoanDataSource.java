package com.icloudwhale.cloudpos.http.request;

import com.icloudwhale.cloudpos.base.MainApplication;
import com.icloudwhale.cloudpos.constant.Constant;
import com.icloudwhale.cloudpos.http.AppRetrofit;
import com.icloudwhale.cloudpos.http.CacheProvider;
import com.icloudwhale.cloudpos.http.HttpFilterFunc;
import com.icloudwhale.cloudpos.http.HttpMapToBean;
import com.icloudwhale.cloudpos.http.HttpUrl;
import com.icloudwhale.cloudpos.http.response.LoginBean;

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

    private final CacheProvider cacheProvider;

    private RemoteLoanDataSource() {
        cacheProvider = new RxCache.Builder()
                .persistence(MainApplication.getInstance().getFilesDir(), new GsonSpeaker())
                .using(CacheProvider.class);

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
        return new AppRetrofit(false).getFaceIDService().getAccessToken(HttpUrl.ACCESS_TOKEN_URL, params)
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
        return cacheProvider.login(new AppRetrofit(Constant.LOGIN_URL).getFaceIDService().login(HttpUrl.LOGIN_URL, params));
    }
}