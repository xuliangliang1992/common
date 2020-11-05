package com.highlands.home.home;

import android.os.Bundle;

import com.highlands.common.util.ActivityUtil;
import com.highlands.home.R;
import com.umeng.analytics.MobclickAgent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author xuliangliang
 * @date 20
 * copyright(c) Highlands
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentation);
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.content);
        if (null == fragment) {
            fragment = HomeFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}