package com.highlands.tianFuFinance.fun.home;

import com.highlands.common.base.BasePresenter;
import com.highlands.common.http.BaseXllObserver;
import com.highlands.tianFuFinance.http.request.RemoteLoanDataSource;
import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.common.http.response.VideoBean;
import com.highlands.tianFuFinance.http.response.BannerBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * @author xll
 * @date 2020-11-01
 * copyright(c) Highlands
 */
class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void getBannerList() {
        RemoteLoanDataSource.getInstance().getBannerList()
                .subscribe(new BaseXllObserver<List<BannerBean>>(mView) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<BannerBean> bannerBeans) {
                        mView.getBanner(bannerBeans);
                    }
                });
    }

    @Override
    public void getPolicyNews() {
        RemoteLoanDataSource.getInstance().getPolicyNews()
                .flatMap((Function<List<PolicyBean>, ObservableSource<PolicyBean>>) Observable::fromIterable)
                .map(policyBean -> {
                    policyBean.setViewType(HomeAdapter.TYPE_POLICY);
                    Timber.tag(TAG).d(policyBean.toString());
                    return policyBean;
                })
                .toList()
                .subscribe(new SingleObserver<List<PolicyBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<PolicyBean> policyBeans) {
                        mView.getPolicyNews(policyBeans);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.tag(TAG + " getPolicyNews").e(e);
                    }
                });
    }

    @Override
    public void getLiveNotices() {
        RemoteLoanDataSource.getInstance().getLiveNotices()
                .flatMap((Function<List<LiveBean>, ObservableSource<LiveBean>>) Observable::fromIterable)
                .map(liveBean -> {
                    liveBean.setViewType(HomeAdapter.TYPE_LIVE);
                    return liveBean;
                })
                .toList()
                .subscribe(new SingleObserver<List<LiveBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<LiveBean> liveBeans) {
                        mView.getLiveNotices(liveBeans);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.tag(TAG + " getLiveNotices").e(e);
                    }
                });
    }

    @Override
    public void getVideoNews() {
        RemoteLoanDataSource.getInstance().getVideoNews()
                .flatMap((Function<List<VideoBean>, ObservableSource<VideoBean>>) Observable::fromIterable)
                .map(videoBean -> {
                    videoBean.setViewType(HomeAdapter.TYPE_VIDEO);
                    return videoBean;
                })
                .toList()
                .subscribe(new SingleObserver<List<VideoBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<VideoBean> videoBeans) {
                        mView.getVideoNews(videoBeans);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.tag(TAG + " getVideoNews").e(e);
                    }
                });
    }

}