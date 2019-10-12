package com.iwhalecloud.common.base.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class BaseBindingViewHolder<B> extends RecyclerView.ViewHolder {
    private B binding;

    public BaseBindingViewHolder(View itemView) {
        super(itemView);
    }

    public B getBinding() {
        return binding;
    }

    public void setBinding(B binding) {
        this.binding = binding;
    }
}
