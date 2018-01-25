package com.ablesky.app;

import android.util.Log;

/**
 * Created by liangzhen on 2018/1/25.
 */

public class AppLog {

    private static boolean sDebug = false;

    public static void setDebug(boolean debug) {
        sDebug = debug;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void d(String tag, String msg) {
        if (sDebug)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (sDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (sDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (sDebug)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (sDebug)
            Log.w(tag, msg);
    }
}
