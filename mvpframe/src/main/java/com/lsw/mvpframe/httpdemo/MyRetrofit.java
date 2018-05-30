package com.lsw.mvpframe.httpdemo;

import com.lsw.mvpframe.mvpbase.bean.LoginInfo;
import com.lsw.mvpframe.mvpbase.http.HttpBase;
import com.lsw.mvpframe.rxhttp.ViseHttp;
import com.lsw.mvpframe.rxhttp.core.ApiTransformer;
import com.lsw.mvpframe.rxhttp.request.RetrofitRequest;

import io.reactivex.Observable;


/**
 * 接口调用的工具类 demo
 * Created by lsw
 */
public class MyRetrofit {

    private static RetrofitRequest retrofit = ViseHttp.RETROFIT();

    public static HttpServiceHelp.getWeatherRX getWeatherService() {
        return HttpBase.getService(HttpServiceHelp.getWeatherRX.class);
    }

    public static Observable<String> getService(String city) {
        return retrofit
                .create(HttpServiceHelp.getWeatherRX.class)
                .getWeather(city)
                .compose(ApiTransformer.<String>norTransformer())
                ;
    }
    public static Observable<LoginInfo> getLoginService(String phone, String pwd, String isTest) {
        return retrofit
                .create(HttpServiceHelp.LoginT.class)
                .Login(phone,pwd,isTest)
                .compose(ApiTransformer.<LoginInfo>norTransformer())
                ;
    }
    public static Observable<LoginInfo> getLoginService(String phone, String pwd) {
        return retrofit
                .create(HttpServiceHelp.Login.class)
                .Login(phone,pwd)
                .compose(ApiTransformer.<LoginInfo>norTransformer())
                ;
    }


}
