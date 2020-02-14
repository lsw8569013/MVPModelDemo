package com.lsw.mvpframe.netutil.interceptor;

import com.lsw.mvpframe.netutil.base.INetWorkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author liushengwei
 * @description: https://github.com/lsw8569013
 * @date :2020-02-14 14:03
 */
public class CommonRequestInterceptor implements Interceptor {

    private INetWorkInfo netWorkInfo;

    public  CommonRequestInterceptor(INetWorkInfo netWorkInfo) {
        this.netWorkInfo = netWorkInfo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        //TODO 可以添加公共的请求投
        builder.addHeader("","");
        return chain.proceed(builder.build());
    }
}
