package com.lsw.mvpframe.mvpbase.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.lsw.mvpframe.utils.DialogUtils;
import com.lsw.mvpframe.utils.GsonUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by lsw on 2017/2/22.
 */

public  abstract class HttpObserver<T> implements Observer<T> {


    public HttpObserver(){
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {


    }

    @Override
    public void onNext(T t) {

        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            ((ApiException)e).toastErr();
            DialogUtils.makeText(e.getMessage());
            Log.e("onError: ","dddddd"+  e.getMessage().toString() );

        } else if ((e instanceof UnknownHostException))
            DialogUtils.makeText( "网络异常", Toast.LENGTH_SHORT);
        else if (e instanceof JsonSyntaxException) {
            DialogUtils.makeText( "数据异常", Toast.LENGTH_SHORT);
        } else if (e instanceof SocketTimeoutException) {
            DialogUtils.makeText( "连接超时", Toast.LENGTH_SHORT);
        } else if (e instanceof ConnectException) {
            DialogUtils.makeText("连接服务器失败", Toast.LENGTH_SHORT);
        } else {
            DialogUtils.makeText( "未知错误", Toast.LENGTH_SHORT);
        }
        Log.d("TAG", "e:" + e);
        onSuccess(null);
    }

    public abstract void onSuccess(T t);

}
