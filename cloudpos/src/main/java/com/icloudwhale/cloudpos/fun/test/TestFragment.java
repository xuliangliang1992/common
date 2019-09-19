package com.icloudwhale.cloudpos.fun.test;

import android.annotation.SuppressLint;
import android.view.View;

import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.base.BaseRefreshFragment;
import com.icloudwhale.cloudpos.base.event.EventBusUtil;
import com.icloudwhale.cloudpos.base.event.EventCode;
import com.icloudwhale.cloudpos.base.event.EventMessage;
import com.icloudwhale.cloudpos.databinding.TestFragmentBinding;
import com.iwhalecloud.common.util.PermissionUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * @author xll
 */
public class TestFragment extends BaseRefreshFragment<User, TestPresenter> implements TestContract.View {
    private ObservableArrayList<User> mUsers;
    private TestFragmentBinding mBinding;
    private TestAdapter mTestAdapter;

    @Contract(" -> new")
    @NotNull
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
            Timber.d("request");
            PermissionUtil.launchCamera(
                    new PermissionUtil.RequestPermission() {
                        @Override
                        public void onRequestPermissionSuccess() {
                            mPresenter.login();
                        }

                        @Override
                        public void onRequestPermissionFailure(List<String> permissions) {

                        }

                        @Override
                        public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {

                        }
                    },
                    new RxPermissions(TestFragment.this)
            );
            EventBusUtil.post(new EventMessage<>(EventCode.EVENT_A, user.getName()));
        });
        mTestAdapter.setOnItemLongClickListener((user, position) -> Timber.d("onItemLongClick " + position));

    }

    @SuppressLint("CheckResult")
    @Override
    public void initData() {

        //        mActivity.showInitLoadView();
        mUsers = new ObservableArrayList<>();
        //        mActivity.showNetWorkErrView();
//        Observable.interval(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(aLong -> {
//                    mPresenter.login();
//                            Timber.d("onNext " + aLong);
//                            if (aLong == 10) {
//                                ObjectHelper.requireNonNull(aLong, "");
//                                throw new NullPointerException();
//                            }
//                        },
//                        throwable -> Timber.d("onError " + throwable.getMessage()),
//                        () -> Timber.d("onComplete"),
//                        disposable -> {
//                            Timber.d("onSubscribe");
//                            addDisposable(disposable);
//                        });
//        Observable.interval(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        mCompositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        if (aLong == 4) {
//                            onDestroy();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event.getCode() == EventCode.EVENT_A) {
            Timber.d(event.toString());
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
        mPresenter.login();
        mUsers.clear();
        mPresenter.refreshData();
    }

    /**
     * 加载更多的回调
     */
    @Override
    public void onLoadMoreEvent() {
        mPresenter.loadMoreData();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    private void getData() {
        mUsers.clear();
        for (int i = 10 * (page - 1); i < 10 * page; i++) {
            if (i % 2 == 0) {
                mUsers.add(new User("名称" + i, true));
            } else {
                mUsers.add(new User("名称" + i, false));
            }
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