package com.iwhalecloud.common.http;

import com.iwhalecloud.common.base.BaseApplication;
import com.iwhalecloud.common.constant.BaseConstant;
import com.iwhalecloud.common.dialog.DialogManager;
import com.iwhalecloud.common.subscriber.HttpObserver;
import com.iwhalecloud.common.subscriber.SubscriberOnErrorListener;
import com.iwhalecloud.common.util.StringUtil;
import com.iwhalecloud.common.util.ToastUtil;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * @author xll
 * @date 2018/12/4
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private HttpObserver mHttpObserver;
    private SubscriberOnErrorListener onErrorListener;

    BaseObserver(HttpObserver httpObserver) {
        mHttpObserver = httpObserver;
    }

    BaseObserver(HttpObserver httpObserver, SubscriberOnErrorListener onErrorListener) {
        this.mHttpObserver = httpObserver;
        this.onErrorListener = onErrorListener;
    }

    /**
     * 错误可以做统一处理，如果不需要统一处理的 自己去覆盖此方法
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        DialogManager.getInstance().dismissProgressHUD();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    //未登录
                    mHttpObserver.httpUnauthorized();
                    break;
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                case GATEWAY_TIMEOUT:
                default:
                    if (!StringUtil.isStringNull(e.getMessage()) && e.getMessage().contains(BaseConstant.HTTP_ERROR)) {
                        ToastUtil.showToast(BaseApplication.getInstance(), e.getMessage());
                    }
                    if (onErrorListener != null) {
                        onErrorListener.onError(e);
                    } else {
                        mHttpObserver.httpException(httpException.code());
                    }
                    break;
            }
        } else if (e instanceof SocketTimeoutException) {
            if (onErrorListener != null) {
                onErrorListener.onError(e);
            } else {
                mHttpObserver.httpTimeOutException();
            }
        } else {
            if (onErrorListener != null) {
                onErrorListener.onError(e);
            } else {
                mHttpObserver.httpOtherException(e.getMessage());
            }
        }
    }
}
