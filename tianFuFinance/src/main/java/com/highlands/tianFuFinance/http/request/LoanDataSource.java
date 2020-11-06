package com.highlands.tianFuFinance.http.request;

import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.LoginBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.common.http.response.VideoBean;
import com.highlands.tianFuFinance.http.response.BannerBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author xll
 * @date 2018/12/4
 */
public interface LoanDataSource {

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @param type  1 登录，2 注册, 3 找回密码
     * @return Observable
     */
    Observable<SmsSendBean> sendSms(String phone, int type);


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
     * 注册
     *
     * @param mobile   手机号
     * @param code     验证码
     * @param password 密码
     * @return Observable
     */
    Observable<LoginBean> register(String mobile, String code, String password);

    /**
     * 账号密码登录
     *
     * @param account  账号
     * @param password 密码
     * @return Observable
     */
    Observable<LoginBean> accountLogin(String account, String password);

    /**
     * 验证码登录
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return Observable
     */
    Observable<LoginBean> mobileLogin(String mobile, String code);

    /**
     * Banner List
     *
     * @return Observable
     */
    Observable<List<BannerBean>> getBannerList();
    /**
     * 最新政策解读列表
     *
     * @return Observable
     */
    Observable<List<PolicyBean>> getPolicyNews();

    /**
     * 直播预告
     *
     * @return Observable
     */
    Observable<List<LiveBean>> getLiveNotices();

    /**
     * 最新视频
     *
     * @return Observable
     */
    Observable<List<VideoBean>> getVideoNews();

}
