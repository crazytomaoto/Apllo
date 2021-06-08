package com.common.project.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.common.project.R;
import com.common.project.config.CommonApp;


/**
 * Toast统一管理类
 * @author pfm
 */
public class ToastUtils {
    private static Toast topMsgToast;
    private static Toast topResToast;
    private static Toast midToast;


    /**
     * 自定义Toast ( 在顶部显示)
     * @param context 上下文
     * @param message  提示内容
     */
    public static void showTopToast(Context context, String message) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_top_view, null);
        //初始化布局控件
        TextView  mTextView = toastRoot.findViewById(R.id.toast_top_tvMessage);
        //为控件设置属性
        mTextView.setText(message);
        if (Build.VERSION.SDK_INT >= 28){
            //9.0以上toast直接用原生的方法即可，并不用setText防止重复的显示的问题
            //下列写法：是为了解决部分手机的提示语 前面 会出现  包名。  例如：  "创成汇：请输入密码"
            Toast toast = new Toast(CommonApp.getInstance());
            toast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(toastRoot);
            toast.show();
        } else {
            //Toast的初始化
            if(topMsgToast == null){
                topMsgToast = new Toast(context);
            }
            topMsgToast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL, 0, 0);
            topMsgToast.setDuration(Toast.LENGTH_SHORT);
            topMsgToast.setView(toastRoot);
            topMsgToast.show();
        }
    }
    /**
     * 自定义Toast ( 在顶部显示)
     * @param context 上下文
     * @param resId 资源Id  如 R.String.cancel
     */
    public static void showTopToast(Context context, @StringRes int resId) {
        //加载Toast布局
        View view = LayoutInflater.from(context).inflate(R.layout.toast_top_view, null);
        //初始化布局控件
        TextView mTextView = view.findViewById(R.id.toast_top_tvMessage);
        //为控件设置属性
        mTextView.setText(context.getResources().getString(resId));

        if (Build.VERSION.SDK_INT >= 28){
            //9.0以上toast直接用原生的方法即可，并不用setText防止重复的显示的问题
            //下列写法：是为了解决部分手机的提示语 前面 会出现  包名。  例如：  "创成汇：请输入密码"
            Toast toast = new Toast(CommonApp.getInstance());
            toast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        } else {
            //Toast的初始化
            if (topResToast == null) {
                topResToast = new Toast(context);
            }
            topResToast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL, 0, 0);
            topResToast.setDuration(Toast.LENGTH_SHORT);
            topResToast.setView(view);
            topResToast.show();
        }
    }

    /**
     * 自定义Toast (在中间显示)
     * @param message 消息内容 null
     */
    @SuppressLint("ShowToast")
    public static void showMidToast(String message){
        if (!TextUtils.isEmpty(message)) {
            if (Build.VERSION.SDK_INT >= 28){
                //9.0以上toast直接用原生的方法即可，并不用setText防止重复的显示的问题
                //下列写法：是为了解决部分手机的提示语 前面 会出现  包名。  例如：  "创成汇：请输入密码"
                Toast toast = Toast.makeText(CommonApp.getInstance(), message, Toast.LENGTH_SHORT);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                if (midToast == null) {
                    midToast = Toast.makeText(CommonApp.getInstance(), message, Toast.LENGTH_SHORT);
                }else {
                    midToast.setText(message);
                }
                midToast.setDuration(Toast.LENGTH_SHORT);
                midToast.setGravity(Gravity.CENTER, 0, 0);
                midToast.show();
            }
        }
    }
}
