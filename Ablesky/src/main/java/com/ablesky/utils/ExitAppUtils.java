package com.ablesky.utils;

import android.app.Activity;
import java.util.LinkedList;
import java.util.List;

/**
 * 退出app的工具类，使用单例模式
 * 1.在Activity的onCreate()的方法中调用addActivity()方法添加到mActivityList
 * 2.你可以在Activity的onDestroy()的方法中调用delActivity()来删除已经销毁的Activity实例
 * 这样避免了mActivityList容器中有多余的实例而影响程序退出速度
 *
 * Created by liangzhen on 2018/1/23.
 */

public class ExitAppUtils {

    /**
     * 转载Activity的容器
     */
    private List<Activity> mActivityList = new LinkedList<>();
    private static ExitAppUtils instance = new ExitAppUtils();

    /**
     * 将构造函数私有化
     */
    private ExitAppUtils() {
    }

    /**
     * 获取ExitAppUtils的实例，保证只有一个ExitAppUtils实例存在
     *
     * @return
     */
    public static ExitAppUtils getInstance() {
        return instance;
    }


    /**
     * 添加Activity实例到mActivityList中，在onCreate()中调用
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    /**
     * 从容器中删除多余的Activity实例，在onDestroy()中调用
     *
     * @param activity
     */
    public void delActivity(Activity activity) {
        mActivityList.remove(activity);
    }


    /**
     * 退出程序的方法
     */
    public void exit() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }

        System.exit(0);
    }

    /**
     * 清除所有的Activity
     */
    public void clearAllActivity() {
        for (int i = 0; i < mActivityList.size(); i++) {
            Activity activity = mActivityList.get(i);
            activity.finish();
        }
    }

    /**
     * 判断mActivityList中是否包含某个activity
     *
     * @param aClass 需要判断的activity
     */
    public boolean isExistActivity(Class<?> aClass) {
        for (int i = 0; i < mActivityList.size(); i++) {
            if (mActivityList.get(i).getClass().equals(aClass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断MainActivity在栈顶
     */
    public Activity isOnTop(Class<?> aClass) {
        for (int i = mActivityList.size() - 1; i >= 0; i--) {
            if (mActivityList.get(mActivityList.size() - 1).getClass().equals(aClass)) {
                return mActivityList.get(i);
            }
        }
        return null;
    }

    /**
     * 返回栈顶的Activity
     */
    public Activity getTopActivity() {
        if (mActivityList.size() > 0) {
            return mActivityList.get(mActivityList.size() - 1);
        }
        return null;
    }
}
