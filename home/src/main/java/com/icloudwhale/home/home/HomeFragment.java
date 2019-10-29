package com.icloudwhale.home.home;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.icloudwhale.home.R;
import com.icloudwhale.home.databinding.HomeFragmentBinding;
import com.iwhalecloud.common.base.fragment.BaseLazyFragment;
import com.iwhalecloud.common.constant.RouterUrl;
import com.icloudwhale.home.view.SlideRecyclerView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;


/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Route(path = RouterUrl.HOME_FRAGMENT_HOME)
public class HomeFragment extends BaseLazyFragment<HomePresenter> implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomeFragmentBinding binding;
    private static String TAG = "HomeFragment";
    private HomeAdapter mHomeAdapter;
    private ObservableArrayList<HomeBean> mHomeBeans;

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
        binding.rvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        mHomeAdapter = new HomeAdapter();
        binding.rvHome.setAdapter(mHomeAdapter);
    }

    @Override
    public void initListener() {
        Timber.tag(TAG).d("onViewCreated initListener");
        binding.rvHome.setSlideClickListener(new SlideRecyclerView.SlideClickListener() {
            @Override
            public void onDelete( int position) {
                Timber.tag(TAG).d("onDelete " + position);
            }

            @Override
            public void onClick(int position) {

                Timber.tag(TAG).d("onClick " + position);
            }
        });

    }

    @Override
    public void initData() {
        Timber.tag(TAG).d("onActivityCreated");
        mHomeBeans = new ObservableArrayList<>();
        for (int i = 0; i < 100; i++) {
            mHomeBeans.add(new HomeBean(i));
        }
        mHomeAdapter.refresh(mHomeBeans);
    }

    @Override
    public void setPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void onLazyLoad() {
        Timber.tag(TAG).d("onLazyLoad");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG).d("onResume");
    }
}