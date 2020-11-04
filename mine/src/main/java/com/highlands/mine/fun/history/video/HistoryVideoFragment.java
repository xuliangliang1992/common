package com.highlands.mine.fun.history.video;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.highlands.common.base.fragment.BaseRefreshFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.mine.R;
import com.highlands.mine.databinding.HistoryFragmentBinding;
import com.highlands.mine.http.response.VideoBean;

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
@Route(path = RouterUrl.MINE_FRAGMENT_HISTORY_VIDEO)
public class HistoryVideoFragment extends BaseRefreshFragment<VideoBean, HistoryVideoPresenter> implements HistoryVideoContract.View {
    private ObservableArrayList<VideoBean> mBeans;
    private HistoryFragmentBinding mBinding;
    private HistoryVideoAdapter mHistoryAdapter;

    static HistoryVideoFragment newInstance() {
        return new HistoryVideoFragment();
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

        mHistoryAdapter = new HistoryVideoAdapter();
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
        mPresenter = new HistoryVideoPresenter(this);
    }

    @Override
    public void refreshData(ObservableArrayList<VideoBean> data) {

    }

    @Override
    public void loadMoreData(ObservableArrayList<VideoBean> data) {

    }
}