package com.highlands.mine.fun.mine;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.highlands.common.base.fragment.BaseLazyFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.util.ShapeUtil;
import com.highlands.common.util.SystemUtil;
import com.highlands.mine.R;
import com.highlands.mine.databinding.MineFragmentBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
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

        ShapeUtil.setShapeColor(binding.clCollect, mActivity, R.color.white_EFF4FF);
        ShapeUtil.setShapeColor(binding.clFocus, mActivity, R.color.yellow_FFF9F0);
        ShapeUtil.setShapeColor(binding.clComment, mActivity, R.color.green_F2FDFF);
        ShapeUtil.setShapeColor(binding.clSupport, mActivity, R.color.yellow_FFF7F4);
        ShapeUtil.setShapeColor(binding.clVip, mActivity, R.color.yellow_FFDB9F);
    }

    @Override
    public void initListener() {
        final Observer<Boolean> paidObserver = isPaid -> {
            ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) binding.clMiddle.getLayoutParams();
            lp.topMargin = SystemUtil.dip2px(mActivity, isPaid ? 59 : 14);
            binding.clMiddle.setLayoutParams(lp);
        };
        mViewModel.isPaid().observe(this, paidObserver);

        binding.tvLogin.setOnClickListener(this);
        binding.tvRegister.setOnClickListener(this);
        binding.tvRenew.setOnClickListener(this);
        binding.tvChat.setOnClickListener(this);
        binding.llChat.setOnClickListener(this);
        binding.tvHistory.setOnClickListener(this);
        binding.tvMail.setOnClickListener(this);
        binding.tvCusService.setOnClickListener(this);
        binding.llCustomService.setOnClickListener(this);
        binding.clCollect.setOnClickListener(this);
        binding.clFocus.setOnClickListener(this);
        binding.clComment.setOnClickListener(this);
        binding.clSupport.setOnClickListener(this);
        binding.tvLogout.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Timber.tag(TAG).d("onActivityCreated");
        mViewModel.isPaid().setValue(true);
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
        if (v.getId() == R.id.tv_login) {
            ARouter.getInstance().build(RouterUrl.TAX_LOGIN).navigation();
        } else if (v.getId() == R.id.tv_register) {
            ARouter.getInstance().build(RouterUrl.TAX_REGISTER).navigation();
        } else if (v.getId() == R.id.tv_renew) {
            mViewModel.isPaid().setValue(false);
        } else if (v.getId() == R.id.tv_chat || v.getId() == R.id.ll_chat) {
            mViewModel.isPaid().setValue(true);
        } else if (v.getId() == R.id.tv_mail) {

        } else if (v.getId() == R.id.tv_cus_service || v.getId() == R.id.ll_custom_service) {

        } else if (v.getId() == R.id.tv_logout) {

        } else if (v.getId() == R.id.tv_history) {
ARouter.getInstance().build(RouterUrl.MINE_ACTIVITY_HISTORY).navigation();
        } else if (v.getId() == R.id.cl_collect) {

        } else if (v.getId() == R.id.cl_focus) {

        } else if (v.getId() == R.id.cl_comment) {

        } else if (v.getId() == R.id.cl_support) {

        }
    }
}