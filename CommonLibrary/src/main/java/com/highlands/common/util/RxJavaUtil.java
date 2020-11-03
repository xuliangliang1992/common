package com.highlands.common.util;

import com.highlands.common.http.HttpFilterFunc;
import com.highlands.common.http.HttpMapToBean;
import com.highlands.common.http.response.BaseResponse;
import com.highlands.common.schedulers.SchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xuliangliang
 * @date 2020/11/3
 * copyright(c) Highlands
 */
@SuppressWarnings("ALL")
public class RxJavaUtil {

    public static <T> Observable<T> applySchedulers(Observable<T> observable) {
        return observable.subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui());
    }

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<BaseResponse<T>, T> filterResponse() {
        return upstream -> upstream
                .filter(new HttpFilterFunc<>())
                .map(new HttpMapToBean<>());
    }

    /**
     * 转换线程
     */
    private final static ObservableTransformer mTransformerSchedulers = upstream -> upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    public static <T> ObservableTransformer<T, T> changeSchedulers() {
        return ObjectCastUtil.cast(mTransformerSchedulers) ;
    }

    /**
     * 转换数据
     */
    private final static ObservableTransformer mTransformerFilters = upstream -> upstream
            .filter(new HttpFilterFunc<>())
            .map(new HttpMapToBean<>());

    public static <T> ObservableTransformer<BaseResponse<T>, T> filterData() {
        return ObjectCastUtil.cast(mTransformerFilters);
    }
}
