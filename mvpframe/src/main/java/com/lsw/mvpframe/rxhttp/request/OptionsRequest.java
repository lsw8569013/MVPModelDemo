package com.lsw.mvpframe.rxhttp.request;


import com.lsw.mvpframe.rxhttp.ViseHttp;
import com.lsw.mvpframe.rxhttp.callback.ACallback;
import com.lsw.mvpframe.rxhttp.core.ApiManager;
import com.lsw.mvpframe.rxhttp.mode.CacheResult;
import com.lsw.mvpframe.rxhttp.subscriber.ApiCallbackSubscriber;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;





/**
 * @Description: Options请求
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2017-04-28 16:07
 */
public class OptionsRequest extends BaseHttpRequest<OptionsRequest> {
    public OptionsRequest(String suffixUrl) {
        super(suffixUrl);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        return apiService.options(suffixUrl, params).compose(this.<T>norTransformer(type));
    }

    @Override
    protected <T> Observable<CacheResult<T>> cacheExecute(Type type) {
        return this.<T>execute(type).compose(ViseHttp.getApiCache().<T>transformer(cacheMode, type));
    }

    @Override
    protected <T> void execute(ACallback<T> callback) {
        DisposableObserver disposableObserver = new ApiCallbackSubscriber(callback);
        if (super.tag != null) {
            ApiManager.get().add(super.tag, disposableObserver);
        }
        if (isLocalCache) {
            this.cacheExecute(getSubType(callback)).subscribe(disposableObserver);
        } else {
            this.execute(getType(callback)).subscribe(disposableObserver);
        }
    }
}
