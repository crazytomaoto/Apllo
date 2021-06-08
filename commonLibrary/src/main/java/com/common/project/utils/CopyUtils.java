package com.common.project.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

import com.common.project.R;
import com.common.project.config.CommonApp;

/**
 * 复制工具
 * @author by benny
 * @date on 2019/5/15.
 */
public class CopyUtils {

    /**
     * 辅复制
     * */
    public static void copy(String text) {
        if (TextUtils.isEmpty(text)) {
            ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_fzsb));
            return;
        }
        ClipboardManager clip = (ClipboardManager) CommonApp.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
        //clip.getText(); // 粘贴
        // 复制
        ClipData myClip;
        myClip = ClipData.newPlainText("text", text);
        if (clip == null) {
            ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_fzsb));
            return;
        }
        clip.setPrimaryClip(myClip);
        ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_fzcg));
    }

    /***
     * 粘贴功能
     */
    public static String stickup() {

        // 粘贴板有数据，并且是文本
        String msg ="";
        ClipboardManager mClipboardManager = (ClipboardManager) CommonApp.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
        if (mClipboardManager.hasPrimaryClip()
                && mClipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            ClipData.Item item = mClipboardManager.getPrimaryClip().getItemAt(0);
            CharSequence text = item.getText();
            if (text == null) {
                return "";
            }
           msg = text.toString();
        }
        return msg;
    }


}
