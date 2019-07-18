package com.lin.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther : lin
 * @date : 2019/7/5 17:12
 * @desc :
 */
public class ThreadUtils {
    private static final ExecutorService es = Executors.newFixedThreadPool(10);

    public static void execute(Runnable runnable) {
        es.submit(runnable);
    }

    public static ExecutorService getEs() {
        return es;
    }
}