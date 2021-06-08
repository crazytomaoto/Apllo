package com.sz.apollo.ui.utils.qrUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.zxing.WriterException;
import com.google.zxing.client.android.encoding.EncodingHandler;
import com.google.zxing.client.android.ui.CaptureActivity;
import com.sz.apollo.ui.utils.DimenUtils;
import com.sz.apollo.ui.utils.PermissionUtils;

/**
 * @author benny
 * on 2017/10/26.
 * 功能：
 */


public class ScanUtils {
    public static final int SCAN_CODE = 2007;
    /**
     * 数据扫描成功
     */
    public static final String SCAN_DATA = "qr_scan_result";

    /**
     * 二维码扫描
     *
     * @param fragment 上下文
     */
    public static void scan(final Fragment fragment) {
        String[] permissions = PermissionUtils.getCamera();
        if (PermissionUtils.checkPermissions(fragment.getActivity(), permissions)) {
            fragment.requestPermissions(permissions, PermissionUtils.REQUEST_CODE);
        } else {
            Intent intent = new Intent(fragment.getActivity(), CaptureActivity.class);
            fragment.startActivityForResult(intent, SCAN_CODE);
        }
    }

    /**
     * 生成二维码
     *
     * @param data      数据源
     * @param imageView 显示控件
     * @return
     */
    public static boolean creatQr(String data, ImageView imageView) {
        if (TextUtils.isEmpty(data)) {
            return false;
        }
        Bitmap mBitmap = null;
        try {
            mBitmap = EncodingHandler.createQRCode(data, 500);
            if (mBitmap != null) {
                imageView.setImageBitmap(mBitmap);
                return true;
            }
        } catch (WriterException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 添加logo的二维码
     *
     * @param data
     * @param bitmap
     * @return
     */
    public static Bitmap creatLogoQr(String data, Bitmap bitmap) {
        Bitmap qrCode = null;
        qrCode = EncodingHandler.createQRCode(data, DimenUtils.dp2px(200), DimenUtils.dp2px(200), bitmap);
        if (null != qrCode) {
            return qrCode;
        }
        return null;

    }
}
