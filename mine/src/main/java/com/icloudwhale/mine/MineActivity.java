package com.icloudwhale.mine;

import android.os.Bundle;

import com.iwhalecloud.common.base.BaseActivity;
import com.iwhalecloud.common.util.ActivityUtil;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class MineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MineFragment fragment = (MineFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = MineFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }
}