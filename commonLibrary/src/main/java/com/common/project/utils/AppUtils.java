package com.common.project.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.common.project.config.CommonApp;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * App相关辅助工具类
 *
 * @author binsky on 2016/7/31.
 */

public class AppUtils {
    private static final String TAG = "AppUtils";

    /**
     * 获取UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();

    }


    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取系统的版本
     *
     * @return
     */
    public static int getSystemVersion() {
        /*String phoneInfo="";
        phoneInfo = "Product: " + android.os.Build.PRODUCT;
               phoneInfo += ", CPU_ABI: " + android.os.Build.CPU_ABI;
                phoneInfo += ", TAGS: " + android.os.Build.TAGS;
                 phoneInfo += ", VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE;
                phoneInfo += ", MODEL: " + android.os.Build.MODEL;
               phoneInfo += ", SDK_INT: " + Build.VERSION.SDK_INT;
                phoneInfo += ", VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
                phoneInfo += ", DEVICE: " + android.os.Build.DEVICE;
                phoneInfo += ", DISPLAY: " + android.os.Build.DISPLAY;
                phoneInfo += ", BRAND: " + android.os.Build.BRAND;
                phoneInfo += ", BOARD: " + android.os.Build.BOARD;
                phoneInfo += ", FINGERPRINT: " + android.os.Build.FINGERPRINT;
                phoneInfo += ", ID: " + android.os.Build.ID;
                phoneInfo += ", MANUFACTURER: " + android.os.Build.MANUFACTURER;
               phoneInfo += ", USER: " + android.os.Build.USER;*/
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = CommonApp.getInstance().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = CommonApp.getInstance().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 获取Android设备ID（android的唯一标志）
     *
     * 需要动态权限: android.permission.READ_PHONE_STATE
     */

//    @SuppressLint("HardwareIds")
//    public static String getDeviceId() {
//        TelephonyManager telephonyManager = (TelephonyManager) CommonApp.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
//        String deviceId = null;
//        if (telephonyManager != null) {
//            deviceId = telephonyManager.getDeviceId();
//        }
//        return deviceId;
//    }

    /**
     * 获取类名
     */
    public static String getClassName() {
        ActivityManager manager = (ActivityManager) CommonApp.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo cinfo = runningTasks.get(0);
        ComponentName component = cinfo.topActivity;
        return component.getClassName();
    }

    /**
     * 获取系统语言
     *
     * @return
     */
    public static String getLanguage() {
        //获取系统当前使用的语言
        String mCurrentLanguage = Locale.getDefault().getLanguage();
        return mCurrentLanguage;
    }

    /**
     * 获取系统的版本
     *
     * @return
     */
    public static void getPhoneInfo() {
        String phoneInfo = "";
        phoneInfo = "Product: " + Build.PRODUCT;
        phoneInfo += ", CPU_ABI: " + Build.CPU_ABI;
        phoneInfo += ", TAGS: " + Build.TAGS;
        phoneInfo += ", VERSION_CODES.BASE: " + Build.VERSION_CODES.BASE;
        phoneInfo += ", MODEL: " + Build.MODEL;
        phoneInfo += ", SDK_INT: " + Build.VERSION.SDK_INT;
        phoneInfo += ", VERSION.RELEASE: " + Build.VERSION.RELEASE;
        phoneInfo += ", DEVICE: " + Build.DEVICE;
        phoneInfo += ", DISPLAY: " + Build.DISPLAY;
        phoneInfo += ", BRAND: " + Build.BRAND;
        phoneInfo += ", BOARD: " + Build.BOARD;
        phoneInfo += ", FINGERPRINT: " + Build.FINGERPRINT;
        phoneInfo += ", ID: " + Build.ID;
        phoneInfo += ", MANUFACTURER: " + Build.MANUFACTURER;
        phoneInfo += ", USER: " + Build.USER;
        Log.i(TAG, "getPhoneInfo: " + phoneInfo);
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取android系统版本
     *
     * @return
     */
    public static String getVersion() {
        return Build.VERSION.RELEASE;
    }


    /**
     * 获取IP地址
     *
     * @return
     */
    public static String getHostIP() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;
    }

}
