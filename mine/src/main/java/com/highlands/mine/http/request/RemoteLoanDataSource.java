package com.highlands.mine.http.request;

import com.highlands.common.base.BaseApplication;
import com.highlands.common.util.RxJavaUtil;
import com.highlands.mine.constant.Constant;
import com.highlands.mine.http.CacheProvider;
import com.highlands.mine.http.HttpUrl;
import com.highlands.mine.http.RetrofitUtil;
import com.highlands.mine.http.response.LoginBean;

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
                .persistence(BaseApplication.getInstance().getFilesDir(), new GsonSpeaker())
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
                .compose(RxJavaUtil.filterData())
                .compose(RxJavaUtil.changeSchedulers());
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