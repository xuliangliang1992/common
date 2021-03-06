package com.icloudwhale.cloudpos.http;

import android.os.Handler;
import android.os.Looper;

import com.icloudwhale.cloudpos.base.MainApplication;
import com.icloudwhale.cloudpos.dialog.DialogManager;
import com.icloudwhale.cloudpos.http.response.BaseResponse;
import com.iwhalecloud.common.util.StringUtil;
import com.iwhalecloud.common.util.ToastUtil;

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
            msg = map.getMessage();
            if (StringUtil.isStringNull(msg)) {
                msg = map.getHttpmsg();
            }
            new Handler(Looper.getMainLooper()).post(
                    () -> {
                        DialogManager.getInstance().dismissProgressHUD();
                        ToastUtil.showToast(MainApplication.getInstance().getApplicationContext(), msg);
                    }
            );
            return false;
        }
        return true;
    }
}
