package com.iwhalecloud.common.base.adapter.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zmy on 2018/1/19.
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
