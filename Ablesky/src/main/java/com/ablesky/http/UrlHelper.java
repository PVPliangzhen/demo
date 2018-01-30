package com.ablesky.http;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by liangzhen on 2018/1/30.
 */

public class UrlHelper {
    private static final String WWW = "";
    private static final String ALPHA = "alpha.";
    private static final String BETA = "beta.";
    private static final String OMEGA = "omega.";
    private static final String GAMMA = "gamma.";
    private static final String IP = "IP";

    // 环境
    private static String ENVIRONMENT = WWW;

    //    public static final String HTTP_HEADER_IP = "http://www.ablesky-a.com:8080/";
    private static final String HTTP_HEADER_IP = "http://192.168.3.203:8080/";
    private static final String MOBILE_HTTP_HEADER_IP = HTTP_HEADER_IP + "as-mobile/";
    private static final String PASSPORT_HTTP_HEADER_IP = HTTP_HEADER_IP + "as-passport/";
    private static final String EXAM_HTTP_HEADER_IP = HTTP_HEADER_IP + "as-examsystem/";
    private static final String LIVE_HTTP_HEADER_IP = HTTP_HEADER_IP + "as-examsystem/";//TODO 直播的后缀暂时不清楚
    private static final String WAP_HTTP_HEADER_IP = HTTP_HEADER_IP + "as-wap/";

    private static final String HTTP_HEADER = "http://www." + getEnvironment()
            + "ablesky.com/";
    private static final String MOBILE_HTTP_HEADER = "http://mobile."
            + getEnvironment() + "ablesky.com/";
    private static final String PASSPORT_HTTP_HEADER = "http://passport."
            + getEnvironment() + "ablesky.com/";
    private static final String EXAM_HTTP_HEADER = "http://www."
            + getEnvironment() + "ablesky.com/exam/";
    private static final String AI_HTTP_HEADER = "http://ai."
            + getEnvironment() + "ablesky.com/";
    private static final  String WAP_HTTP_HEADER =  "http://wap."
            + getEnvironment() + "ablesky.com/";

    //直播新增
    private static final String LIVE_HTTP_HEADER = "http://live." + getEnvironment() + "ablesky.com/";

    public static String getEnvironment() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/ablesky/environment");
            if (file.exists()) {
                FileInputStream is = new FileInputStream(file);
                byte[] b = new byte[is.available()];
                is.read(b);
                ENVIRONMENT = new String(b);
                if (ENVIRONMENT.equals("www.")) {
                    return WWW;
                }
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ENVIRONMENT = WWW;
        }
        switch (ENVIRONMENT) {
            case ALPHA:
                return ALPHA;
            case BETA:
                return BETA;
            case GAMMA:
                return GAMMA;
            case OMEGA:
                return OMEGA;
            default:
                return WWW;
        }
    }

    public static String getHttpHeader() {
        if (ENVIRONMENT.equals(IP)) {
            return HTTP_HEADER_IP;
        } else {
            return HTTP_HEADER;
        }
    }

    private static String getMobileHttpHeader() {
        if (ENVIRONMENT.equals(IP)) {
            return MOBILE_HTTP_HEADER_IP;
        } else {
            return MOBILE_HTTP_HEADER;
        }
    }

    private static String getPassportHttpHeader() {
        if (ENVIRONMENT.equals(IP)) {
            return PASSPORT_HTTP_HEADER_IP;
        } else {
            return PASSPORT_HTTP_HEADER;
        }
    }

    private static String getExamHttpHeader() {
        if (ENVIRONMENT.equals(IP)) {
            return EXAM_HTTP_HEADER_IP;
        } else {
            return EXAM_HTTP_HEADER;
        }
    }

    public static String getLiveHttpHeader() {
        if (ENVIRONMENT.equals(IP)) {
            return LIVE_HTTP_HEADER_IP;
        } else {
            return LIVE_HTTP_HEADER;
        }
    }

    public static String getWapHttpHeader(){
        if (ENVIRONMENT.equals(IP)) {
            return WAP_HTTP_HEADER_IP;
        } else {
            return WAP_HTTP_HEADER;
        }
    }

    /*
       * 广告位信息获取
       * */
    public static final String advert_info = AI_HTTP_HEADER + "ajax/ads/appads";
}
