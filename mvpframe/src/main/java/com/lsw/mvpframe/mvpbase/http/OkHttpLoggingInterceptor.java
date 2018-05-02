package com.lsw.mvpframe.mvpbase.http;

/**
 * Created by Administrator on 2017/11/15.
 */

import android.text.TextUtils;
import android.util.Log;

import com.lsw.mvpframe.MyApplication;
import com.lsw.mvpframe.utils.LogUtil;

import java.io.IOException;
import java.nio.charset.Charset;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * 网络切面处理
 * Created By: AndroidStudio
 * Author :http://write.blog.csdn.net/postedit/53446562
 *
 */
public class OkHttpLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder newBuilder = chain
                .request()
                .newBuilder();

        Request request = newBuilder
//                .addHeader("Content-Type","application/json; charset=utf-8")
//                .addHeader("Authorization", Bearer )
                .build();

        String cacheControl=request.cacheControl().toString();
//        if(TextUtils.isEmpty(cacheControl)){
//            cacheControl = "public, max-age=60";
//        }
        Response response = chain.proceed(request);

        if(MyApplication.isdebug){
            LogUtil.e( "response返回参数" + response.toString());

            //添加打印服务器返回的数据
            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            BufferedSource source = responseBody.source();
            source.request(Integer.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();


            if (contentLength != 0) {
                LogUtil.e("服务器返回数据："+ ""+buffer.clone().readString(Charset.forName("UTF-8")));
            }
        }

        return response.
                newBuilder()
//                .header("Cache-Control", cacheControl)
                .build();

    }
}