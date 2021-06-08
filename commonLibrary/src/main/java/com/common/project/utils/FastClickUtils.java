package com.common.project.utils;

/**
 * 防止过快点击工具类
 * @author pengfaming
 * @version V1.0
 * @date 2019/6/21 8:57
 */
public class FastClickUtils {
    /**
     * 上次点击的时间
     * */
    private static long lastCheckTime = 0L;

    /**
     * 是否过快点击
     * */
    public static boolean isFastClick(){
        long currentTime = System.currentTimeMillis();
        //是否允许点击
        boolean isFastClick = currentTime - lastCheckTime < 1000;
        lastCheckTime = currentTime;
        return isFastClick;
    }
}
