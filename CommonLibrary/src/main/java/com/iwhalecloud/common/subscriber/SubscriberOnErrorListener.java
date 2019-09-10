package com.iwhalecloud.common.subscriber;

/**
 * @author xll
 * @date 2018/1/1
 */

public interface SubscriberOnErrorListener {

    /**
     * onError
     *
     * @param e 异常
     */
    void onError(Throwable e);

}
