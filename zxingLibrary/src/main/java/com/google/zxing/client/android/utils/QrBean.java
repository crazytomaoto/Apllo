package com.google.zxing.client.android.utils;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @author by benny
 * @date on 2018/7/25.
 * @function 二维码数据都用这个对象封装
 */

public class QrBean implements Serializable{
    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
