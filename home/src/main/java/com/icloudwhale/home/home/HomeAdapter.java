package com.icloudwhale.home.home;

import com.icloudwhale.home.R;
import com.icloudwhale.home.databinding.HomeItemBinding;
import com.iwhalecloud.common.base.adapter.BaseSingleBindingAdapter;

/**
 * @author xuliangliang
 * @date 2019-10-24
 * copyright(c) 浩鲸云计算科技股份有限公司
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
