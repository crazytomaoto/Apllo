package com.google.zxing.client.android.utils;

import android.text.TextUtils;

import com.common.project.config.CommonApp;
import com.common.project.utils.ToastUtils;
import com.common.project.utils.json.GsonUtils;
import com.google.zxing.client.android.R;


/**
 * @author by benny
 * @date on 2018/7/25.
 * @function 二维码操作类
 */

public class QrUtils {

    /**
     * 二维码标识
     */
    public static int QR_TYPE = 0;

    /**
     *
     * @param bean QrBean
     * @return String
     *
     * 封装json数据
     */
    public static String getQrCodeString(QrBean bean) {
        if (bean == null) {
            return "";
        }
        return GsonUtils.toJson(bean);

    }

    /**
     * 解析二维码
     *
     * @param data
     * @return
     */
    public static QrBean getBean(String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        try {
            return GsonUtils.fromJson(data, QrBean.class);
        } catch (Exception e) {
            ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_qr_error));
        }
        return null;
    }

}
