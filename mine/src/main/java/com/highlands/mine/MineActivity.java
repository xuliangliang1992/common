package com.highlands.mine;

import android.os.Bundle;

import com.highlands.common.base.BaseActivity;
import com.highlands.common.util.ActivityUtil;
import com.highlands.mine.R;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
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