package com.sz.apollo.ui.models;

public class AddressSharePowerBean {
    /**
     * code : 200
     * data : {"firstChildCount":1,"validChildCount":0,"allCommonPower":0,"maxAddressTier":1,"validAddressTier":"0"}
     * success : true
     * message : OK
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;

    public AddressSharePowerBean() {
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
         * firstChildCount : 1
         * validChildCount : 0
         * allCommonPower : 0.0
         * maxAddressTier : 1
         * validAddressTier : 0
         */
        private double sharePower;
        private int firstChildCount;
        private int validChildCount;
        private double allCommonPower;
        private int maxAddressTier;
        private String validAddressTier;

        public double getSharePower() {
            return sharePower;
        }

        public void setSharePower(double sharePower) {
            this.sharePower = sharePower;
        }

        public int getFirstChildCount() {
            return firstChildCount;
        }

        public void setFirstChildCount(int firstChildCount) {
            this.firstChildCount = firstChildCount;
        }

        public int getValidChildCount() {
            return validChildCount;
        }

        public void setValidChildCount(int validChildCount) {
            this.validChildCount = validChildCount;
        }

        public double getAllCommonPower() {
            return allCommonPower;
        }

        public void setAllCommonPower(double allCommonPower) {
            this.allCommonPower = allCommonPower;
        }

        public int getMaxAddressTier() {
            return maxAddressTier;
        }

        public void setMaxAddressTier(int maxAddressTier) {
            this.maxAddressTier = maxAddressTier;
        }

        public String getValidAddressTier() {
            return validAddressTier;
        }

        public void setValidAddressTier(String validAddressTier) {
            this.validAddressTier = validAddressTier;
        }
    }
}
