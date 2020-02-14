package com.lsw.mvpframe.netutil;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.lsw.mvpframe.mvpbase.http.ApiException;
import com.lsw.mvpframe.utils.DialogUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by lsw on 2017/2/22.
 */

public  abstract class BaseObserver<T> implements Observer<T> {


    public BaseObserver(){
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
        onFail(e);
    }

    public abstract void onFail(Throwable e);

    public abstract void onSuccess(T t);

}
