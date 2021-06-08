package com.common.project.utils.system;

import android.content.Context;
import android.util.TypedValue;

import com.common.project.config.CommonApp;

/**
 * @author  benny .
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
                dpVal, CommonApp.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int sp2px( float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, CommonApp.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float px2dp( float pxVal) {
        final float scale = CommonApp.getInstance().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp( float pxVal) {
        return (pxVal/CommonApp.getInstance().getResources().getDisplayMetrics().scaledDensity);
    }
}
