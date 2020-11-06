package com.highlands.tianFuFinance.http;

/**
 * 接口url
 *
 * @author xll
 * @date 2018/12/4
 */
public class HttpUrl {

    /**
     * 获取token
     */
    public static final String ACCESS_TOKEN_URL = "auth/getAccessToken";
    /**
     * 短信验证码发送
     */
    public static final String SMS_SEND_URL = "common/sms/send";
    /**
     * 注册
     */
    public static final String REGISTER_URL = "api/user/register";
    /**
     * 账号密码登录
     */
    public static final String ACCOUNT_LOGIN_URL = "api/user/pwd/login";

    /**
     * 验证码登录
     */
    public static final String MOBILE_LOGIN_URL = "api/user/mobile/login";


    /**
     * Banner
     */
    public static final String BANNER_LIST_URL = "api/app/banner/list";
    /**
     * 最新政策解读列表
     */
    public static final String POLICY_NEWS_URL = "api/app/policy/news";
    /**
     * 直播预告
     */
    public static final String LIVE_NOTICE_URL = "api/app/live/notice";
    /**
     * 最新视频
     */
    public static final String VIDEO_NEWS_URL = "api/app/video/news";

}
