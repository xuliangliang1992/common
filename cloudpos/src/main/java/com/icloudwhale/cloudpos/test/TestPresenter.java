//package com.icloudwhale.cloudpos.test;
//
//import com.icloudwhale.cloudpos.test.TestContract;
//import com.iwhalecloud.common.schedulers.BaseSchedulerProvider;
//
//import io.reactivex.disposables.CompositeDisposable;
//
///**
// * @author xll
// */
//public class TestPresenter implements TestContract.Presenter {
//    private TestContract.View mView;
//    private CompositeDisposable mCompositeSubscription;
//    private BaseSchedulerProvider mSchedulerProvider;
//    private LoanDataSource mLoanDataSource;
//
//    TestPresenter(LoanDataSource loanDataSource, TestContract.View view, BaseSchedulerProvider schedulerProvider) {
//        this.mView = view;
//        this.mLoanDataSource = loanDataSource;
//        this.mSchedulerProvider = schedulerProvider;
//        mView.setPresenter(this);
//        mCompositeSubscription = new CompositeDisposable();
//    }
//
//    @Override
//    public void subscriber() {
//
//    }
//
//    @Override
//    public void unSubscriber() {
//        mCompositeSubscription.clear();
//    }
//}