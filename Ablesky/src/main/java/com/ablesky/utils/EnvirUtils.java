package com.ablesky.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by liangzhen on 2018/1/30.
 */

public class EnvirUtils {

    /**
     * 获取应用版本名称
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
        }
        return versionName;
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionCode(Context context) {
        String versionCode = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = String.valueOf(packInfo.versionCode);
        } catch (Exception e) {
        }
        return versionCode;
    }

    /**
     * 获取手机系统版本
     *
     * @param context
     * @return
     */
    public static String getPhoneSysVersion(Context context) {
        return Build.VERSION.RELEASE;
    }


    /**
     * 获取手机型号
     */
    public static String getPhoneType() {
        String model = "";
        try {
            model = URLEncoder.encode(Build.MODEL, "UTF-8").replace("+", " ");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * 获取AndroidManifest.xml  meta-data 值
     *
     * @param context
     * @param name
     * @return
     */
    public static String getMetaDataValue(Context context, String name) {
        String value = "website";
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                Object mDataValue = applicationInfo.metaData.get(name);
                if (null != mDataValue) {
                    value = String.valueOf(mDataValue);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not read the name in the manifest file.", e);
        }
        return value.toString();
    }
}
