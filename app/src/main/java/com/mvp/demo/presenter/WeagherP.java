package com.mvp.demo.presenter;


import com.lsw.mvpframe.mvpbase.presenter.BasePresenter;

import com.mvp.demo.view.IWeagherView;
import com.mvp.demo.bean.WeatherBean;
import com.mvp.demo.module.WeatherModule;

/**
 * Created by Administrator on 2017/10/20.
 */

public class WeagherP extends BasePresenter<IWeagherView,WeatherModule> {

    public void showWeather() {

            model.getWeather(new WeatherModule.onWeatherListener() {
            @Override
            public void onComplete(WeatherBean weatherBean) {
                getView().showWeatherV(weatherBean);
            }

            @Override
            public void onCompleteRX(String s) {
                getView().showWeatherV(s);
            }
        });
    }
}
