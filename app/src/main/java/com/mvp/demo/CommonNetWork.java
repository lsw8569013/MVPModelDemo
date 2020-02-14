package com.mvp.demo;

import com.lsw.mvpframe.netutil.base.INetWorkInfo;

/**
 * @author liushengwei
 * @description: https://github.com/lsw8569013
 * @date :2020-02-14 12:59
 */
public class CommonNetWork implements INetWorkInfo {
    @Override
    public String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String getAPPVersionCode() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
