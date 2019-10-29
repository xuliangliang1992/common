package com.icloudwhale.cloudpos.fun.main;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.databinding.MainFragmentBinding;
import com.iwhalecloud.common.base.fragment.BaseFragment;
import com.iwhalecloud.common.constant.RouterUrl;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class MainFragment extends BaseFragment implements MainContract.View {
    private MainContract.Presenter mPresenter;
    private MainFragmentBinding binding;
    private String[] titles;
    private BaseFragment mHomeFragment, mDiscoverFragment, mCartFragment, mMineFragment;
    private List<BaseFragment> fragments;

    static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.main_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        titles = new String[]{"首页", "发现", "购物车", "我的"};

        mHomeFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.HOME_FRAGMENT_HOME).navigation();
        mDiscoverFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.DISCOVER_FRAGMENT_DISCOVER).navigation();
        mCartFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.CART_FRAGMENT_CART).navigation();
        mMineFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.MINE_FRAGMENT_MINE).navigation();

        fragments.add(mHomeFragment);
        fragments.add(mDiscoverFragment);
        fragments.add(mCartFragment);
        fragments.add(mMineFragment);

        MainPageAdapter mAdapter = new MainPageAdapter(getFragmentManager(), fragments);
        binding.vpMain.setAdapter(mAdapter);
        binding.vpMain.setOffscreenPageLimit(3);
        binding.tlMain.setupWithViewPager(binding.vpMain, true);
        for (int i = 0; i < titles.length; i++) {
            binding.tlMain.getTabAt(i).setText(titles[i]);
        }
    }

    @Override
    public void setPresenter() {
        mPresenter = new MainPresenter(this);
    }

    public class MainPageAdapter extends FragmentStatePagerAdapter {

        private List<BaseFragment> mFragmentList;

        private MainPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.mFragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            if (mFragmentList != null) {
                return mFragmentList.size();
            }
            return -1;
        }
    }
}