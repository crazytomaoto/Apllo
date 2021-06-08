package com.sz.apollo.ui.models;

public class FlashRateBean {
    /**
     * code : 200
     * data : {"rate":10}
     * success : true
     * message : OK
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;

    public FlashRateBean() {
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

    public static class DataBean {
        /**
         * rate : 10.0
         */

        private double rate;

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }
}
