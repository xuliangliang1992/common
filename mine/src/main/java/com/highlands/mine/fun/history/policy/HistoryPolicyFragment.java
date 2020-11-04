package com.highlands.mine.fun.history.policy;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.common.base.fragment.BaseRefreshFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.mine.R;
import com.highlands.mine.databinding.HistoryFragmentBinding;
import com.highlands.mine.http.response.PolicyBean;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 政策解读
 *
 * @author xll
 * @date 2020-11-03
 * copyright(c) Highlands
 */
@Route(path = RouterUrl.MINE_FRAGMENT_HISTORY_POLICY)
public class HistoryPolicyFragment extends BaseRefreshFragment<PolicyBean, HistoryPolicyPresenter> implements HistoryPolicyContract.View {
    private ObservableArrayList<PolicyBean> mBeans;
    private HistoryFragmentBinding mBinding;
    private HistoryPolicyAdapter mHistoryAdapter;

    static HistoryPolicyFragment newInstance() {
        return new HistoryPolicyFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.history_fragment;
    }

    @Override
    public void initView(View view) {
        enableLoadMore(true);
        autoLoadData();
        mBinding = DataBindingUtil.bind(view);
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));

        mHistoryAdapter = new HistoryPolicyAdapter();
        mBinding.rvList.setAdapter(mHistoryAdapter);
    }

    /**
     * refreshLayout
     *
     * @return id
     */
    @Override
    protected int onBindRefreshLayout() {
        return R.id.refresh_layout;
    }

    @Override
    public void initListener() {
        mHistoryAdapter.setItemClickListener((user, position) -> {

        });
        mHistoryAdapter.setOnItemLongClickListener((user, position) -> {

        });

    }

    @Override
    public void initData() {
//        mActivity.showInitLoadView();
        mBeans = new ObservableArrayList<>();
        mPresenter.getList(page);
    }


    @Override
    public void onDestroy() {
        mHistoryAdapter.destroy();
        super.onDestroy();
    }

    /**
     * 刷新回调
     */
    @Override
    public void onRefreshEvent() {
        mPresenter.refreshData();
    }

    /**
     * 加载更多的回调
     */
    @Override
    public void onLoadMoreEvent() {
        mPresenter.loadMoreData(page);
    }


    /**
     * 自动加载的事件
     */
    @Override
    public void onAutoLoadEvent() {

    }

    /**
     * 设置presenter对象
     */
    @Override
    public void setPresenter() {
        mPresenter = new HistoryPolicyPresenter(this);
    }

    @Override
    public void refreshData(ObservableArrayList<PolicyBean> data) {

    }

    @Override
    public void loadMoreData(ObservableArrayList<PolicyBean> data) {

    }
}