package com.icloudwhale.cloudpos.http.request;


import com.icloudwhale.cloudpos.http.AppRetrofit;
import com.icloudwhale.cloudpos.http.HttpFilterFunc;
import com.icloudwhale.cloudpos.http.HttpMapToBean;
import com.icloudwhale.cloudpos.http.HttpUrl;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import io.reactivex.Observable;

/**
 * @author xll
 * @date 2018/12/4
 */
public class RemoteLoanDataSource implements LoanDataSource {
    @Nullable
    private static RemoteLoanDataSource INSTANCE;

    private RemoteLoanDataSource() {
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

}