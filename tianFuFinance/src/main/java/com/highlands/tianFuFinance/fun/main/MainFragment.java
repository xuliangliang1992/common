package com.highlands.tianFuFinance.fun.main;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.highlands.common.base.adapter.MainPageAdapter;
import com.highlands.common.base.fragment.BaseFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.MainFragmentBinding;
import com.highlands.tianFuFinance.databinding.MainTabItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;

/**
 * @author xll
 * @date 2020-11-01
 * copyright(c) Highlands
 */
public class MainFragment extends BaseFragment {
    private MainFragmentBinding binding;
    private String[] titles;
    private int[] imgRes;
    private BaseFragment mHomeFragment, mColumnFragment, mDiscoverFragment, mCartFragment, mMineFragment;
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
        titles = new String[]{"首页", "资讯", "培训", "咨询", "我的"};
        imgRes = new int[]{R.drawable.home, R.drawable.selector_main_tab_information,
                R.drawable.selector_main_tab_train, R.drawable.selector_main_tab_ask,
                R.drawable.selector_main_tab_mine};
        mHomeFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.HOME_FRAGMENT_HOME).navigation();
        mColumnFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.INFORMATION_FRAGMENT_COLUMN).navigation();
        mDiscoverFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.INFORMATION_FRAGMENT_DISCOVER).navigation();
        mCartFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.TRAIN_FRAGMENT_CART).navigation();
        mMineFragment = (BaseFragment) ARouter.getInstance().build(RouterUrl.MINE_FRAGMENT_MINE).navigation();

        fragments.add(mHomeFragment);
        fragments.add(mColumnFragment);
        fragments.add(mDiscoverFragment);
        fragments.add(mCartFragment);
        fragments.add(mMineFragment);

        MainPageAdapter mAdapter = new MainPageAdapter(getFragmentManager(), fragments);
        binding.vpMain.setAdapter(mAdapter);
        binding.vpMain.setOffscreenPageLimit(3);
        binding.tlMain.setupWithViewPager(binding.vpMain, true);
        for (int i = 0; i < titles.length; i++) {
            binding.tlMain.getTabAt(i).setCustomView(getTabView(i));
        }
    }

    @Override
    public void setPresenter() {
    }

    private View getTabView(int position) {
        if (position == 0) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setImageResource(imgRes[position]);
            return imageView;
        }
        MainTabItemBinding tabItemBinding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.main_tab_item, binding.tlMain, false);
        tabItemBinding.imgTitle.setImageResource(imgRes[position]);
        tabItemBinding.txtTitle.setText(titles[position]);
        return tabItemBinding.getRoot();
    }

}