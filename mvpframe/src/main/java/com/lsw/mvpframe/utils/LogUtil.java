package com.lsw.mvpframe.utils;

import android.util.Log;

import com.lsw.mvpframe.rxhttp.config.HttpGlobalConfig;


/**
 * Created by Administrator on 2017/11/7.
 */

public class LogUtil {


    public static void e(String msg){
        if(HttpGlobalConfig.canLog){
            Log.e("lsw",msg);
        }
    }
    public static void w(String msg){
        if(HttpGlobalConfig.canLog){
            Log.w("lsw",msg);
        }
    }

    public static void e(Exception e) {
        if(HttpGlobalConfig.canLog){
            Log.w("lsw",e.getMessage().toString());
        }
    }

    public static void i(String msg) {
        if(HttpGlobalConfig.canLog){
            Log.i("lsw",msg);
        }
    }
}
