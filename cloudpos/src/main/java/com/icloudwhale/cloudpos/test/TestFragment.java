package com.icloudwhale.cloudpos.test;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.base.BaseRefreshFragment;
import com.icloudwhale.cloudpos.databinding.TestFragmentBinding;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author xll
 */
public class TestFragment extends BaseRefreshFragment<User> implements TestContract.View {
    private TestContract.Presenter mPresenter;
    private TestFragmentBinding binding;

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
        binding = DataBindingUtil.bind(view);
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

    }

    @Override
    protected void initData() {

        //        mActivity.showNetWorkErrView();
        //                mActivity.showInitLoadView();
        //        mActivity.showNoDataView();
        //        RxView.clicks(binding.getRoot())
        //                .compose(new ObservableTransformer<Unit, Object>() {
        //                    @Override
        //                    public ObservableSource<Object> apply(Observable<Unit> upstream) {
        //                        return null;
        //                    }
        //                })
        //                .subscribe(new Consumer<Object>() {
        //                    @Override
        //                    public void accept(Object o) throws Exception {
        //                        Logger.d("0-0-");
        //                    }
        //                });
    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscriber();
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
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        stopRefresh();
                    }
                });
    }

    /**
     * 加载更多的回调
     */
    @Override
    public void onLoadMoreEvent() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        stopLoadMore();
                    }
                });
    }

    /**
     * 自动加载的事件
     */
    @Override
    public void onAutoLoadEvent() {
        Logger.d("onAutoLoadEvent");
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        stopRefresh();
                    }
                });}
}