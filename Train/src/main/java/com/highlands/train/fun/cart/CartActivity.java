package com.highlands.train.fun.cart;

import android.os.Bundle;

import com.highlands.train.R;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.util.ActivityUtil;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
 */
public class CartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CartFragment fragment = (CartFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = CartFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }
}