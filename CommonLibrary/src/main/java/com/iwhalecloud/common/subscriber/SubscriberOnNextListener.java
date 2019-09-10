package com.iwhalecloud.common.subscriber;

/**
 * @author xll
 * @date 2018/1/1
 */

public interface SubscriberOnNextListener<T> {

    /**
     * onNext
     *
     * @param t
     */
    void onNext(T t);

}
