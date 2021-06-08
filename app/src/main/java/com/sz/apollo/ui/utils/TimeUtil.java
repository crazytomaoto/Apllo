package com.sz.apollo.ui.utils;

import android.annotation.SuppressLint;

import com.hysd.android.platform_ub.utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtil {
    /**
     * @param time 秒
     * @return
     */
    public static String getTime(long time) {

        SimpleDateFormat format = null;
        try {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
            return "time error";
        } finally {
        }
        return format.format(new Date(time * 1000));//换成毫秒
    }

    /**
     * @param time 秒
     * @return
     */
    public static String getTime2(long time) {

        SimpleDateFormat format = null;
        try {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } catch (Exception e) {
            e.printStackTrace();
            return "time error";
        } finally {
        }
        return format.format(new Date(time * 1000));//换成毫秒
    }

    public static String getTime3(long time) {

        SimpleDateFormat format = null;
        try {
            format = new SimpleDateFormat("MM/dd");
        } catch (Exception e) {
            e.printStackTrace();
            return "time error";
        } finally {
        }
        return format.format(new Date(time * 1000));//换成毫秒
    }

    public static String getTime4(String time) {
        if (!StringUtils.isNEmpty(time)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("MM/dd");
            if (format != null) {
                Date date = null;
                try {
                    date = format.parse(time);
                    return format2.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String getDatePull() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(new Date());
    }
}
