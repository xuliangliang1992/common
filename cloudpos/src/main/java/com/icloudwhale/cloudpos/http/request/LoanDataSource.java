package com.icloudwhale.cloudpos.http.request;

import io.reactivex.Observable;

/**
 * @author xll
 * @date 2018/12/4
 */
public interface LoanDataSource {

    /**
     * 获取token
     *
     * @param userId 用户id
     * @param shopId 店铺id
     * @param dbName 数据库
     * @return Observable
     */
    Observable<String> getAccessToken(long userId, long shopId, String dbName);
}
