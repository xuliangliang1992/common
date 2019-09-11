package com.icloudwhale.cloudpos.test;

import android.view.View;

import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.base.BaseRefreshFragment;
import com.icloudwhale.cloudpos.base.event.EventBusUtil;
import com.icloudwhale.cloudpos.base.event.EventCode;
import com.icloudwhale.cloudpos.base.event.EventMessage;
import com.icloudwhale.cloudpos.databinding.TestFragmentBinding;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author xll
 */
public class TestFragment extends BaseRefreshFragment<User, TestPresenter> implements TestContract.View {
    private ObservableArrayList<User> mUsers;
    private TestFragmentBinding mBinding;
    private TestAdapter mTestAdapter;
    private int page = 1;

    static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.test_fragment;
    }

    @Override
    public void initView(View view) {
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
    public void initListener() {
        mTestAdapter.setItemClickListener((user, position) -> {
            Logger.d("onItemClick " + position);
            EventBusUtil.post(new EventMessage<String>(EventCode.EVENT_A, user.getName()));
        });
        mTestAdapter.setOnItemLongClickListener((user, position) -> Logger.d("onItemLongClick " + position));

    }

    @Override
    public void initData() {
        mActivity.showInitLoadView();
        mUsers = new ObservableArrayList<>();

        addDisposable(Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mActivity.hideInitLoadView()));
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event.getCode() == EventCode.EVENT_A) {
            Logger.d(event.toString());
        }
    }

    @Override
    public void onDestroy() {
        mTestAdapter.destroy();
        super.onDestroy();
    }

    /**
     * 刷新回调
     */
    @Override
    public void onRefreshEvent() {
        page = 1;
        mUsers.clear();
        mPresenter.refreshData();
    }

    /**
     * 加载更多的回调
     */
    @Override
    public void onLoadMoreEvent() {
        page++;
        mPresenter.loadMoreData();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
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

    /**
     * 刷新数据
     *
     * @param data
     */
    @Override
    public void refreshData(ObservableArrayList<User> data) {
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
     * 加载更多
     *
     * @param data
     */
    @Override
    public void loadMoreData(ObservableArrayList<User> data) {
        getData();
        addDisposable(Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    getData();
                    mTestAdapter.loadMore(mUsers);
                    stopLoadMore();
                }));
    }

    /**
     * 设置presenter对象
     */
    @Override
    public void setPresenter() {
        mPresenter = new TestPresenter(this);
    }
}