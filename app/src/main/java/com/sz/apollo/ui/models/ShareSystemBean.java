package com.sz.apollo.ui.models;

import java.util.List;

public class ShareSystemBean {
    /**
     * code : 200
     * data : {"childList":[{"address":"ta54jeq3hrcu","allTotalPower":null,"balance":1.8351,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694249,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"kskommacxhxs","allTotalPower":null,"balance":8.691,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694250,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"jmvydndtlpc1","allTotalPower":null,"balance":9.372,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694251,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"cxgvjtm2gksm","allTotalPower":null,"balance":2,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694251,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"duvgoqlh4qbb","allTotalPower":null,"balance":1,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596722154,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"fruhvt5fk2kr","allTotalPower":null,"balance":0.1,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596787548,"timePower":0,"totalPower":null,"totalProfit":null}],"totalChild":9}
     * success : true
     * message : OK
     * timestamp : 1596789992657
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public ShareSystemBean() {
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
         * childList : [{"address":"ta54jeq3hrcu","allTotalPower":null,"balance":1.8351,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694249,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"kskommacxhxs","allTotalPower":null,"balance":8.691,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694250,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"jmvydndtlpc1","allTotalPower":null,"balance":9.372,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694251,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"cxgvjtm2gksm","allTotalPower":null,"balance":2,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596694251,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"duvgoqlh4qbb","allTotalPower":null,"balance":1,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596722154,"timePower":0,"totalPower":null,"totalProfit":null},{"address":"fruhvt5fk2kr","allTotalPower":null,"balance":0.1,"blockHeight":null,"blockRewardDay":null,"commonPower":0,"leaderLevel":0,"leaderPower":0,"level":0,"powerRate":null,"sharePower":0,"startTime":1596787548,"timePower":0,"totalPower":null,"totalProfit":null}]
         * totalChild : 9
         */

        private int totalChild;
        private List<ChildListBean> childList;

        public int getTotalChild() {
            return totalChild;
        }

        public void setTotalChild(int totalChild) {
            this.totalChild = totalChild;
        }

        public List<ChildListBean> getChildList() {
            return childList;
        }

        public void setChildList(List<ChildListBean> childList) {
            this.childList = childList;
        }

        public static class ChildListBean {
            /**
             * address : ta54jeq3hrcu
             * allTotalPower : null
             * balance : 1.8351
             * blockHeight : null
             * blockRewardDay : null
             * commonPower : 0.0
             * leaderLevel : 0
             * leaderPower : 0.0
             * level : 0
             * powerRate : null
             * sharePower : 0.0
             * startTime : 1596694249
             * timePower : 0.0
             * totalPower : null
             * totalProfit : null
             */

            private String address;
            private Object allTotalPower;
            private double balance;
            private Object blockHeight;
            private Object blockRewardDay;
            private double commonPower;
            private int leaderLevel;
            private double leaderPower;
            private int level;
            private Object powerRate;
            private double sharePower;
            private int startTime;
            private double timePower;
            private Object totalPower;
            private Object totalProfit;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getAllTotalPower() {
                return allTotalPower;
            }

            public void setAllTotalPower(Object allTotalPower) {
                this.allTotalPower = allTotalPower;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public Object getBlockHeight() {
                return blockHeight;
            }

            public void setBlockHeight(Object blockHeight) {
                this.blockHeight = blockHeight;
            }

            public Object getBlockRewardDay() {
                return blockRewardDay;
            }

            public void setBlockRewardDay(Object blockRewardDay) {
                this.blockRewardDay = blockRewardDay;
            }

            public double getCommonPower() {
                return commonPower;
            }

            public void setCommonPower(double commonPower) {
                this.commonPower = commonPower;
            }

            public int getLeaderLevel() {
                return leaderLevel;
            }

            public void setLeaderLevel(int leaderLevel) {
                this.leaderLevel = leaderLevel;
            }

            public double getLeaderPower() {
                return leaderPower;
            }

            public void setLeaderPower(double leaderPower) {
                this.leaderPower = leaderPower;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public Object getPowerRate() {
                return powerRate;
            }

            public void setPowerRate(Object powerRate) {
                this.powerRate = powerRate;
            }

            public double getSharePower() {
                return sharePower;
            }

            public void setSharePower(double sharePower) {
                this.sharePower = sharePower;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public double getTimePower() {
                return timePower;
            }

            public void setTimePower(double timePower) {
                this.timePower = timePower;
            }

            public Object getTotalPower() {
                return totalPower;
            }

            public void setTotalPower(Object totalPower) {
                this.totalPower = totalPower;
            }

            public Object getTotalProfit() {
                return totalProfit;
            }

            public void setTotalProfit(Object totalProfit) {
                this.totalProfit = totalProfit;
            }
        }
    }
}
