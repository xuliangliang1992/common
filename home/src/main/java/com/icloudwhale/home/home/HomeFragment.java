package com.icloudwhale.home.home;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.icloudwhale.home.R;
import com.icloudwhale.home.databinding.HomeFragmentBinding;
import com.iwhalecloud.common.base.fragment.BaseLazyFragment;

import androidx.databinding.DataBindingUtil;
import timber.log.Timber;


/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Route(path = "/home/fragment/home")
public class HomeFragment extends BaseLazyFragment<HomePresenter> implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomeFragmentBinding binding;
    private static String TAG = "HomeFragment";

    static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.home_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
        Timber.tag(TAG).d("onViewCreated initView");
    }

    @Override
    public void initListener() {
        Timber.tag(TAG).d("onViewCreated initListener");
    }

    @Override
    public void initData() {
        Timber.tag(TAG).d("onActivityCreated");
    }

    @Override
    public void setPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void onLazyLoad() {
        Timber.tag(TAG).d("onResume");
    }

}