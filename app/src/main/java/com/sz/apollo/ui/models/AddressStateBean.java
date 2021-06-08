package com.sz.apollo.ui.models;

public class AddressStateBean {
    /**
     * code : 200
     * data : {"msg":"未激活","enable":false}
     * success : true
     * message : OK
     * timestamp : 1596535851522
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public AddressStateBean() {
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
        /**
         * msg : 未激活
         * enable : false
         */

        private String msg;
        private boolean enable;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
