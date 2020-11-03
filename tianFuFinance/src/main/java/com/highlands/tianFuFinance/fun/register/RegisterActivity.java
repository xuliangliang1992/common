package com.highlands.tianFuFinance.fun.register;

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
@Route(path = RouterUrl.TAX_REGISTER)
public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("注册");
        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = RegisterFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
    }

}