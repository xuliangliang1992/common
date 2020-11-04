package com.highlands.tianFuFinance.fun.test;

import com.highlands.tianFuFinance.http.request.RemoteLoanDataSource;
import com.highlands.tianFuFinance.http.response.LoginBean;
import com.highlands.common.base.BasePresenter;
import com.highlands.common.http.BaseXllObserver;

import androidx.databinding.ObservableArrayList;
import io.reactivex.disposables.Disposable;
import io.rx_cache2.Reply;
import timber.log.Timber;

/**
 * @author xll
 */
class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    TestPresenter(TestContract.View view) {
        super(view);
    }

    /**
     * 刷新数据
     */
    @Override
    public void refreshData() {
        mView.refreshData(new ObservableArrayList<>());
    }

    /**
     * 加载更多
     */
    @Override
    public void loadMoreData(int page) {
        mView.loadMoreData(new ObservableArrayList<>());
    }

    @Override
    public void login() {
        RemoteLoanDataSource.getInstance()
                .login("15021676796", "xll19921110")
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new BaseXllObserver<Reply<LoginBean>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Reply<LoginBean> loginBeanReply) {
                        Timber.d(loginBeanReply.toString());
                        mView.loginSuccess();
                    }
                });
    }
}