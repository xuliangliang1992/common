package com.icloudwhale.cloudpos.fun.main;

import android.os.Bundle;

import com.icloudwhale.cloudpos.R;
import com.iwhalecloud.common.base.BaseActivity;
import com.iwhalecloud.common.util.ActivityUtil;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("Main");
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = MainFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }

}