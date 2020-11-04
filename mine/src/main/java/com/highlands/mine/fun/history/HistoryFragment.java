package com.highlands.mine.fun.history;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.highlands.common.base.adapter.MainPageAdapter;
import com.highlands.common.base.fragment.BaseFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.mine.R;
import com.highlands.mine.databinding.ViewPageFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;

/**
 * @author xll
 */
class HistoryFragment extends BaseFragment {
    private ViewPageFragmentBinding mBinding;
    private String[] titles;
    private BaseFragment mHistoryPolicyFragment, mHistoryVideoFragment, mHistoryLiveFragment;
    private List<BaseFragment> fragments;

    static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.view_page_fragment;
    }

    @Override
    public void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
    }


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        titles = new String[]{mActivity.getString(R.string.policy), mActivity.getString(R.string.video), mActivity.getString(R.string.living)};
        mHistoryPolicyFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.MINE_FRAGMENT_HISTORY_POLICY).navigation();
        mHistoryVideoFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.MINE_FRAGMENT_HISTORY_VIDEO).navigation();
        mHistoryLiveFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.MINE_FRAGMENT_HISTORY_LIVE).navigation();
//
        fragments.add(mHistoryPolicyFragment);
        fragments.add(mHistoryVideoFragment);
        fragments.add(mHistoryLiveFragment);
//
        MainPageAdapter mAdapter = new MainPageAdapter(getFragmentManager(), fragments);
        mBinding.vp.setAdapter(mAdapter);
        mBinding.vp.setOffscreenPageLimit(3);
        mBinding.tl.setupWithViewPager(mBinding.vp, true);
        for (int i = 0; i < titles.length; i++) {
            mBinding.tl.getTabAt(i).setText(titles[i]);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置presenter对象
     */
    @Override
    public void setPresenter() {
    }


}