package com.highlands.tianFuFinance.fun.home;

import android.view.View;

import com.highlands.common.base.adapter.BaseBindingViewHolder;
import com.highlands.common.base.adapter.BaseMultiBindingAdapter;
import com.highlands.common.http.response.CategorysBean;
import com.highlands.common.http.response.HomeBean;
import com.highlands.common.http.response.LabelsBean;
import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.common.http.response.VideoBean;
import com.highlands.common.util.ShapeUtil;
import com.highlands.common.util.StringUtil;
import com.highlands.common.util.glide.GlideUtil;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.HomeBottomItemBinding;
import com.highlands.tianFuFinance.databinding.HomeFunctionItemBinding;
import com.highlands.tianFuFinance.databinding.HomeLiveItemBinding;
import com.highlands.tianFuFinance.databinding.HomePolicyItemBinding;
import com.highlands.tianFuFinance.databinding.HomeVideoItemBinding;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;

/**
 * @author xuliangliang
 * @date 2019-10-24
 * copyright(c) Highlands
 */
public class HomeAdapter extends BaseMultiBindingAdapter<HomeBean, ViewDataBinding> {

    private final static int TYPE_BANNER = 1;
    public final static int TYPE_FUNCTION = 2;
    public final static int TYPE_POLICY = 3;
    public final static int TYPE_LIVE = 4;
    public final static int TYPE_VIDEO = 5;
    public final static int TYPE_BOTTOM = 6;

    private OnHomeClickListener mHomeClickListener;

    public void setHomeClickListener(OnHomeClickListener homeClickListener) {
        mHomeClickListener = homeClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getViewType();
    }

    @Override
    protected int getItemLayout(int viewType) {
        switch (viewType) {
            case TYPE_FUNCTION:
                return R.layout.home_function_item;
            case TYPE_POLICY:
                return R.layout.home_policy_item;
            case TYPE_LIVE:
                return R.layout.home_live_item;
            case TYPE_VIDEO:
                return R.layout.home_video_item;
            case TYPE_BOTTOM:
                return R.layout.home_bottom_item;
            default:
                return -1;
        }

    }

    @Override
    protected void onBindItem(BaseBindingViewHolder<ViewDataBinding> holder, int position) {
        if (getItemViewType(position) == TYPE_FUNCTION) {
            HomeFunctionItemBinding binding = (HomeFunctionItemBinding) holder.getBinding();
            binding.executePendingBindings();
            ShapeUtil.setShapeRadius(binding.clInformation, binding.getRoot().getContext(), 12);
            ShapeUtil.setShapeRadius(binding.clAsk, binding.getRoot().getContext(), 12);
            ShapeUtil.setShapeRadius(binding.clTrain, binding.getRoot().getContext(), 12);
            ShapeUtil.setShapeRadius(binding.clShare, binding.getRoot().getContext(), 12);

            if (mHomeClickListener != null) {
                addDisposable(RxView.clicks(binding.clInformation)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(unit -> {
                            mHomeClickListener.toInformation();
                        }));
                addDisposable(RxView.clicks(binding.clAsk)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(unit -> {
                            mHomeClickListener.toAsk();
                        }));
                addDisposable(RxView.clicks(binding.clTrain)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(unit -> {
                            mHomeClickListener.toTrain();
                        }));
                addDisposable(RxView.clicks(binding.clShare)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(unit -> {
                            mHomeClickListener.toShare();
                        }));
            }

        }

        if (getItemViewType(position) == TYPE_POLICY) {
            HomePolicyItemBinding binding = (HomePolicyItemBinding) holder.getBinding();
            PolicyBean policyBean = (PolicyBean) mItems.get(position);
            binding.setModel(policyBean);
            binding.executePendingBindings();

            GlideUtil.loadImage(binding.getRoot().getContext(), policyBean.getLecturerInfo().getAvatar(), binding.ivPolicyHead);
            GlideUtil.loadImage(binding.getRoot().getContext(), policyBean.getImageUrl(), binding.ivPolicy);
        }

        if (getItemViewType(position) == TYPE_LIVE) {
            HomeLiveItemBinding binding = (HomeLiveItemBinding) holder.getBinding();
            LiveBean liveBean = (LiveBean) mItems.get(position);
            binding.setModel(liveBean);
            binding.executePendingBindings();
            ShapeUtil.setShape(binding.tvNotice, binding.getRoot().getContext(), 20, R.color.yellow_FFBC1F);
            GlideUtil.loadImage(binding.getRoot().getContext(), liveBean.getLecturerInfo().getAvatar(), binding.ivLiveHead);
            GlideUtil.loadImage(binding.getRoot().getContext(), liveBean.getCover(), binding.ivLive);

        }

        if (getItemViewType(position) == TYPE_VIDEO) {
            HomeVideoItemBinding binding = (HomeVideoItemBinding) holder.getBinding();
            VideoBean videoBean = (VideoBean) mItems.get(position);
            binding.setModel(videoBean);
            binding.executePendingBindings();

            GlideUtil.loadImage(binding.getRoot().getContext(), videoBean.getLecturerInfo().getAvatar(), binding.ivVideoHead);
            GlideUtil.loadImage(binding.getRoot().getContext(), videoBean.getCoverUrl(), binding.ivVideo);

            List<CategorysBean> categorysBeans = videoBean.getCategorys();
            if (categorysBeans != null && categorysBeans.size() > 0) {
                binding.tvCategory.setText(StringUtil.emptyIs(categorysBeans.get(0).getTitle()));
                binding.tvCategory.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.yellow_FEA104));
                ShapeUtil.setShape(binding.tvCategory, binding.getRoot().getContext(), 9, R.color.yellow_FFEFD4);
                binding.tvCategory.setVisibility(View.VISIBLE);
            } else {
                binding.tvCategory.setVisibility(View.GONE);
            }
            List<LabelsBean> labelsBeans = videoBean.getLabels();
            if (labelsBeans != null && labelsBeans.size() > 0) {
                binding.tvLabel.setText(StringUtil.emptyIs(labelsBeans.get(0).getName()));
                binding.tvLabel.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.green_00AA7B));
                ShapeUtil.setShape(binding.tvLabel, binding.getRoot().getContext(), 9, R.color.green_D1FFF2);
                binding.tvLabel.setVisibility(View.VISIBLE);
            } else {
                binding.tvLabel.setVisibility(View.GONE);
            }
        }

        if (getItemViewType(position) == TYPE_BOTTOM) {
            HomeBottomItemBinding binding = (HomeBottomItemBinding) holder.getBinding();
            binding.executePendingBindings();
        }

        if (mHomeClickListener != null) {
            addDisposable(RxView.clicks(holder.itemView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(unit -> {
                        switch (getItemViewType(position)) {
                            case TYPE_POLICY:
                                mHomeClickListener.toPolicyDetail((PolicyBean) mItems.get(position), position);
                                break;
                            case TYPE_VIDEO:
                                mHomeClickListener.toVideoDetail((VideoBean) mItems.get(position), position);
                                break;
                            case TYPE_LIVE:
                                mHomeClickListener.toLiveDetail((LiveBean) mItems.get(position), position);
                                break;
                            default:

                                break;
                        }
                    }));
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
