package com.icloudwhale.cart.fun.cart;

import com.icloudwhale.cart.R;
import com.icloudwhale.cart.databinding.CartChildItemBinding;
import com.icloudwhale.cart.databinding.CartGroupItemBinding;
import com.icloudwhale.cart.http.response.CartBean;
import com.icloudwhale.cart.http.response.GoodBean;
import com.iwhalecloud.common.base.adapter.BaseBindingViewHolder;
import com.iwhalecloud.common.base.adapter.BaseExpandRecyclerAdapter;
import com.iwhalecloud.common.http.response.DataTree;

import java.util.List;

import androidx.databinding.ViewDataBinding;

/**
 * @author xuliangliang
 * @date 2019-10-28
 * copyright(c) 浩鲸云计算科技股份有限公司
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
