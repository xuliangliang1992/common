package com.icloudwhale.cloudpos.http;

import com.iwhalecloud.common.subscriber.HttpObserver;
import com.iwhalecloud.common.subscriber.SubscriberOnCompleteListener;
import com.iwhalecloud.common.subscriber.SubscriberOnErrorListener;

/**
 * @author xll
 * @date 2018/12/4
 */

public abstract class BaseXllObserver<T> extends BaseObserver<T> {

    private SubscriberOnCompleteListener mOnCompleteListener;

    protected BaseXllObserver(HttpObserver httpObserver) {
        super(httpObserver);
    }

    protected BaseXllObserver(HttpObserver httpObserver, SubscriberOnErrorListener onErrorListener) {
        super(httpObserver, onErrorListener);
    }

    public BaseXllObserver(HttpObserver httpObserver, SubscriberOnCompleteListener onCompleteListener) {
        super(httpObserver);
        this.mOnCompleteListener = onCompleteListener;
    }

    @Override
    public void onComplete() {
        if (mOnCompleteListener != null) {
            mOnCompleteListener.onComplete();
        }
    }

}
