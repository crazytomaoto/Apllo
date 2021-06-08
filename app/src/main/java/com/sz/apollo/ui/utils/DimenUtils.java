package com.sz.apollo.ui.utils;

import android.util.TypedValue;

import com.sz.apollo.application.ApolloApplication;

/**
 * @author benny .
 * Time on 2017/3/3 .
 * 描述：单位转换工具类
 */

public class DimenUtils {
    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, ApolloApplication.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, ApolloApplication.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float px2dp(float pxVal) {
        final float scale = ApolloApplication.getInstance().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal) {
        return (pxVal / ApolloApplication.getInstance().getResources().getDisplayMetrics().scaledDensity);
    }
}
