package com.highlands.mine.fun.history.live;

import com.highlands.common.base.adapter.BaseSingleBindingAdapter;
import com.highlands.mine.R;
import com.highlands.mine.databinding.HistoryLiveItemBinding;
import com.highlands.mine.http.response.LiveBean;

/**
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
class HistoryLiveAdapter extends BaseSingleBindingAdapter<LiveBean, HistoryLiveItemBinding> {

    @Override
    protected int getItemLayout() {
        return R.layout.history_live_item;
    }

    @Override
    protected void onBindItem(HistoryLiveItemBinding binding, int position) {
        binding.setModel(mItems.get(position));
        binding.executePendingBindings();
    }

}