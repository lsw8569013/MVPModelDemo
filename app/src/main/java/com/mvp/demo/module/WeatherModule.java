package com.mvp.demo.module;


import com.lsw.mvpframe.rxhttp.callback.ACallback;
import com.lsw.mvpframe.rxhttp.subscriber.ApiCallbackSubscriber;
import com.mvp.demo.bean.WeatherBean;
import com.mvp.demo.http.MyRetrofit;


/**
 * Created by Administrator on 2017/10/20.
 */

public class WeatherModule {

    public void getWeather(final onWeatherListener listener){

//        MyRetrofit.getWeatherService().getWeather("天津")
//                .compose(ApiTransformer.<WeatherBean>norTransformer())
//                .subscribe(new ApiCallbackSubscriber<>(new ACallback<WeatherBean>() {
//                    @Override
//                    public void onSuccess(WeatherBean authorModel) {
//                        ViseLog.i("request onSuccess!");
//                        if (authorModel == null) {
//                            return;
//                        }
//                        listener.onComplete(authorModel);
//                    }
//
//                    @Override
//                    public void onFail(int errCode, String errMsg) {
//                        ViseLog.e("request errorCode:" + errCode + ",errorMsg:" + errMsg);
//                    }
//                }));

//        ViseHttp.BASE(new GetRequest("weather/json.shtml"))
//                .addUrlParam("appId", "10001")
//                .request(new ACallback<WeatherBean>() {
//                        @Override
//                        public void onSuccess(WeatherBean data) {
//
//                        }
//
//                        @Override
//                        public void onFail(int errCode, String errMsg) {
//
//                        }
//                    });

//        //带缓存策略
//        MyRetrofit.getService("天津")
//                .compose(ApiTransformer.<WeatherBean>norTransformer())
//                .compose(ViseHttp.getApiCache().<WeatherBean>transformer(CacheMode.ONLY_REMOTE, WeatherBean.class))
//                .subscribe(new ApiCallbackSubscriber<>(new ACallback<CacheResult<WeatherBean>>() {
//                    @Override
//                    public void onSuccess(CacheResult<WeatherBean> cacheResult) {
//                        ViseLog.i("request onSuccess!");
//                        if (cacheResult == null || cacheResult.getCacheData() == null) {
//                            return;
//                        }
//                        listener.onCompleteRX(cacheResult.getCacheData().toString());
////                        if (cacheResult.isCache()) {
////                            mShow_response_data.setText("From Cache:\n" + cacheResult.getCacheData().toString());
////                        } else {
////                            mShow_response_data.setText("From Remote:\n" + cacheResult.getCacheData().toString());
////                        }
//                    }
//
//                    @Override
//                    public void onFail(int errCode, String errMsg) {
//                        ViseLog.e("request errorCode:" + errCode + ",errorMsg:" + errMsg);
//                    }
//                }));

        MyRetrofit.getService("北京")
                .subscribe(new ApiCallbackSubscriber<>(new ACallback<WeatherBean>() {
                    @Override
                    public void onSuccess(WeatherBean authorModel) {

                        if (authorModel == null) {
                            return;
                        }
                        listener.onComplete(authorModel);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                }));

//        MyRetrofit.getService("北京")
//                .subscribe(new HttpObserverImpl<WeatherBean>() {
//
//                    @Override
//                    public void getDataSuccess(BaseBean baseBean) {
//
//                    }
//                });


    }

    public void getWeather2(final ACallback<WeatherBean> aCallback) {
        MyRetrofit.getService("北京")
                .subscribe(new ApiCallbackSubscriber<>(aCallback));
    }

    public interface onWeatherListener{
        void onComplete(WeatherBean weatherBean);

        void onCompleteRX(String s);
    }


}
