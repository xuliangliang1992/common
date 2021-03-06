package com.icloudwhale.cloudpos.http;

import com.icloudwhale.cloudpos.http.response.LoginBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * 接口缓存
 *
 * @author xuliangliang
 * @date 2019-09-18
 * copyright(c) 浩鲸云计算科技股份有限公司
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
