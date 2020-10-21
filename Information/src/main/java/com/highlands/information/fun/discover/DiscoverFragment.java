package com.highlands.information.fun.discover;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.information.R;
import com.highlands.information.databinding.DiscoverFragmentBinding;
import com.highlands.common.base.fragment.BaseMvpFragment;
import com.highlands.common.constant.RouterUrl;

import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

/**
 * @author xll
 * @date 20
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.INFORMATION_FRAGMENT_DISCOVER)
public class DiscoverFragment extends BaseMvpFragment<DiscoverPresenter> implements DiscoverContract.View {
    private DiscoverContract.Presenter mPresenter;
    private DiscoverFragmentBinding binding;

    static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.discover_fragment;
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
        mPresenter = new DiscoverPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG).d("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG).d("onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.tag(TAG).d("onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.tag(TAG).d("onStop");
    }
}