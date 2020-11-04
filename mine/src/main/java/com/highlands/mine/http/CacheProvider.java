package com.highlands.mine.http;


import com.highlands.mine.http.response.LoginBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * 接口缓存
 *
 * @author xuliangliang
 * @date 2019-09-18
 * copyright(c) Highlands
 */
public interface CacheProvider {

    /**
     * 登录
     *
     * @param loginBeanObservable retrofit返回的Observable
     * @return Observable<Reply < LoginBean>>
     */
    @LifeCache(duration = 20, timeUnit = TimeUnit.SECONDS)
    Observable<Reply<LoginBean>> login(Observable<LoginBean> loginBeanObservable);
}
