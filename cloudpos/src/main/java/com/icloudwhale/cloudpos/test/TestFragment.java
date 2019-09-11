package com.icloudwhale.cloudpos.test;

import android.view.View;

import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.base.BaseRefreshFragment;
import com.icloudwhale.cloudpos.databinding.TestFragmentBinding;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author xll
 */
public class TestFragment extends BaseRefreshFragment<User> implements TestContract.View {
    private TestContract.Presenter mPresenter;
    private ObservableArrayList<User> mUsers;
    private TestFragmentBinding mBinding;
    private TestAdapter mTestAdapter;
    private int page = 1;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    protected int setLayout() {
        return R.layout.test_fragment;
    }

    @Override
    protected void initView(View view) {
        enableLoadMore(true);
        autoLoadData();
        mBinding = DataBindingUtil.bind(view);
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));

        mTestAdapter = new TestAdapter();
        mBinding.rvList.setAdapter(mTestAdapter);
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
    protected void initListener() {
        mTestAdapter.setItemClickListener((user, position) -> Logger.d("onItemClick " + position));
        mTestAdapter.setOnItemLongClickListener((user, position) -> Logger.d("onItemLongClick " + position));

    }

    @Override
    protected void initData() {
        mActivity.showInitLoadView();
        mUsers = new ObservableArrayList<>();

        addDisposable(Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mActivity.hideInitLoadView()));
    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscriber();
        mTestAdapter.destroy();
    }

    @Override
    public void refreshData(List<User> data) {

    }

    @Override
    public void loadMoreData(List<User> data) {

    }

    /**
     * 刷新回调
     */
    @Override
    public void onRefreshEvent() {
        page = 1;
        mUsers.clear();
        getData();
        addDisposable(Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    getData();
                    mTestAdapter.refresh(mUsers);
                    stopRefresh();
                }));
    }

    /**
     * 加载更多的回调
     */
    @Override
    public void onLoadMoreEvent() {
        page++;
        getData();
        addDisposable(Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getData();
                        mTestAdapter.loadMore(mUsers);
                        stopLoadMore();
                    }
                }));
    }

    private void getData() {
        mUsers.clear();
        for (int i = 10 * (page - 1); i < 10 * page; i++) {
            mUsers.add(new User("名称" + i));
        }
    }

    /**
     * 自动加载的事件
     */
    @Override
    public void onAutoLoadEvent() {
        addDisposable(Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    getData();
                    mTestAdapter.refresh(mUsers);
                    stopRefresh();
                }));
    }
}