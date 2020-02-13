package com.mvp.demo.presenter;


import android.content.Context;

import com.lsw.mvpframe.rxhttp.callback.ACallback;
import com.mvp.demo.MyApplication;

import utils.ToastUtil;

/**
 * author: Created by lsw on 2018/8/1 15:51
 * description:
 */
public abstract class NetCallBack<T> extends ACallback<T> {
    @Override

    public void onFail(int errCode, String errMsg) {
        Context context = MyApplication.getInstance().getContext();
        ToastUtil.show(context,errMsg+"-"+errCode);
    }
}
