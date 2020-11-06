package com.highlands.common.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.ImmersionBar;
import com.highlands.common.R;
import com.highlands.common.base.event.EventBusUtil;
import com.highlands.common.base.event.EventMessage;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.databinding.BaseActivityBinding;
import com.highlands.common.databinding.StubInitLoadingBinding;
import com.highlands.common.databinding.StubNetErrorBinding;
import com.highlands.common.databinding.StubNoDataBinding;
import com.highlands.common.databinding.StubToolBarBinding;
import com.highlands.common.dialog.DialogManager;
import com.highlands.common.network.NetChangeObserver;
import com.highlands.common.network.NetType;
import com.highlands.common.network.NetWorkManager;
import com.highlands.common.util.DeviceUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

/**
 * Activity基类
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) Highlands
 */
public abstract class BaseActivity extends AppCompatActivity implements ILoadView, INoDataView, INetErrView, ViewStub.OnInflateListener, NetChangeObserver {

    protected String TAG = this.getClass().getSimpleName();
    private BaseActivityBinding binding;
    private StubToolBarBinding mStubToolBarBinding;
    private StubInitLoadingBinding mStubInitLoadingBinding;
    private StubNoDataBinding mStubNoDataBinding;
    private StubNetErrorBinding mStubNetErrorBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.base_activity);

        binding.viewStubToolbar.setOnInflateListener(this);
        binding.viewStubInitLoading.setOnInflateListener(this);
        binding.viewStubNoData.setOnInflateListener(this);
        binding.viewStubNetError.setOnInflateListener(this);
        if (isRegisteredEventBus()) {
            EventBusUtil.register(this);
        }

        NetWorkManager.getInstance().setNetChangeObserver(this);
        ImmersionBar.with(this).statusBarDarkFont(false).navigationBarColor(R.color.colorPrimary).keyboardEnable(true).init();
    }

    /**
     * 初始化ToolBar
     */
    private void initTooBar() {
        binding.viewStubToolbar.getViewStub().inflate();
        setSupportActionBar(mStubToolBarBinding.toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mStubToolBarBinding.toolBar.setNavigationOnClickListener(v -> onBackPressed());
        ImmersionBar.with(this).titleBar(mStubToolBarBinding.toolBar).navigationBarColor(R.color.colorPrimary).init();
    }

    /**
     * 设置toolbar标题
     *
     * @param titleId 资源id
     */
    protected void setText(@StringRes int titleId) {
        if (!binding.viewStubToolbar.isInflated()) {
            initTooBar();
        }
        mStubToolBarBinding.tvTitle.setText(titleId);

    }

    /**
     * 设置toolbar标题
     *
     * @param title 标题
     */
    protected void setText(String title) {
        if (!binding.viewStubToolbar.isInflated()) {
            initTooBar();
        }
        mStubToolBarBinding.tvTitle.setText(title);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisteredEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    @Override
    public void showInitLoadView() {
        showLoadView();
    }

    @Override
    public void showInitLoadView(int colorResId) {
        showLoadView(colorResId);
    }

    @Override
    public void hideInitLoadView() {
        hideLoadView();
    }

    @Override
    public void showNoDataView() {
        showNoDataView(true);
    }

    @Override
    public void showNoDataView(int resId) {
        showNoDataView(true, resId);
    }

    @Override
    public void hideNoDataView() {
        showNoDataView(false);
    }

    @Override
    public void showNetWorkErrView() {
        showNetWorkErrView(true);
    }

    @Override
    public void hideNetWorkErrView() {
        showNetWorkErrView(false);
    }

    /**
     * 显示加载ui
     */
    private void showLoadView() {
        showLoadView(android.R.color.transparent);
    }

    /**
     * 显示加载ui
     *
     * @param colorResId 背景颜色
     */
    private void showLoadView(@ColorRes int colorResId) {
        if (!binding.viewStubInitLoading.isInflated()) {
            binding.viewStubInitLoading.getViewStub().inflate();
        }
        mStubInitLoadingBinding.viewLoading.setLoadingBackgroundColor(colorResId);
        mStubInitLoadingBinding.viewLoading.setVisibility(View.VISIBLE);
        mStubInitLoadingBinding.viewLoading.loading(true);
    }

    /**
     * 隐藏加载ui
     */
    private void hideLoadView() {
        if (!binding.viewStubInitLoading.isInflated()) {
            binding.viewStubInitLoading.getViewStub().inflate();
        }
        mStubInitLoadingBinding.viewLoading.setVisibility(View.GONE);
        mStubInitLoadingBinding.viewLoading.loading(false);
    }

    /**
     * 无数据
     *
     * @param show true显示
     */
    private void showNoDataView(boolean show) {
        if (!binding.viewStubNoData.isInflated()) {
            binding.viewStubNoData.getViewStub().inflate();
        }
        mStubNoDataBinding.viewNoData.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * 无数据
     *
     * @param show  true显示
     * @param resId 图片id
     */
    private void showNoDataView(boolean show, @DrawableRes int resId) {
        showNoDataView(show);
        if (show) {
            mStubNoDataBinding.viewNoData.setNoDataView(resId);
        }
    }

    /**
     * 网络异常图示
     *
     * @param show true显示
     */
    private void showNetWorkErrView(boolean show) {
        if (!binding.viewStubNetError.isInflated()) {
            binding.viewStubNetError.getViewStub().inflate();
            mStubNetErrorBinding.viewNetError.setRefreshBtnClickListener(v -> {
                if (!DeviceUtils.hasInternet(this)) {
                    return;
                }
                hideNetWorkErrView();
            });
        }
        mStubNetErrorBinding.viewNetError.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    protected void toMain() {
        ARouter.getInstance().build(RouterUrl.TAX_MAIN).navigation();
    }

    @Override
    public void onInflate(ViewStub stub, View inflated) {
        if (stub.getId() == R.id.view_stub_toolbar) {
            mStubToolBarBinding = DataBindingUtil.bind(inflated);
        } else if (stub.getId() == R.id.view_stub_init_loading) {
            mStubInitLoadingBinding = DataBindingUtil.bind(inflated);
        } else if (stub.getId() == R.id.view_stub_no_data) {
            mStubNoDataBinding = DataBindingUtil.bind(inflated);
        } else if (stub.getId() == R.id.view_stub_net_error) {
            mStubNetErrorBinding = DataBindingUtil.bind(inflated);
        }
    }

    /**
     * 是否注册事件分发
     *
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
    }

    /**
     * 接收到分发的粘性事件
     *
     * @param event 粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveStickyEvent(EventMessage event) {
    }

    /**
     * 是否监听网络变化
     *
     * @return true 监听；false 不监听，默认不监听
     */
    protected boolean isRegisteredNetChange() {
        return false;
    }

    @Override
    public void onConnect(NetType type) {
        if (isRegisteredNetChange()) {
            Timber.tag(TAG).d("onConnect" + type.name());
        }
    }

    @Override
    public void onDisConnect() {
        if (isRegisteredNetChange()) {
            Timber.tag(TAG).d("onDisConnect");
        }
    }

    /**
     * 显示小菊花
     */
    protected void showLoading() {
        DialogManager.getInstance().showProgressDialog(this);
    }

    /**
     * 隐藏小菊花
     */
    protected void hideLoading() {
        DialogManager.getInstance().dismissDialog();
    }
}
