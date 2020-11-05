package com.highlands.tianFuFinance.fun.login;

import com.highlands.common.base.BaseView;
import com.highlands.tianFuFinance.http.response.LoginBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

/**
 * Main控制器
 *
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
class LoginContract {

    interface View extends BaseView {

        void sendSmsSuccess(SmsSendBean smsSendBean);

        void loginSuccess(LoginBean loginBean);
    }

    interface Presenter {

        /**
         * 账号密码登录
         *
         * @param account  账号
         * @param password 密码
         */
        void accountLogin(String account, String password);

        /**
         * 验证码登录
         *
         * @param mobile 手机号
         * @param code   验证码
         */
        void mobileLogin(String mobile, String code);

        /**
         * 获取验证码
         *
         * @param phone 手机号
         */
        void sendSms(String phone);
    }
}