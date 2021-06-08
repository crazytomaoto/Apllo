package com.sz.apollo.ui.models;

public class NboPowerInfoBean {

    /**
     * code : 200
     * data : {"profitSum":6594.106578,"extensionPower":1230000,"rankPower":0.1538}
     * success : true
     * message : OK
     * timestamp : 1597302992215
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public NboPowerInfoBean() {
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
         * profitSum : 6594.106578
         * extensionPower : 1230000.0
         * rankPower : 0.1538
         */

        private double profitSum;
        private double extensionPower;
        private double rankPower;

        public double getProfitSum() {
            return profitSum;
        }

        public void setProfitSum(double profitSum) {
            this.profitSum = profitSum;
        }

        public double getExtensionPower() {
            return extensionPower;
        }

        public void setExtensionPower(double extensionPower) {
            this.extensionPower = extensionPower;
        }

        public double getRankPower() {
            return rankPower;
        }

        public void setRankPower(double rankPower) {
            this.rankPower = rankPower;
        }
    }
}
