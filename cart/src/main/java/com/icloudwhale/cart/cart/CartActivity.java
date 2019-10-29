package com.icloudwhale.cart.cart;

import android.os.Bundle;

import com.icloudwhale.cart.R;
import com.iwhalecloud.common.base.BaseActivity;
import com.iwhalecloud.common.util.ActivityUtil;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
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