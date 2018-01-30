package com.ablesky.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liangzhen on 2018/1/30.
 */

public class ThreadPoolUtil {

    private static ExecutorService instance = null;

    private ThreadPoolUtil() {}

    public static ExecutorService getInstance() {
        if (instance == null)
            instance = Executors.newCachedThreadPool();
        return instance;
    }
}
