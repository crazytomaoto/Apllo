package com.sz.apollo.ui.utils;

/**
 * 为了防止用户或者测试MM疯狂的点击某个button
 * Created by Administrator on 2016/2/20 0020.
 */
public class ClickUtil {
    private static long lastClickTime;

    public static boolean isNotFirstClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 300) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
