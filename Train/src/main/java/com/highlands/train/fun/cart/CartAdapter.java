package com.highlands.train.fun.cart;

import com.highlands.train.http.response.CartBean;
import com.highlands.train.http.response.GoodBean;
import com.highlands.train.R;
import com.highlands.train.databinding.CartChildItemBinding;
import com.highlands.train.databinding.CartGroupItemBinding;
import com.highlands.common.base.adapter.BaseBindingViewHolder;
import com.highlands.common.base.adapter.BaseExpandRecyclerAdapter;
import com.highlands.common.http.response.DataTree;

import java.util.List;

import androidx.databinding.ViewDataBinding;

/**
 * @author xuliangliang
 * @date 2019-10-28
 * copyright(c) Highlands
 */
public class CartAdapter extends BaseExpandRecyclerAdapter<CartBean, GoodBean, DataTree<CartBean, GoodBean>, ViewDataBinding> {

    @Override
    protected int getGroupLayout() {
        return R.layout.cart_group_item;
    }

    @Override
    protected int getChildLayout() {
        return R.layout.cart_child_item;
    }

    @Override
    protected void onBindGroupItem(BaseBindingViewHolder<ViewDataBinding> holder, int groupPosition, CartBean cartBean, List<GoodBean> goodBeans) {
        CartGroupItemBinding binding = (CartGroupItemBinding) holder.getBinding();
        binding.setCart(cartBean);
        binding.executePendingBindings();
    }

    @Override
    protected void onBindChildItem(BaseBindingViewHolder<ViewDataBinding> holder, int groupPosition, int childPosition, GoodBean goodBean) {
        CartChildItemBinding binding = (CartChildItemBinding) holder.getBinding();
        binding.setGoodBean(goodBean);
        binding.executePendingBindings();
    }


}
