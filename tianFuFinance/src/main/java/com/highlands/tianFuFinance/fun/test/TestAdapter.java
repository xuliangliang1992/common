package com.highlands.tianFuFinance.fun.test;

import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.TestItem2Binding;
import com.highlands.tianFuFinance.databinding.TestItemBinding;
import com.highlands.common.base.adapter.BaseBindingViewHolder;
import com.highlands.common.base.adapter.BaseMultiBindingAdapter;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import androidx.databinding.ViewDataBinding;

/**
 * @author xll
 */
class TestAdapter extends BaseMultiBindingAdapter<User, ViewDataBinding> {

    @Override
    protected int getItemLayout(int viewType) {
        if (viewType == 2) {
            return R.layout.test_item2;
        }
        return R.layout.test_item;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position).isType()) {
            return 1;
        }
        return 2;
    }

    @Override
    protected void onBindItem(BaseBindingViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            TestItemBinding binding = (TestItemBinding) holder.getBinding();
            binding.setUser(mItems.get(position));
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
        } else if (getItemViewType(position) == 2) {
            TestItem2Binding binding = (TestItem2Binding) holder.getBinding();
            binding.setUser(mItems.get(position));
            binding.executePendingBindings();
        }
    }

}