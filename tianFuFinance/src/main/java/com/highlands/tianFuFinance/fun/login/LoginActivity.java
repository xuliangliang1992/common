package com.highlands.tianFuFinance.fun.login;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.util.ActivityUtil;
import com.highlands.tianFuFinance.R;

/**
 * @author xuliangliang
 * @date 2020-11-02
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.TAX_LOGIN)
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("登录");
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = LoginFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
    }

}