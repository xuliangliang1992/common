package com.highlands.login;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.util.ActivityUtil;
import com.highlands.login.R;

@Route(path = "/login/login")
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("LoginActivity");
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = LoginFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
    }
}
