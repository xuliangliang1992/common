
package com.iwhalecloud.common.view.expandablerecycleradapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public interface ViewProducer {
    int VIEW_TYPE_EMPTY = 1 << 30;
    int VIEW_TYPE_HEADER = VIEW_TYPE_EMPTY >> 1;

    /**
     * equivalent to RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)
     *
     * @param parent
     * @return
     */
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    /**
     * equivalent to RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)
     *
     * @param holder
     */
    void onBindViewHolder(RecyclerView.ViewHolder holder);

    public static class DefaultEmptyViewHolder extends RecyclerView.ViewHolder {
        public DefaultEmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
