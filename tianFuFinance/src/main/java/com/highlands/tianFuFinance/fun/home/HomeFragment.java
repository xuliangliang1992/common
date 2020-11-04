package com.highlands.tianFuFinance.fun.home;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.highlands.common.base.fragment.BaseLazyFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.util.SystemUtil;
import com.highlands.common.util.ToastUtil;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.HomeFragmentBinding;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import timber.log.Timber;

/**
 * @author xll
 * @date 2020-11-01
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.HOME_FRAGMENT_HOME)
public class HomeFragment extends BaseLazyFragment<HomePresenter> implements HomeContract.View/*,SimpleImmersionOwner*/ {
    private HomeFragmentBinding binding;
    private static String TAG = "HomeFragment";
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

        GradientDrawable mGroupDrawable = (GradientDrawable) binding.tvNotice.getBackground();
        mGroupDrawable.setCornerRadius(SystemUtil.dip2px(mActivity, 20));
        initBanner();
    }

    @Override
    public void initListener() {
        Timber.tag(TAG).d("onViewCreated initListener");
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // Create the observer which updates the UI.
        final Observer<List<HomeBean>> nameObserver = newName -> {
            // Update the UI, in this case, a TextView.
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.getHomeBeans().observe(this, nameObserver);

        //        binding.rvHome.setSlideClickListener(new SlideRecyclerView.SlideClickListener() {
        //            @Override
        //            public void onDelete(int position) {
        //                Timber.tag(TAG).d("onDelete " + position);
        //            }
        //
        //            @Override
        //            public void onClick(int position) {
        //
        //                Timber.tag(TAG).d("onClick " + position);
        //            }
        //        });

        //        binding.button.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                mHomeBeans.add(new HomeBean(111));
        //                mViewModel.getHomeBeans().setValue(mHomeBeans);
        //            }
        //        });
    }

    @Override
    public void initData() {
        Timber.tag(TAG).d("onActivityCreated");
        mHomeBeans = new ObservableArrayList<>();
        for (int i = 0; i < 100; i++) {
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

    @SuppressWarnings("unchecked")
    private void initBanner() {
        List<String> mImages = new ArrayList<>();
        mImages.add("http://172.31.225.218/zentao/file-read-34542.png");
        mImages.add("http://172.31.225.218/zentao/file-read-33569.png");
        mImages.add("http://172.31.225.218/zentao/file-read-34542.png");
        binding.banner.setAdapter(new BannerImageAdapter<String>(mImages) {
            @Override
            public void onBindView(BannerImageHolder holder, String url, int position, int size) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(url)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(mActivity))//设置指示器
                .setOnBannerListener((data, position) -> {
                    ToastUtil.showToast(mActivity, "点击了banner" + position);
                })
                .isAutoLoop(true)
                .setBannerRound2(5)
                .setUserInputEnabled(true)
                .setOrientation(Banner.HORIZONTAL)
                .start();


    }

    //    @Override
    //    public void initImmersionBar() {
    //        ImmersionBar.with(this).statusBarColorTransformEnable(false)
    //                .keyboardEnable(false)
    //                .navigationBarColor(R.color.colorPrimary)
    //                .init();
    //    }


}