package com.lsw.mvpframe.mvpbase.http;


import com.lsw.mvpframe.MyApplication;
import com.lsw.mvpframe.mvpbase.bean.BaseBean;
import com.lsw.mvpframe.utils.DecodeData;
import com.lsw.mvpframe.utils.DialogUtils;
import com.lsw.mvpframe.utils.LogUtil;

/**
 * Author: lsw
 * Created by lsw on 2017/11/15.
 */

public abstract class HttpObserverImpl<BaseBan> extends HttpObserver<BaseBean> {


    @Override
    public void onSuccess(BaseBean baseBean) {
        if(null == baseBean){
            getDataError();
        }else{
            if(baseBean.getStatus() == 0){
                getDataSuccess(DecodeData.decode(baseBean.getData().toString()));
                MyApplication.releaseInputStream();

                String decode = DecodeData.decode(baseBean.getData().toString());
                LogUtil.e("服务器返回的data--"+ decode);
            }else{
                LogUtil.e("服务器返回的data--"+ baseBean.toString());
                DialogUtils.makeText(baseBean.getMessage());
                getDataError();
            }
        }

    }

    public abstract void getDataSuccess(String dataStr) ;

    protected abstract void getDataError();
}
