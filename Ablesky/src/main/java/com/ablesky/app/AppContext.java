package com.ablesky.app;

import android.support.multidex.MultiDexApplication;

/**
 * Created by liangzhen on 2018/1/23.
 */

public class AppContext extends MultiDexApplication{

    public static AppContext application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CustomCrashHandler.getInstance().setCustomCrashHanler(this);
    }
}
