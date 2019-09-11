package com.icloudwhale.cloudpos.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iwhalecloud.common.base.PermissionListener;
import com.iwhalecloud.common.constant.BaseConstant;
import com.iwhalecloud.common.constant.RouterUrl;
import com.iwhalecloud.common.util.FileUtil;
import com.iwhalecloud.common.util.ToastUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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

    private CompositeDisposable mCompositeDisposable;

    private PermissionListener mPermissionListener;

    @Override
    public void onAttach(Context context) {
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected void toHome() {
        ARouter.getInstance()
                .build(RouterUrl.HAND_MAIN)
                .navigation();
    }

    /**
     * 添加rxJava订阅
     *
     * @param disposable 订阅
     */
    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    /**
     * 动态申请权限
     *
     * @param permissions        权限
     * @param requestCode        请求码
     * @param permissionListener 回调
     */
    public void requestPermission(String[] permissions, int requestCode, PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
        boolean b = false;
        for (String permission : permissions) {
            b = b || ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED;
        }
        if (b) {
            requestPermissions(permissions, requestCode);
        } else {
            if (mPermissionListener != null) {
                mPermissionListener.requestPermissionSuccess();
            }
        }
    }

    public void showToast(String msg) {
        ToastUtil.showToast(getActivity(), msg);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean b = true;
        for (int grantResult : grantResults) {
            b = b && grantResult == PackageManager.PERMISSION_GRANTED;
        }
        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case BaseConstant.CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && b) {
                    if (FileUtil.sdCardExist()) {
                        if (mPermissionListener != null) {
                            mPermissionListener.requestPermissionSuccess();
                        }
                    } else {
                        ToastUtil.showToast(getActivity(), ("没有内存卡"));
                    }
                } else {
                    ToastUtil.showToast(getActivity(), "请打开权限");
                }
                break;
            }
            //调用系统相册申请Sdcard权限回调
            case BaseConstant.STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && b) {
                    if (mPermissionListener != null) {
                        mPermissionListener.requestPermissionSuccess();
                    }
                } else {
                    ToastUtil.showToast(getActivity(), "请打开权限");
                }
                break;
            default:
        }
    }

    @Override
    public void httpUnauthorized() {
        ARouter.getInstance().build(RouterUrl.HAND_LOGIN).navigation();
    }

    @Override
    public void httpException(int code) {
        ToastUtil.showToast(getContext(), "errorCode = " + code + "网络请求出错，请检查网络设置或联系系统管理员");
    }

    @Override
    public void httpTimeOutException() {
        ToastUtil.showToast(mActivity, "网络连接超时");
    }

    @Override
    public void httpOtherException(String message) {
        ToastUtil.showToast(getActivity(), "网络请求失败");
    }


}
