package com.highlands.tianFuFinance.http;

import com.highlands.common.http.response.BaseResponse;
import com.highlands.tianFuFinance.http.response.LoginBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

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
     * 发送验证码
     *
     * @param url 请求地址
     * @param map 请求体
     * @return Observable
     */
    @POST
    Observable<BaseResponse<SmsSendBean>> sendSms(@Url String url, @Body Map<String, Object> map);

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
     * 获取access_token
     *
     * @param url 请求地址
     * @param map 请求体
     * @return Observable
     */
    @POST
    Observable<BaseResponse<LoginBean>> register(@Url String url, @Body Map<String, Object> map);

    /**
     * 账号密码登录
     *
     * @param url 请求地址
     * @param map 请求体
     * @return Observable
     */
    @POST
    Observable<BaseResponse<LoginBean>> accountLogin(@Url String url, @Body Map<String, Object> map);

    /**
     * 验证码登录
     *
     * @param url 请求地址
     * @param map 请求体
     * @return Observable
     */
    @POST
    Observable<BaseResponse<LoginBean>> mobileLogin(@Url String url, @Body Map<String, Object> map);

}

