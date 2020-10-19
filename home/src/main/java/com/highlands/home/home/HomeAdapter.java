package com.highlands.home.home;

import com.highlands.common.base.adapter.BaseSingleBindingAdapter;
import com.highlands.home.R;
import com.highlands.home.databinding.HomeItemBinding;

/**
 * @author xuliangliang
 * @date 2019-10-24
 * copyright(c) Highlands
 */
public class HomeAdapter extends BaseSingleBindingAdapter<HomeBean, HomeItemBinding> {
    @Override
    protected int getItemLayout() {
        return R.layout.home_item;
    }

    @Override
    protected void onBindItem(HomeItemBinding binding, int position) {
        binding.setModel(mItems.get(position));
        binding.executePendingBindings();
    }
}
