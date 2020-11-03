package com.highlands.mine;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.highlands.common.base.fragment.BaseLazyFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.util.ShapeUtil;
import com.highlands.mine.databinding.MineFragmentBinding;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import timber.log.Timber;

/**
 * @author xll
 * @date 20
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.MINE_FRAGMENT_MINE)
public class MineFragment extends BaseLazyFragment<MinePresenter> implements MineContract.View, View.OnClickListener {
    private MineFragmentBinding binding;
    private static String TAG = "MineFragment";
    private MineViewModel mViewModel;

    static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
        Timber.tag(TAG).d("onViewCreated initView");
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        ShapeUtil.setShapeColor(binding.clCollect,mActivity,R.color.white_EFF4FF);
        ShapeUtil.setShapeColor(binding.clFocus,mActivity,R.color.yellow_FFF9F0);
        ShapeUtil.setShapeColor(binding.clComment,mActivity,R.color.green_F2FDFF);
        ShapeUtil.setShapeColor(binding.clSupport,mActivity,R.color.yellow_FFF7F4);
    }

    @Override
    public void initListener() {
        Timber.tag(TAG).d("onViewCreated initListener");
        binding.tvLogin.setOnClickListener(this);
        binding.tvRegister.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Timber.tag(TAG).d("onActivityCreated");
    }

    @Override
    public void setPresenter() {
        mPresenter = new MinePresenter(this);
    }

    @Override
    public void onLazyLoad() {
        Timber.tag(TAG).d("onResume");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_login){
            ARouter.getInstance().build(RouterUrl.TAX_LOGIN).navigation();
        }else if(v.getId()==R.id.tv_register){
            ARouter.getInstance().build(RouterUrl.TAX_REGISTER).navigation();
        }
    }
}