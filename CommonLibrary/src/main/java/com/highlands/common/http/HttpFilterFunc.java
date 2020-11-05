package com.highlands.common.http;

import android.os.Handler;
import android.os.Looper;

import com.highlands.common.base.BaseApplication;
import com.highlands.common.dialog.DialogManager;
import com.highlands.common.http.response.BaseResponse;
import com.highlands.common.util.StringUtil;
import com.highlands.common.util.ToastUtil;

import io.reactivex.functions.Predicate;

/**
 * 过滤器
 *
 * @author xll
 * @date 2018/12/4
 */
public class HttpFilterFunc<T> implements Predicate<BaseResponse<T>> {

    private String msg;

    @Override
    public boolean test(BaseResponse<T> map) {
        if (map == null) {
            return false;
        }
        if (!map.isSuccess()) {
            msg = map.getMsg();
            if (StringUtil.isStringNull(msg)) {
                msg = map.getHttpmsg();
            }
            new Handler(Looper.getMainLooper()).post(
                    () -> {
                        DialogManager.getInstance().dismissProgressDialog();
                        ToastUtil.showToast(BaseApplication.getInstance().getApplicationContext(), msg);
                    }
            );
            return false;
        }
        return true;
    }
}
