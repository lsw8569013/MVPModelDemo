package com.mvp.demo.acy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lsw.mvpframe.mvpbase.base.BaseActivity;
import com.lsw.mvpframe.rxhttp.subscriber.ApiCallbackSubscriber;
import com.mvp.demo.R;
import com.mvp.demo.bean.WeatherBean;
import com.mvp.demo.http.NetRetrofitUtil;
import com.mvp.demo.presenter.NetCallBack;
import com.mvp.demo.presenter.WeagherP;
import com.mvp.demo.view.IWeagherView;

import butterknife.BindView;


/**
 * mvp weatherAcy Deomo
 * Created by lsw on 2017/10/20.
 */
public class WeatherAcy extends BaseActivity<IWeagherView, WeagherP> implements IWeagherView {


    @BindView(R.id.btn_getWeather)
    Button btnGetWeather;
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected int setLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {

        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WeatherAcy.this,HomeActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected WeagherP createPresenter() {
        return new WeagherP();
    }

    // 发送请求
    public void getWeatherClick(View view) {
//        presenter.showWeather();

        NetRetrofitUtil.getWeather("101030100",
                new ApiCallbackSubscriber<>(
                        new NetCallBack<WeatherBean>() {
                            @Override
                            public void onSuccess(WeatherBean data) {
                                showWeatherV(data);
                            }
                        }
                ));
    }

    @Override
    public void showWeatherV(WeatherBean weatherBean) {
        tvTest.setText("mvpbase---" + weatherBean.toString());
    }



    @Override
    public void showWeatherV(String s) {
        tvTest.setText("mvpbase" + s);
    }


    @Override
    public void loading() {

    }

    @Override
    public void loadError() {

    }
}
