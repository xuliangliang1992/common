package com.highlands.home.home;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.home.view.SlideRecyclerView;
import com.highlands.home.R;
import com.highlands.home.databinding.HomeFragmentBinding;
import com.highlands.common.base.fragment.BaseLazyFragment;
import com.highlands.common.constant.RouterUrl;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;


/**
 * @author xll
 * @date 20
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.HOME_FRAGMENT_HOME)
public class HomeFragment extends BaseLazyFragment<HomePresenter> implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomeFragmentBinding binding;
    private static String TAG = "HomeFragment";
    private HomeAdapter mHomeAdapter;
    private ObservableArrayList<HomeBean> mHomeBeans;
    private HomeViewModel mViewModel;

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
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // Create the observer which updates the UI.
        final Observer<List<HomeBean>> nameObserver = newName -> {
            // Update the UI, in this case, a TextView.
            mHomeAdapter.refresh(newName);
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.getHomeBeans().observe(this, nameObserver);

        binding.rvHome.setSlideClickListener(new SlideRecyclerView.SlideClickListener() {
            @Override
            public void onDelete(int position) {
                Timber.tag(TAG).d("onDelete " + position);
            }

            @Override
            public void onClick(int position) {

                Timber.tag(TAG).d("onClick " + position);
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeBeans.add(new HomeBean(111));
                mViewModel.getHomeBeans().setValue(mHomeBeans);
            }
        });
    }

    @Override
    public void initData() {
        Timber.tag(TAG).d("onActivityCreated");
        mHomeBeans = new ObservableArrayList<>();
        for (int i = 0; i < 10; i++) {
            mHomeBeans.add(new HomeBean(i));
        }
        mViewModel.getHomeBeans().setValue(mHomeBeans);
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