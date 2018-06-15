package com.lsw.mvpframe.mvpbase.view;

/**
 * create by lsw8569013
 * view 层基类接口 用于，
 */
public interface BaseView {

    /**
     * 开始加载网络请求
     */
    void loading();


    /**
     * 加载失败
     */
    void loadError();
}
