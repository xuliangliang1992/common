package com.highlands.tianFuFinance.http;

import com.highlands.common.http.response.BaseResponse;
import com.highlands.common.http.response.LiveBean;
import com.highlands.common.http.response.LoginBean;
import com.highlands.common.http.response.PolicyBean;
import com.highlands.common.http.response.VideoBean;
import com.highlands.tianFuFinance.http.response.BannerBean;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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

    /**
     * Banner
     *
     * @param url 请求地址
     * @return Observable
     */
    @GET
    Observable<BaseResponse<List<BannerBean>>> getBannerList(@Url String url);

    /**
     * 最新政策解读列表
     *
     * @param url 请求地址
     * @param limit 数量
     * @return Observable
     */
    @GET
    Observable<BaseResponse<List<PolicyBean>>> getPolicyNews(@Url String url, @Query("limit") Integer limit);

    /**
     * 直播预告
     *
     * @param url 请求地址
     * @param limit 数量
     * @return Observable
     */
    @GET
    Observable<BaseResponse<List<LiveBean>>> getLiveNotices(@Url String url, @Query("limit") Integer limit);

    /**
     * 最新视频
     *
     * @param url 请求地址
     * @param limit 数量
     * @return Observable
     */
    @GET
    Observable<BaseResponse<List<VideoBean>>> getVideoNews(@Url String url, @Query("limit") Integer limit);

}

