package com.highlands.tax.fun.splash;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.highlands.common.base.fragment.BaseFragment;
import com.highlands.common.view.CountDownTextView;
import com.highlands.tax.databinding.SplashFragmentBinding;
import com.highlands.common.constant.RouterUrl;
import com.highlands.tax.R;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Unit;

/**
 * @author xll
 * @date 20
 * copyright(c) Highlands
 */
public class SplashFragment extends BaseFragment implements SplashContract.View {
    private SplashContract.Presenter mPresenter;
    private SplashFragmentBinding binding;
    private int count;

    static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.splash_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public void initListener() {
        addDisposable(RxView.clicks(binding.tvCount).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(unit -> {
                    toHome();
                }));
    }

    @Override
    public void initData() {
        count = 4;
        countTime();
    }

    @Override
    public void setPresenter() {
        mPresenter = new SplashPresenter(this);
    }

    public void countTime() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(aLong -> count - aLong)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NotNull Long aLong) {
                        String time = aLong + "s";
                        binding.tvCount.setText(time);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        toHome();
                    }
                });
    }

    @Override
    protected void toHome() {
        super.toHome();
        mActivity.finish();
    }
}