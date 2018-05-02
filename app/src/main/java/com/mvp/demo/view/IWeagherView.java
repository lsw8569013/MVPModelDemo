package com.mvp.demo.view;


import com.lsw.mvpframe.mvpbase.view.BaseView;
import com.mvp.demo.bean.WeatherBean;

/**
 *
 * lsw8569013 如果有加载 网络显示 loading 可以 extends BaseView.
 */
public interface IWeagherView /*extends BaseView*/{

    void showWeatherV(WeatherBean weatherBean);

    void showWeatherV(String s);
}
