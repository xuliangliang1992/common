package com.highlands.tianFuFinance.fun.register;

import com.highlands.common.base.BaseView;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

/**
 * Main控制器
 *
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
class RegisterContract {

    interface View extends BaseView {

        void sendSmsSuccess(SmsSendBean smsSendBean);

        void register();

    }

    interface Presenter {

        /**
         * 注册
         *
         * @param mobile   手机号
         * @param code     验证码
         * @param password 密码
         */
        void register(String mobile, String code, String password);

        /**
         * 获取验证码
         *
         * @param phone 手机号
         */
        void sendSms(String phone);
    }
}