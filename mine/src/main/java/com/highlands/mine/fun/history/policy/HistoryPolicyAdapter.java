package com.highlands.mine.fun.history.policy;

import com.highlands.common.base.adapter.BaseSingleBindingAdapter;
import com.highlands.mine.R;
import com.highlands.mine.databinding.HistoryPolicyItemBinding;
import com.highlands.mine.http.response.PolicyBean;

/**
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
class HistoryPolicyAdapter extends BaseSingleBindingAdapter<PolicyBean, HistoryPolicyItemBinding> {

    @Override
    protected int getItemLayout() {
        return R.layout.history_policy_item;
    }

    @Override
    protected void onBindItem(HistoryPolicyItemBinding binding, int position) {
        binding.setModel(mItems.get(position));
        binding.executePendingBindings();
    }

}