package com.icloudwhale.login;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.iwhalecloud.common.base.BaseActivity;
import com.iwhalecloud.common.util.ActivityUtil;

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
