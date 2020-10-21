package com.highlands.information.fun.column;

import android.os.Bundle;

import com.highlands.common.base.BaseActivity;
import com.highlands.common.util.ActivityUtil;
import com.highlands.information.R;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
 */
public class ColumnActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ColumnFragment fragment = (ColumnFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = ColumnFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

    }
}