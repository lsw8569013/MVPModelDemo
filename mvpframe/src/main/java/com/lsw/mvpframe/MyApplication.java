package com.lsw.mvpframe;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;


import com.lsw.mvpframe.mvpbase.http.OkHttpLoggingInterceptor;
import com.lsw.mvpframe.rxhttp.ViseHttp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @Description: 自定义Application，主要负责一些初始化操作
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 17/1/18 23:19.
 */
public class MyApplication extends Application {
    public static boolean isdebug = true;
    static InputStream is = null;

    private volatile static  Context mContext ;

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static MyApplication getInstance() {
        return SingleHolderApplication.sInstance;
    }

    public static void releaseInputStream() {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SingleHolderApplication {
        private static final MyApplication sInstance = new MyApplication();
    }


    @Deprecated
    public static  InputStream getRsaInputStream(){

        AssetManager am = MyApplication.getInstance().getContext().getResources().getAssets();
        try {
            is = am.open("public.pem");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    };

}
