package com.sz.apollo.ui.models;

import java.util.List;

public class ImportApolloBean {
    /**
     * code : 200
     * data : {"account_names":["kskommacxhxs"]}
     * success : true
     * message : OK
     * timestamp : 1595306099846
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public ImportApolloBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        private List<String> account_names;

        public List<String> getAccount_names() {
            return account_names;
        }

        public void setAccount_names(List<String> account_names) {
            this.account_names = account_names;
        }
    }
}
