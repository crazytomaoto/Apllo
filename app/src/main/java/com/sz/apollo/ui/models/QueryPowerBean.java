package com.sz.apollo.ui.models;

public class QueryPowerBean {
    /**
     * code : 200
     * data : {"address":"a","level":4,"leaderLevel":1,"commonPower":500,"timePower":0,"sharePower":10611.84,"leaderPower":1447.2,"totalPower":12559.04,"allTotalPower":72932,"powerRate":0.17220205,"totalProfit":11307.3408,"startTime":1593405480120,"blockHeight":0,"blockRewardDay":69120}
     * success : true
     * message : OK
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;

    public QueryPowerBean() {
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
         * address : a
         * level : 4
         * leaderLevel : 1
         * commonPower : 500.0
         * timePower : 0.0
         * sharePower : 10611.84
         * leaderPower : 1447.2
         * totalPower : 12559.04
         * allTotalPower : 72932.0
         * powerRate : 0.17220205
         * totalProfit : 11307.3408
         * startTime : 1593405480120
         * blockHeight : 0
         * blockRewardDay : 69120
         */

        private String address;
        private int level;
        private int leaderLevel;
        private double commonPower;
        private double timePower;
        private double sharePower;
        private double leaderPower;
        private double totalPower;
        private double allTotalPower;
        private double powerRate;
        private double totalProfit;
        private long startTime;
        private int blockHeight;
        private int blockRewardDay;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLeaderLevel() {
            return leaderLevel;
        }

        public void setLeaderLevel(int leaderLevel) {
            this.leaderLevel = leaderLevel;
        }

        public double getCommonPower() {
            return commonPower;
        }

        public void setCommonPower(double commonPower) {
            this.commonPower = commonPower;
        }

        public double getTimePower() {
            return timePower;
        }

        public void setTimePower(double timePower) {
            this.timePower = timePower;
        }

        public double getSharePower() {
            return sharePower;
        }

        public void setSharePower(double sharePower) {
            this.sharePower = sharePower;
        }

        public double getLeaderPower() {
            return leaderPower;
        }

        public void setLeaderPower(double leaderPower) {
            this.leaderPower = leaderPower;
        }

        public double getTotalPower() {
            return totalPower;
        }

        public void setTotalPower(double totalPower) {
            this.totalPower = totalPower;
        }

        public double getAllTotalPower() {
            return allTotalPower;
        }

        public void setAllTotalPower(double allTotalPower) {
            this.allTotalPower = allTotalPower;
        }

        public double getPowerRate() {
            return powerRate;
        }

        public void setPowerRate(double powerRate) {
            this.powerRate = powerRate;
        }

        public double getTotalProfit() {
            return totalProfit;
        }

        public void setTotalProfit(double totalProfit) {
            this.totalProfit = totalProfit;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public int getBlockHeight() {
            return blockHeight;
        }

        public void setBlockHeight(int blockHeight) {
            this.blockHeight = blockHeight;
        }

        public int getBlockRewardDay() {
            return blockRewardDay;
        }

        public void setBlockRewardDay(int blockRewardDay) {
            this.blockRewardDay = blockRewardDay;
        }
    }
}
