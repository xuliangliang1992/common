package com.highlands.tianFuFinance.fun.home;

import com.highlands.common.base.adapter.BaseBindingViewHolder;
import com.highlands.common.base.adapter.BaseMultiBindingAdapter;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.HomeItemBinding;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

/**
 * @author xuliangliang
 * @date 2019-10-24
 * copyright(c) Highlands
 */
public class HomeAdapter extends BaseMultiBindingAdapter<HomeBean, HomeItemBinding> {

    private final static int TYPE_NOTICE_TITLE = 1;
    private final static int TYPE_EMPTY = 2;
    private final static int TYPE_COMMUNITY_TITLE = 3;
    private final static int TYPE_NOTICE = 4;
    private final static int TYPE_ACTIVITY = 5;
    private final static int TYPE_COMMUNITY = 6;

    @Override
    public int getItemViewType(int position) {
//        if (mItems.get(position).isType()) {
//            return 1;
//        }
        return 2;
    }

    @Override
    protected int getItemLayout(int viewType) {
//        if (viewType == 2) {
//            return R.layout.test_item2;
//        }
        return R.layout.home_item;
    }

    @Override
    protected void onBindItem(BaseBindingViewHolder<HomeItemBinding> holder, int position) {
        if (getItemViewType(position) == 1) {
            HomeItemBinding binding = (HomeItemBinding) holder.getBinding();
            binding.setModel(mItems.get(position));
            binding.executePendingBindings();
            if (mItemClickListener != null) {
                addDisposable(RxView.clicks(holder.itemView)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(o -> mItemClickListener.onItemClick(mItems.get(position), position)));
            }
            if (mOnItemLongClickListener != null) {
                addDisposable(RxView.longClicks(holder.itemView)
                        .subscribe(o -> mOnItemLongClickListener.onItemLongClick(mItems.get(position), position)));
            }
        }

    }
}
