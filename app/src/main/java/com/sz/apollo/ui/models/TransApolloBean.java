package com.sz.apollo.ui.models;

public class TransApolloBean {
    /**
     * code : 200
     * data : 1cbcff72f354137f88be9fab013eaabe17b1aefc8999266d10d94efd8be0659b
     * success : true
     * message : 发送交易成功
     * timestamp : 1595904924225
     */

    private int code;
    private String data;
    private boolean success;
    private String message;
    private long timestamp;

    public TransApolloBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
