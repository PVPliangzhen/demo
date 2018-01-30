package com.ablesky.utils;

import android.support.annotation.Nullable;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * 字符串操作工具包
 */
public class StringUtils {
    /**
     * 判断给定字符串是否空串
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(@Nullable String str) {
        if (android.text.TextUtils.isEmpty(str)) {
            return true;
        } else {
            if (str.equalsIgnoreCase("null")) {
                return true;
            }
        }
        return false;
    }

    public static int str2Int(String str) {
        return Integer.parseInt(str);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static long toLong(String str, int defValue) {
        try {
            return Long.parseLong(str.trim());
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 字符串数组转字符串
     *
     * @param strings 数组
     * @return String
     */
    public static String stringArrayToStringForSearchHistory(String[] strings) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                sBuffer.append(strings[i]);
            } else {
                sBuffer.append(strings[i] + "-_-");
            }
        }
        return sBuffer.toString();
    }

    /**
     * 格式化视频时长
     *
     * @param length
     * @return
     */
    public static String formatVideoLength(String length) {
        int total = 0;
        try {
            int hour = Integer.parseInt(length.substring(0, 2));
            int minutes = Integer.parseInt(length.substring(3, 5));
            int seconds = Integer.parseInt(length.substring(6, 8));
            int millisecond = Integer.parseInt(length.substring(9, 12));
            total = (hour * 3600 + minutes * 60 + seconds) * 1000 + millisecond;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return String.valueOf(total);
    }

    /**
     * @param separator 拆分字符依据
     * @param str       要拆分的字符
     * @return 拆分后得到的数组
     */
    public static String[] convertStrToArray(String str, String separator, int size) {
        String[] strArray = null;
        if (size > 0) {
            strArray = str.split(separator, size);
        } else {
            strArray = str.split(separator);
        }
        return strArray;
    }

    /**
     * 对String字符串进行排序
     *
     * @param str String字符串
     * @return
     */
    public static String sortStringBuilder(String str) {
        String[] strArray = convertStrToArray(str.toString(), ",", -1);
        Arrays.sort(strArray);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            builder.append(strArray[i] + ",");
        }
        return builder.toString();
    }

    /**
     * 小数点后如果是纯零，只保留整数
     *
     * @param aDouble
     * @return
     */
    public static String cleanDecimalZero(Double aDouble) {
        try {
            if (!aDouble.toString().equals("NaN") && !aDouble.toString().equals("null")) {
                String[] tempS = aDouble.toString().split("\\.");
                if (Integer.parseInt(tempS[1]) == 0) {
                    return tempS[0];
                }
                return aDouble.toString();
            } else {
                return "null";
            }
        } catch (Exception e) {
            return aDouble.toString();
        }
    }

    /**
     * 转换字符串中的HTML特殊字符
     */
    public static String escapeSpecialHTML(String str) {
        if (str != null) {
            str = str.replaceAll("<br>|<br/>", "\n");
            str = str.replaceAll("&amp;", "&");
            str = str.replaceAll("&quot;", "\"");
            str = str.replaceAll("&lt;", "<");
            str = str.replaceAll("&gt;", ">");
            str = str.replaceAll("&nbsp;", " ");
            str = str.replaceAll("&frasl;", "/");
        }
        return str;
    }

    /**
     * 判断两个时间点是否是一天
     */
    public static boolean isSameDay(long currTime, long showTime) {
        Date currData = new Date(currTime);
        Date showData = new Date(showTime);
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(currData);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(showData);
        return nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)
                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)
                && nowCalendar.get(Calendar.DATE) == dateCalendar.get(Calendar.DATE);
    }

    public static String getDateStr(long l){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date(l);
        return simpleDateFormat.format(date);
    }

    public static long getDateLong(String s){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
