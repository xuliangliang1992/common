package com.icloudwhale.cloudpos.test;


import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.databinding.TestItemBinding;
import com.iwhalecloud.common.base.adapter.BaseBindingListAdapter;

/**
 * @author xll
 */
public class TestAdapter extends BaseBindingListAdapter<User, TestItemBinding> {

    @Override
    protected int getItemLayout(int viewType) {
        return R.layout.test_item;
    }

    @Override
    protected void onBindItem(TestItemBinding binding, int position) {
        binding.setUser(mItems.get(position));
        binding.executePendingBindings();
    }

}