package com.sz.apollo.ui.models;

public class CreateApolloBean {
    /**
     * code : 200
     * data : kkv2p2hoihja
     * success : true
     * message : OK
     * timestamp : 1595229268837
     */

    private int code;
    private String data;
    private boolean success;
    private String message;
    private long timestamp;

    public CreateApolloBean() {
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
