package com.sz.apollo.ui.utils.qrUtil;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.sz.apollo.R;
import com.sz.apollo.ui.utils.ToastUtil;


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
     * @param bean QrBean
     * @return String
     * <p>
     * 封装json数据
     */
    public static String getQrCodeString(QrBean bean) {
        if (bean == null) {
            return "";
        }
        return JSON.toJSONString(bean);

    }

    /**
     * 解析二维码
     *
     * @param data
     * @return
     */
    public static QrBean getBean(Context context, String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        try {
            return JSON.parseObject(data, QrBean.class);
        } catch (Exception e) {
            ToastUtil.show(context, context.getResources().getString(R.string.str_qr_error));
        }
        return null;
    }

}
