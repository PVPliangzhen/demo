package com.ablesky.http;

import android.text.TextUtils;

import com.ablesky.app.AppContext;
import com.ablesky.utils.EnvirUtils;
import com.ablesky.utils.UIUtils;

/**
 * Created by liangzhen on 2018/1/30.
 */

public class HttpHelper {

    private static String appUserAgent;
    public static int TIMEOUT = 60 * 1000;

    public static String getUserAgent(AppContext context){
        if (TextUtils.isEmpty(appUserAgent)){
            appUserAgent = "AbleSky.NET/ableskyapp (property=nengliketang;server_version=4.2.0)"
                    + " AppVersion/" + EnvirUtils.getAppVersionName(context)
                    + " (Android " + EnvirUtils.getPhoneSysVersion(context)
                    + ";" + EnvirUtils.getPhoneType() + ")";
        }
        return appUserAgent;
    }
}
