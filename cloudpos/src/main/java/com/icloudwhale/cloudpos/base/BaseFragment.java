package com.icloudwhale.cloudpos.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.icloudwhale.cloudpos.base.event.EventBusUtil;
import com.icloudwhale.cloudpos.base.event.EventMessage;
import com.iwhalecloud.common.constant.RouterUrl;
import com.iwhalecloud.common.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Fragment基类
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    /**
     * Activity的context
     */
    protected BaseActivity mActivity;

    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        onAttachToContext(context);
        mCompositeDisposable = new CompositeDisposable();
    }

    private void onAttachToContext(Context context) {
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisteredEventBus()) {
            EventBusUtil.register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCommonView(view);
        initView(view);
        initListener();
    }

    protected void initCommonView(View view) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        if (isRegisteredEventBus()) {
            EventBusUtil.unregister(this);
        }
        mCompositeDisposable.clear();
        super.onDestroy();
    }

    protected void toHome() {
        ARouter.getInstance()
                .build(RouterUrl.HAND_MAIN)
                .navigation();
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
     * 添加rxJava订阅
     *
     * @param disposable 订阅
     */
    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public void showToast(String msg) {
        ToastUtil.showToast(getActivity(), msg);
    }

    @Override
    public void httpUnauthorized() {
        ARouter.getInstance().build(RouterUrl.HAND_LOGIN).navigation();
    }

    @Override
    public void httpException(int code) {
        ToastUtil.showToast(mActivity, "errorCode = " + code + "网络请求出错，请检查网络设置或联系系统管理员");
    }

    @Override
    public void httpTimeOutException() {
        ToastUtil.showToast(mActivity, "网络连接超时");
    }

    @Override
    public void httpOtherException(String message) {
        ToastUtil.showToast(mActivity, "网络请求失败");
    }

}
