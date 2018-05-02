package com.lsw.mvpframe.mvpbase.http;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * http 返回值的处理 ，可以根据自己的业务进行处理
 */
public abstract class MyCallBack<T> implements Callback<T> {

    private static final String TAG = "MyCallBack";

    @Override
    public void onResponse(Call call, Response response) {
        if(response.code()>=200 && response.code()<=300){
            onSuccess(call,response,(T)response.body());
        }else{
            getDataError(call,response);
        }
    }



    protected abstract void onSuccess(Call<T> call, Response<T> response,T t);
    protected abstract void getDataError(Call<T> call, Response<T> response);

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.e(TAG,t.getMessage().toString());
    }

}
