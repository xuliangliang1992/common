package com.highlands.mine.fun.test;

import com.highlands.common.base.adapter.BaseSingleBindingAdapter;
import com.highlands.mine.R;
import com.highlands.mine.databinding.TestItemBinding;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

/**
 * @author xll
 */
class TestAdapter extends BaseSingleBindingAdapter<User, TestItemBinding> {

    @Override
    protected int getItemLayout() {
        return R.layout.test_item;
    }

    @Override
    protected void onBindItem(TestItemBinding binding, int position) {
        binding.setUser(mItems.get(position));
        binding.executePendingBindings();
        if (mItemClickListener != null) {
            addDisposable(RxView.clicks(binding.getRoot())
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(o -> mItemClickListener.onItemClick(mItems.get(position), position)));
        }
        if (mOnItemLongClickListener != null) {
            addDisposable(RxView.longClicks(binding.getRoot())
                    .subscribe(o -> mOnItemLongClickListener.onItemLongClick(mItems.get(position), position)));
        }
    }




}