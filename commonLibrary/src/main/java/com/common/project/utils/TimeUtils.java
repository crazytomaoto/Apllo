package com.common.project.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author benny
 * @date 2018/12/13
 */

public class TimeUtils {
    public static String YEAR_MONTH_DAY_TIME = "yyyy-MM-dd HH:mm:ss";
    public static String YEAR_MONTH_DAY_TIME2 = "yyyy-MM-dd HH:mm";
    public static String YEAR_MONTH_DAY_TIME3 = "yyyy/MM/dd HH:mm";
    public static String YEAR_MONTH_DAY_TIME4 = "yyyy/MM/dd HH:mm:ss";
    public static String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static String YEAR_MONTH_DAY2 = "yyyy/-MM/dd";
    public static String HOURS_MINUTE_TIME = "HH:mm";
    public static String TIME_ZONE = "GMT+8";

    /**
     * 获取系统时间
     *
     * @param format
     */
    public static String getTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        // 获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        return sdf.format(curDate);
    }

    /**
     * 获取当前年月日 时分秒时间
     *
     * @return String  ( yyyy-MM-dd HH:mm:ss )
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY_TIME, Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return sdf.format(new Date());
    }

    /**
     * 获取当前年月日
     *
     * @return String
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY, Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return sdf.format(new Date());
    }

    /**
     * 传入时间戳返回时间
     *
     * @param time
     * @return
     */
    public static String getTimestampTime(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat(YEAR_MONTH_DAY_TIME2, Locale.CHINA);
        String times = sdr.format(new Date(time));
        if (getTime(YEAR_MONTH_DAY).equals(times.substring(0, 10))) {
            return times.substring(times.length() - 5, times.length());
        }
        return times;
    }

    /**
     * 传入时间戳返回时间
     *
     * @param time
     * @return
     */
    public static String getTimestampTime(long time,String format) {
        SimpleDateFormat sdr = new SimpleDateFormat(format, Locale.CHINA);
        String times = sdr.format(new Date(time));
        return times;
    }

    public static long getTimestampTime3(long time){
        SimpleDateFormat sdr = new SimpleDateFormat(YEAR_MONTH_DAY_TIME4, Locale.CHINA);
        String times = sdr.format(new Date(time));
        if(times.length()>3) {
            times = times.substring(0, times.length() - 2);
            times += "00";
        }
        try {
            Date date = sdr.parse(times);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimestampTime4(long time){
        SimpleDateFormat sdr = new SimpleDateFormat(YEAR_MONTH_DAY_TIME4, Locale.CHINA);
        return sdr.format(new Date(time));
    }

    /**
     * 传入日期（2018-10-10）返回时间戳（）
     *
     * @param time YmdTimestamp
     * @return
     */
    public static long getStampDate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date != null ? date.getTime() : 0;
    }

    /**
     * 传入日期（2018-10-10 00:00:00）返回时间戳（1232456465465）
     *
     * @param time YmdTimestamp
     * @return 时间戳
     */
    public static long getStampDateForHms(String time) {
        String p = YEAR_MONTH_DAY_TIME;
        //做兼容，防止后台又来一种/的模式时间。  2020年5月14日17:26:31
        if (!TextUtils.isEmpty(time) && time.contains("/")) {
            p = YEAR_MONTH_DAY_TIME4;
        }
        SimpleDateFormat sdr = new SimpleDateFormat(p, Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date != null ? date.getTime() : 0;
    }

    /**
     * 传入时间戳返回时分格式（HH:MM）
     *
     * @param time YmdTimestamp
     * @return
     */
    public static String getHmForTimestamp(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat(HOURS_MINUTE_TIME, Locale.CHINA);
        return sdr.format(new Date(time));
    }

    /**
     * 获取系统当前时间（13位，精确到毫秒）
     * @return
     */
    public static long getSysCurrentTime() {
        long currentTime = System.currentTimeMillis();
        return currentTime;
    }

    /**
     * 去除T和点
     * @param time
     * @return
     */
    public static String removePointT(String time) {
        String temp="";
        if (TextUtils.isEmpty(time)){
            return "";
        }
        temp=time.replace("T"," ");
        if (temp.contains(".")) {
            int index_D = time.indexOf(".");
            temp = temp.substring(0, index_D);
        }
        return temp;
    }

    /**
     * 把时间转换为：时分秒格式。
     *
     * @param second ：秒，传入单位为秒
     * @return
     */
    /**
     * 把时间转换为：时分秒格式。
     *
     * @param time
     * @return
     */
    public static String getTimeString(int time) {
        int miao = time % 60;
        int fen = time / 60;
        int hour = 0;
        if (fen >= 60) {
            hour = fen / 60;
            fen = fen % 60;
        }
        String timeString = "";
        String miaoString = "";
        String fenString = "";
        String hourString = "";
        if (miao < 10) {
            miaoString = "0" + miao;
        } else {
            miaoString = miao + "";
        }
        if (fen < 10) {
            fenString = "0" + fen;
        } else {
            fenString = fen + "";
        }
        if (hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = hour + "";
        }
        if (hour != 0) {
            timeString = hourString + ":" + fenString + ":" + miaoString;
        } else {
            timeString = fenString + ":" + miaoString;
        }
        return timeString;
    }

    /**
     * 传入日期（2018-10-10 11:55:54）返回时间戳（1321212312312）
     *
     * @param time YmdTimestamp
     * @return
     */
    public static long dateTimeToLong(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat(YEAR_MONTH_DAY_TIME, Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date != null ? date.getTime() : 0;
    }

    public static String dateToString(Date date){
        SimpleDateFormat sdr = new SimpleDateFormat("MM/dd HH:mm", Locale.CHINA);
        return sdr.format(date);
    }
}
