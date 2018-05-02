package com.lsw.mvpframe.mvpbase.http;

import android.content.Context;
import android.widget.Toast;

import com.lsw.mvpframe.mvpbase.bean.BaseBean;





/**
 * Created by lsw on 2017/2/22.
 * 异常类
 */

public class ApiException extends RuntimeException {

    private BaseBean tBean;

    public ApiException(BaseBean tBean) {
        super(tBean.getMessage());
        this.tBean = tBean;
    }

    public void toastErr() {

        switch (tBean.getStatus()) {
            case 1101://登录过期
//                EventBus.getDefault().post(new EventReLoginBean());
                break;
            case 1102://验证码发送失败
                break;

        }
    }
}
