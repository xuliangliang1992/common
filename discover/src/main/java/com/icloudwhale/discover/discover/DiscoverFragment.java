package com.icloudwhale.discover.discover;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.icloudwhale.discover.R;
import com.icloudwhale.discover.databinding.DiscoverFragmentBinding;
import com.iwhalecloud.common.base.fragment.BaseLazyRefreshFragment;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import timber.log.Timber;

/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Route(path = "/discover/fragment/discover")
public class DiscoverFragment extends BaseLazyRefreshFragment<DiscoverBean, DiscoverPresenter> implements DiscoverContract.View {
    private DiscoverContract.Presenter mPresenter;
    private DiscoverFragmentBinding binding;
    private static String TAG = "DiscoverFragment";

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
    public void onLazyLoad() {
        Timber.tag(TAG).d("onResume");
    }

    @Override
    protected int onBindRefreshLayout() {
        return R.id.refresh_layout;
    }

    @Override
    public void refreshData(ObservableArrayList<DiscoverBean> data) {

    }

    @Override
    public void loadMoreData(ObservableArrayList<DiscoverBean> data) {

    }

    @Override
    public void onRefreshEvent() {

    }

    @Override
    public void onLoadMoreEvent() {

    }

    @Override
    public void onAutoLoadEvent() {

    }
}