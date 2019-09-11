package com.icloudwhale.cloudpos.http;


import com.icloudwhale.cloudpos.http.response.BaseResponse;

import io.reactivex.functions.Function;

/**
 * 去掉通讯里面外层 获取数据bean
 *
 * @author xll
 * @date 2018/12/4
 */

public class HttpMapToBean<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) {
        return tBaseResponse.getData();
    }
}
