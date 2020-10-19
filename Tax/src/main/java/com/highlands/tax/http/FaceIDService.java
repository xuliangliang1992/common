package com.highlands.tax.http;

import com.highlands.common.http.response.BaseResponse;
import com.highlands.tax.http.response.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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
    /**
     * 登录
     *
     * @param url 请求地址
     * @param map 请求体
     * @return Observable
     */
    @FormUrlEncoded
    @POST
    Observable<LoginBean> login(@Url String url, @FieldMap Map<String, Object> map);

}

