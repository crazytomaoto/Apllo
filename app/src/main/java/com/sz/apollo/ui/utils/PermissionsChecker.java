package com.sz.apollo.ui.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

/**
 * 检查权限的工具类
 * <p/>
 *
 * @author wangchenlong on 16/1/26.
 */

public class PermissionsChecker {
    private final Context mContext;

    PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 判断权限集合
     */
    boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否缺少权限
     */
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }

    /***
     * 检测是否开启通知权限
     * @param context 上下文
     * @return true 开启 false 关闭
     */
    public static boolean checkNotifySetting(Context context) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        boolean isOpen = manager.areNotificationsEnabled();
        if (isOpen) {
            Log.d("通知权限已开启", "++");
        } else {
            Log.d("通知权限已关闭", "++");
        }
        return isOpen;
    }
}
