package com.highlands.mine.fun.history.video;

import com.highlands.common.base.adapter.BaseSingleBindingAdapter;
import com.highlands.mine.R;
import com.highlands.mine.databinding.HistoryVideoItemBinding;
import com.highlands.mine.http.response.VideoBean;

/**
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
class HistoryVideoAdapter extends BaseSingleBindingAdapter<VideoBean, HistoryVideoItemBinding> {

    @Override
    protected int getItemLayout() {
        return R.layout.history_video_item;
    }

    @Override
    protected void onBindItem(HistoryVideoItemBinding binding, int position) {
        binding.setModel(mItems.get(position));
        binding.executePendingBindings();
    }

}