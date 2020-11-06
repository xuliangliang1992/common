package com.highlands.tianFuFinance.fun.home;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;
import com.highlands.common.base.fragment.BaseMvpFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.http.response.HomeBean;
import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.common.http.response.VideoBean;
import com.highlands.common.util.ToastUtil;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.HomeFragmentBinding;
import com.highlands.tianFuFinance.http.response.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.RectangleIndicator;

import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;

/**
 * @author xll
 * @date 2020-11-01
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.HOME_FRAGMENT_HOME)
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View, SimpleImmersionOwner, View.OnClickListener {
    private HomeFragmentBinding binding;
    private ObservableArrayList<HomeBean> mHomeBeans;
    private HomeViewModel mViewModel;
    private HomeAdapter mHomeAdapter;

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
        mHomeAdapter = new HomeAdapter();
        binding.rvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        //        ShapeUtil.setShape(binding.tvNotice, mActivity, 20, R.color.yellow_FFBC1F);
        //        ShapeUtil.setShape(binding.tvSearch, mActivity, 20, R.color.gray_646968);
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

        mHomeAdapter.setHomeClickListener(new OnHomeClickListener() {
            @Override
            public void toInformation() {
                showToast("此版本不支持该功能");
            }

            @Override
            public void toAsk() {
                showToast("此版本不支持该功能");
            }

            @Override
            public void toTrain() {
                showToast("此版本不支持该功能");
            }

            @Override
            public void toShare() {
                showToast("此版本不支持该功能");
            }

            @Override
            public void toPolicyDetail(PolicyBean policyBean, int position) {
                showToast("toPolicy");
            }

            @Override
            public void toLiveDetail(LiveBean liveBean, int position) {
                showToast("toLive");
            }

            @Override
            public void toVideoDetail(VideoBean videoBean, int position) {
                showToast("toVideo");
            }
        });
    }

    @Override
    public void initData() {
        mHomeBeans = new ObservableArrayList<>();
        mHomeBeans.add(new HomeBean(HomeAdapter.TYPE_FUNCTION));
        mHomeBeans.add(new HomeBean(HomeAdapter.TYPE_BOTTOM));
        mViewModel.getHomeBeans().setValue(mHomeBeans);

        mActivity.showInitLoadView();
        mPresenter.getBannerList();
        mPresenter.getPolicyNews();
        mPresenter.getLiveNotices();
        mPresenter.getVideoNews();
    }

    @Override
    public void setPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(binding.toolBar)
                .navigationBarColor(R.color.colorPrimary)
                .keyboardEnable(false)
                .init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeAdapter.destroy();
    }

    @SuppressWarnings("ALL")
    @Override
    public void getBanner(List<BannerBean> bannerBeans) {
        binding.banner.setAdapter(new BannerImageAdapter<BannerBean>(bannerBeans) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerBean bannerBean, int position, int size) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(bannerBean.getFileUrl())
                        .into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new RectangleIndicator(mActivity))//设置指示器
                .setOnBannerListener((data, position) -> {
                    ToastUtil.showToast(mActivity, "点击了banner" + position);
                })
                .isAutoLoop(true)
                .setBannerRound2(5)
                .setUserInputEnabled(false)
                .setOrientation(Banner.HORIZONTAL)
                .start();
    }

    @Override
    public void getPolicyNews(List<PolicyBean> policyBeans) {
        hideLoading();
        mHomeBeans.addAll(mHomeBeans.size() - 1, policyBeans);
        loadData();
    }

    @Override
    public void getLiveNotices(List<LiveBean> liveBeans) {
        mHomeBeans.addAll(mHomeBeans.size() - 1, liveBeans);
        loadData();
    }

    @Override
    public void getVideoNews(List<VideoBean> videoBeans) {
        mHomeBeans.addAll(mHomeBeans.size() - 1, videoBeans);
        loadData();
    }

    private void loadData() {
        mViewModel.getHomeBeans().setValue(mHomeBeans);
        Collections.sort(mHomeBeans, (o1, o2) -> o1.getViewType() - o2.getViewType());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_search) {
            showToast("此版本不支持该功能");
        }
        if (v.getId() == R.id.iv_cus_service) {
            showToast("此版本不支持该功能");
        }
    }
}