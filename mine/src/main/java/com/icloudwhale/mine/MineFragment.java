package com.icloudwhale.mine;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.icloudwhale.mine.databinding.MineFragmentBinding;
import com.iwhalecloud.common.base.fragment.BaseLazyFragment;
import com.iwhalecloud.common.constant.RouterUrl;

import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Route(path = RouterUrl.MINE_FRAGMENT_MINE)
public class MineFragment extends BaseLazyFragment<MinePresenter> implements MineContract.View {
    private MineContract.Presenter mPresenter;
    private MineFragmentBinding binding;
    private static String TAG = "MineFragment";

    static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
        Timber.tag(TAG).d("onViewCreated initView");
    }

    @Override
    public void initListener() {
        Timber.tag(TAG).d("onViewCreated initListener");
    }

    @Override
    public void initData() {
        Timber.tag(TAG).d("onActivityCreated");
    }

    @Override
    public void setPresenter() {
        mPresenter = new MinePresenter(this);
    }

    @Override
    public void onLazyLoad() {
        Timber.tag(TAG).d("onResume");
    }
}