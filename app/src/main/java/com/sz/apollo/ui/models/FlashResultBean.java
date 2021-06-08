package com.sz.apollo.ui.models;

public class FlashResultBean {
    /**
     * code : 200
     * success : true
     * message : 闪兑成功
     * timestamp : 1596273746347
     */

    private int code;
    private boolean success;
    private String message;
    private long timestamp;

    public FlashResultBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
