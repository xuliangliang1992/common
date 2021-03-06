package com.icloudwhale.cloudpos.http;


import com.icloudwhale.cloudpos.base.MainApplication;
import com.icloudwhale.cloudpos.constant.Constant;
import com.iwhalecloud.common.commonlibrary.BuildConfig;
import com.iwhalecloud.common.util.SharePreferenceUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xll
 * @date 2018/12/4
 */

public class AppRetrofit {

    private final Retrofit retrofit;
    private static int CONNECT_TIME_OUT = 10;
    private int readTimeOut = 120;

    public AppRetrofit() {
        retrofit = new Retrofit.Builder().client(initBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }
    public AppRetrofit(boolean isToken) {
        retrofit = new Retrofit.Builder().client(initBuilder(isToken).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }
    public AppRetrofit(String url) {
        retrofit = new Retrofit.Builder().client(initBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    public AppRetrofit(int connectTimeOut) {
        CONNECT_TIME_OUT = connectTimeOut;
        retrofit = new Retrofit.Builder().client(initBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    private OkHttpClient.Builder initBuilder() {
        //声明缓存地址和大小
        Cache cache = new Cache(MainApplication.getInstance().getCacheDir(), 10 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String token = (String) SharePreferenceUtil.getInstance().get(MainApplication.getInstance().getApplicationContext(),
                            Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.TOKEN, "");

                    Request newRequest = chain.request().newBuilder()
                            .addHeader("token", token)
                            .build();
                    return chain.proceed(newRequest);
                });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(level(BuildConfig.LOG_DEBUG));
        builder.addInterceptor(interceptor);
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
        builder.writeTimeout(readTimeOut, TimeUnit.SECONDS);
        return builder;
    }

    private OkHttpClient.Builder initBuilder(final boolean isToken) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String token = (String) SharePreferenceUtil.getInstance().get(MainApplication.getInstance().getApplicationContext(),
                            Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.TOKEN, "");
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("token", isToken ? token : "")
                            .build();
                    return chain.proceed(newRequest);
                });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(level(BuildConfig.LOG_DEBUG));
        builder.addInterceptor(interceptor);
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
        builder.writeTimeout(readTimeOut, TimeUnit.SECONDS);
        return builder;
    }

    private HttpLoggingInterceptor.Level level(boolean isDebug) {
        return isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
    }

    public FaceIDService getFaceIDService() {
        return retrofit.create(FaceIDService.class);
    }

}
