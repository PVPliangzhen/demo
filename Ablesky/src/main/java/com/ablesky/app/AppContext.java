package com.ablesky.app;

import android.support.multidex.MultiDexApplication;

/**
 * Created by liangzhen on 2018/1/23.
 */

public class AppContext extends MultiDexApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        CustomCrashHandler.getInstance().setCustomCrashHanler(this);
    }
}
