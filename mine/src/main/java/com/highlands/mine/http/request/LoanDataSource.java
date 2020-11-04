package com.highlands.mine.http.request;


import com.highlands.mine.http.response.LoginBean;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

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
    /**
     * 登录
     *
     * @param userName 账号
     * @param password 密码
     * @return Observable
     */
    Observable<Reply<LoginBean>> login(String userName, String password);
}
