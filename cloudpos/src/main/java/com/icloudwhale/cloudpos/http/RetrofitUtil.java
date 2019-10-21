package com.icloudwhale.cloudpos.http;

import com.iwhalecloud.common.http.AppRetrofit;

/**
 * @author xuliangliang
 * @date 2019-10-21
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class RetrofitUtil {

    public RetrofitUtil() {
    }

    public FaceIDService getFaceIDService() {
        return new AppRetrofit().getRetrofit().create(FaceIDService.class);
    }

    public FaceIDService getFaceIDService(boolean isToken) {
        return new AppRetrofit(isToken).getRetrofit().create(FaceIDService.class);
    }

    public FaceIDService getFaceIDService(String url) {
        return new AppRetrofit(url).getRetrofit().create(FaceIDService.class);
    }

    public FaceIDService getFaceIDService(int connectTimeOut) {
        return new AppRetrofit(connectTimeOut).getRetrofit().create(FaceIDService.class);
    }
}
