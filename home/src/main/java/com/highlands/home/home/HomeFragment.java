package com.highlands.home.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionFragment;
import com.highlands.home.R;
import com.highlands.home.databinding.HomeFragmentBinding;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;


/**
 * @author xll
 * @date 20
 * copyright(c) Highlands
 */
//@Route(path = RouterUrl.HOME_FRAGMENT_HOME)
public class HomeFragment extends SimpleImmersionFragment {
    private HomeFragmentBinding binding;

    static HomeFragment newInstance() {
        return new HomeFragment();
    }


    public void initView(View view) {
        binding = DataBindingUtil.bind(view);

        initBanner();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }
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
                .setIndicator(new CircleIndicator(getActivity()))//设置指示器
                .setOnBannerListener((data, position) -> {
                })
                .isAutoLoop(true)
                .setBannerRound2(5)
                .setUserInputEnabled(true)
                .setOrientation(Banner.HORIZONTAL)
                .start();


    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .init();
    }

}