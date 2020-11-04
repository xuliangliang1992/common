package com.highlands.mine.fun.history;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.common.base.BaseActivity;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.util.ActivityUtil;
import com.highlands.mine.R;

/**
 * 历史记录
 *
 * @author xuliangliang
 * @date 2020-11-03
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.MINE_ACTIVITY_HISTORY)
public class HistoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText(getString(R.string.history));
        HistoryFragment fragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = HistoryFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
    }

}