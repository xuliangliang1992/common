package com.highlands.common.subscriber;

/**
 * @author xll
 * @date 2018/1/1
 */

public interface HttpObserver {

    /**
     * 登录失效
     */
    void httpUnauthorized();

    /**
     * 请求异常
     *
     * @param code 错误码
     */
    void httpException(int code);

    /**
     * 网络超时
     */
    void httpTimeOutException();

    /**
     * 其他请求异常
     *
     * @param message 错误信息
     */
    void httpOtherException(String message);
}
