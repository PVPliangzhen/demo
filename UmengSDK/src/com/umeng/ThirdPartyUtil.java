package com.umeng;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.List;

public class ThirdPartyUtil {
    public static final String WX_APP_KEY = "wxba36fda7043ca6d3";
    public static final String WX_APP_SECRET = "4f85d7a259899944bc6011afa8a23d5a";
    public static final String WB_APP_KEY = "2896308683";
    public static final String WB_APP_SECRET = "600d979e5c727b810b25d9227ac2a182";
    public static final String QQ_APP_KEY = "1101038737";
    public static final String QQ_APP_SECRET = "HCURiBTaj8n3767t";
    public static final String ACCESS_TOKEN = "ab_a";
    public static final String SCREEN_NAME = "ab_b";
    public static final String REFRESH_TOKEN = "ab_c";
    public static final String THIRDLOGIN = "ab_d";
    public static final String THIRDLOGIN_WEIXIN = "ab_e";
    public static final String THIRDLOGIN_SINA = "ab_f";
    public static final String THIRDLOGIN_QQ = "ab_g";

    public static void initUmeng(Context context) {
        UMShareAPI.get(context);
        PlatformConfig.setWeixin(WX_APP_KEY, WX_APP_SECRET);
        //新浪微博
        PlatformConfig.setSinaWeibo(WB_APP_KEY, WB_APP_SECRET,"http://sns.whalecloud.com");
        //QQ&空间
        PlatformConfig.setQQZone(QQ_APP_KEY, QQ_APP_SECRET);
    }
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断微博是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWeiboClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }
        return false;
    }
}