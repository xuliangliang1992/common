package com.icloudwhale.cloudpos.http;

import com.icloudwhale.cloudpos.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 接口
 *
 * @author xll
 * @date 2018/12/4
 */
public interface FaceIDService {

    /**
     * 获取access_token
     *
     * @param url 请求地址
     * @param map 请求体
     * @return Observable
     */
    @POST
    Observable<BaseResponse<String>> getAccessToken(@Url String url, @Body Map<String, Object> map);

}

