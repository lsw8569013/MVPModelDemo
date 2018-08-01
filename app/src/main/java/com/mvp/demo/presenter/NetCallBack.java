package com.mvp.demo.presenter;

import com.lsw.mvpframe.rxhttp.callback.ACallback;

import utils.ToastUtil;

/**
 * author: Created by lsw on 2018/8/1 15:51
 * description:
 */
public abstract class NetCallBack<T> extends ACallback<T> {
    @Override
    public void onFail(int errCode, String errMsg) {
        ToastUtil.show(errMsg+"-"+errCode);
    }
}
