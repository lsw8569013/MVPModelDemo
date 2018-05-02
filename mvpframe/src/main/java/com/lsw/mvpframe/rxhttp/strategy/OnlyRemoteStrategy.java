package com.lsw.mvpframe.rxhttp.strategy;


import com.lsw.mvpframe.rxhttp.core.ApiCache;
import com.lsw.mvpframe.rxhttp.mode.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;


/**
 * @Description: 缓存策略--只取网络
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 16/12/31 14:30.
 */
public class OnlyRemoteStrategy<T> extends CacheStrategy<T> {
    @Override
    public <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, Type type) {
        return loadRemote(apiCache, cacheKey, source);
    }
}
