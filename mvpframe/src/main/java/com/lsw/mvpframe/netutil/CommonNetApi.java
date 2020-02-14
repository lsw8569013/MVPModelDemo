package com.lsw.mvpframe.netutil;

import com.lsw.mvpframe.netutil.base.NetWorkApi;
import com.lsw.mvpframe.netutil.interceptor.CommonResponseInterceptor;

import okhttp3.Interceptor;

/**
 * 适配多域名的api
 * @author liushengwei
 * @description: https://github.com/lsw8569013
 * @date :2020-02-14 17:22
 */
public class CommonNetApi extends NetWorkApi {

    private static volatile CommonNetApi mInstance;

    public static CommonNetApi getInstance() {
        if (mInstance == null) {
            synchronized (CommonNetApi.class) {
                mInstance = new CommonNetApi();
            }
        }
        return mInstance;
    }

    protected CommonNetApi() {
        super("http://t.weather.sojson.com/api/weather/");
    }

    public static <T> T getService(Class<T> clazz) {
        return getInstance().getRetrofit().create(clazz);
    }

    @Override
    protected Interceptor getInterceptor() {
        return new CommonResponseInterceptor();
    }
}
