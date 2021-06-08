package com.sz.apollo.ui.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Looper;
import android.provider.Settings;
import android.widget.ImageView;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Date:2020/3/30
 * Time:10:54
 * author:wty
 * describe:
 */
public class Util {
    //此处代码可以放到StatusBarUtils
    public static int getStatusBarHeight(Context context) {
        int mStatusBarHeight = 0;
        if (mStatusBarHeight == 0) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                mStatusBarHeight = res.getDimensionPixelSize(resourceId);
            }
        }
        return mStatusBarHeight;
    }

    public static void copy(Activity activity, String content) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("content", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

    /**
     * 判断字符串是否由字母和数字组成（缺一不可）
     *
     * @param str
     * @return
     */
    public static boolean checkLoginPass(String str) {
        Pattern p1 = Pattern.compile("[a-zA-z]");
        Pattern p2 = Pattern.compile("[0-9]");
        if (p1.matcher(str).find() && p2.matcher(str).find()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getStringFromDecimal(double a, int scale) {
        String tempt = String.valueOf(a);
        BigDecimal bigDecimal = new BigDecimal(tempt);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.HALF_UP);
        return bigDecimal.toPlainString();
    }

    public static String getStringFromDecimal(String a, int scale) {
        BigDecimal bigDecimal = new BigDecimal(a);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.HALF_UP);
        return bigDecimal.toPlainString();
    }

    public static void setImageRs(ImageView iv, String coinType, boolean isSelected) {
        switch (coinType) {
            case "ALL":
                if (isSelected) {
                    iv.setImageResource(R.drawable.ic_all_wallet_logo_select_yes);
                } else iv.setImageResource(R.drawable.ic_all_wallet_logo_select_no);
                break;
            case "ETH":
                if (isSelected) {
                    iv.setImageResource(R.drawable.ic_eth_logo_select_yes);
                } else iv.setImageResource(R.drawable.ic_eth_logo_select_no);
                break;
            case "Apollo":
                if (isSelected) {
                    iv.setImageResource(R.drawable.ic_apl_logo_select_yes);
                } else iv.setImageResource(R.drawable.ic_apl_logo_select_no);
                break;
        }
    }

    public static void setImageRsForMoreToken(ImageView iv, String name) {
        switch (name) {
            case Constant.TYPE_ETH:
                iv.setImageResource(R.drawable.logo_eth2);
                break;
            case Constant.TYPE_U:
                iv.setImageResource(R.drawable.logo_usdt);
                break;
            case Constant.TYPE_AOT:
                iv.setImageResource(R.drawable.logo_aot);
                break;
            case Constant.TYPE_AOC:
                iv.setImageResource(R.drawable.logo_aoc);
                break;
            case Constant.TYPE_NBO:
                iv.setImageResource(R.drawable.ic_logo_nbo);
                break;
            case Constant.TYPE_DDAO:
                iv.setImageResource(R.drawable.ic_logo_ddao);
                break;
            case Constant.TYPE_LON:
                iv.setImageResource(R.drawable.ic_logo_lon);
                break;
        }
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static String getDeviceOnlyNum(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
    }

    /**
     * 从左边开始获取第一个不为0的索引
     *
     * @param num
     * @return
     */
    public static int getIndexNoneZore(String num) {
        char[] temp = num.toCharArray();
        int index = -1;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != '0') {
                index = i;
                break;
            }
        }
        return index;
    }

    public static boolean isMainLoop() {
        boolean ret;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    /**
     * String转map
     *
     * @param str_json
     * @return
     */
    public static Map<String, String> json2map(String str_json) {
        Map<String, String> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(str_json, new TypeToken<Map<String, String>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
        }
        return res;
    }
}
