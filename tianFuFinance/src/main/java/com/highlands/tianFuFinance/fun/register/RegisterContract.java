package com.highlands.tianFuFinance.fun.register;

import com.highlands.common.base.BaseView;

/**
 * Main控制器
 *
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
class RegisterContract {

    interface View extends BaseView {


    }

    interface Presenter {

        /**
         * 账号密码登录
         *
         * @param phone    手机号
         * @param code     验证码
         * @param password 密码
         */
        void register(String phone, String code, String password);

        /**
         * 获取验证码
         *
         * @param phone 手机号
         */
        void getCode(String phone);
    }
}