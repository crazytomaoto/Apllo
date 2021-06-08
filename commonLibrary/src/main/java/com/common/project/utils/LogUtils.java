package com.common.project.utils;

import android.util.Log;

/**
 * 打印日志
 *
 * @author benny
 * @date 2018/12/13
 */
public class LogUtils {
    public static boolean isLog = true;

    /**
     * info的日志
     *
     * @param info 信息
     */
    public static void i(String info) {
        if (!isLog) {
            return;
        }
        Log.i("====info", info);
    }

    /**
     * info的日志
     *
     * @param info 信息
     */
    public static void i(String TAG, String info) {
        if (!isLog) {
            return;
        }
        Log.i(TAG + "====info", info);
    }

    /**
     * debug的日志
     *
     * @param debug debug
     */
    public static void d(String debug) {
        if (!isLog) {
            return;
        }
        Log.e("====debug", debug);
    }

    /**
     * error的日志
     *
     * @param error 错误
     */
    public static void e(String error) {
        if (!isLog) {
            return;
        }
        Log.e("====error", error);
    }
    /**
     * error的日志
     *
     * @param error 错误
     */
    public static void e(String TAG,String error) {
        if (!isLog) {
            return;
        }
        Log.e("TAG ====error", error);
    }

    /**
     * push log
     *
     * @param error
     */
    public static void e2(String error) {
        if (!isLog) {
            return;
        }
        Log.e("pushData：", error);
    }

    /**
     * warn的日志
     *
     * @param warn 异常
     */
    public static void w(String warn) {
        if (!isLog) {
            return;
        }
        Log.w("====warn", warn);
    }

    /**
     * verbose模式,打印最详细的日志
     *
     * @param verbose 打印最详细的日志
     */
    public static void v(String verbose) {
        if (!isLog) {
            return;
        }
        Log.v("====verbose", verbose);
    }
}
