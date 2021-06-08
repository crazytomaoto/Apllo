package com.common.project.utils;

import android.graphics.Color;

import com.common.project.config.CommonApp;

/**
 * @author by benny
 * @date on 2018/9/5.
 * @function 颜色值
 */

public class AppColor {
    public static int getColor(int colorId) {
        return CommonApp.getInstance().getResources().getColor(colorId);
    }

    public static int getColor(String colorString) {
        return Color.parseColor(colorString);
    }

    /**
     * 图标配色
     *
     * @param i
     * @return
     */
    public static int getColor16(int i) {
        int mColor = 0;
        switch (i) {
            case 0:
                //红
                mColor = 0xFFDE345B;
                break;
            case 1:
                //橙
                mColor = 0xFFF18200;
                break;
            case 2:
                //黄
                mColor = 0xFFFEF104;
                break;
            case 3:
                //绿
                mColor = 0xFF009946;
                break;
            case 4:
                mColor = 0xFF141927;   //0xFF141927;
                break;
            case 5:
                //蓝
                mColor = 0xFF02B5E9;
                break;
            case 6:
                //紫色
                mColor = 0xff383191;
                break;
            case 7:
                //浅紫色
                mColor = 0xFFB970A7;
                break;
            case 8:
                //主题辅色
                mColor = 0xFFCCDEF9;
                break;
            case 9:
                //黄绿
                mColor = 0xFF88C027;
                break;
            case 10:
                //深蓝
                mColor = 0xFF262EDB;
                break;
            case 11:
                //蓝紫
                mColor = 0xFF0168B7;
                break;
            default:
                mColor = 0xFF286CA1;
                break;
        }

        return mColor;
    }
}
