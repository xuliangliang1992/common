package com.highlands.tianFuFinance.fun.login;

import com.highlands.common.base.BaseView;

/**
 * Main控制器
 *
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
class LoginContract {

    interface View extends BaseView {


    }

    interface Presenter {

        /**
         * 账号密码登录
         *
         * @param phone    手机号
         * @param password 密码
         */
        void accountLogin(String phone, String password);

        /**
         * 验证码登录
         *
         * @param phone 手机号
         * @param code  验证码
         */
        void codeLogin(String phone, String code);

        /**
         * 获取验证码
         *
         * @param phone 手机号
         */
        void getCode(String phone);
    }
}