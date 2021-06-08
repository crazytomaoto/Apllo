package com.sz.apollo.ui.models;

import java.util.List;

public class NboPollBean {
    /**
     * code : 200
     * data : {"profitSum":1706.79973,"profitlogList":[{"createdAt":1597305546,"extensionProfit":"1352.477995","extensionSum":1907219.693297,"extensionUser":597100.16639,"id":396,"nissanSum":"8640.000000","profitSum":1706,"rankingProfit":354.321734,"rankingSum":317,"rankingUser":26,"state":0,"userAddress":"3rvap4gf2wps"}],"holdingNumber":100,"optimumNmber":10000}
     * success : true
     * message : OK
     * timestamp : 1597305842367
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public NboPollBean() {
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
         * profitSum : 1706.79973
         * profitlogList : [{"createdAt":1597305546,"extensionProfit":"1352.477995","extensionSum":1907219.693297,"extensionUser":597100.16639,"id":396,"nissanSum":"8640.000000","profitSum":1706,"rankingProfit":354.321734,"rankingSum":317,"rankingUser":26,"state":0,"userAddress":"3rvap4gf2wps"}]
         * holdingNumber : 100
         * optimumNmber : 10000.0
         */

        private double profitSum;
        private int holdingNumber;
        private double optimumNmber;
        private List<ProfitlogListBean> profitlogList;

        public double getProfitSum() {
            return profitSum;
        }

        public void setProfitSum(double profitSum) {
            this.profitSum = profitSum;
        }

        public int getHoldingNumber() {
            return holdingNumber;
        }

        public void setHoldingNumber(int holdingNumber) {
            this.holdingNumber = holdingNumber;
        }

        public double getOptimumNmber() {
            return optimumNmber;
        }

        public void setOptimumNmber(double optimumNmber) {
            this.optimumNmber = optimumNmber;
        }

        public List<ProfitlogListBean> getProfitlogList() {
            return profitlogList;
        }

        public void setProfitlogList(List<ProfitlogListBean> profitlogList) {
            this.profitlogList = profitlogList;
        }

        public static class ProfitlogListBean {
            /**
             * createdAt : 1597305546
             * extensionProfit : 1352.477995
             * extensionSum : 1907219.693297
             * extensionUser : 597100.16639
             * id : 396
             * nissanSum : 8640.000000
             * profitSum : 1706
             * rankingProfit : 354.321734
             * rankingSum : 317
             * rankingUser : 26
             * state : 0
             * userAddress : 3rvap4gf2wps
             */

            private int createdAt;
            private String extensionProfit;
            private double extensionSum;
            private double extensionUser;
            private int id;
            private String nissanSum;
            private int profitSum;
            private double rankingProfit;
            private int rankingSum;
            private int rankingUser;
            private int state;
            private String userAddress;

            public int getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(int createdAt) {
                this.createdAt = createdAt;
            }

            public String getExtensionProfit() {
                return extensionProfit;
            }

            public void setExtensionProfit(String extensionProfit) {
                this.extensionProfit = extensionProfit;
            }

            public double getExtensionSum() {
                return extensionSum;
            }

            public void setExtensionSum(double extensionSum) {
                this.extensionSum = extensionSum;
            }

            public double getExtensionUser() {
                return extensionUser;
            }

            public void setExtensionUser(double extensionUser) {
                this.extensionUser = extensionUser;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNissanSum() {
                return nissanSum;
            }

            public void setNissanSum(String nissanSum) {
                this.nissanSum = nissanSum;
            }

            public int getProfitSum() {
                return profitSum;
            }

            public void setProfitSum(int profitSum) {
                this.profitSum = profitSum;
            }

            public double getRankingProfit() {
                return rankingProfit;
            }

            public void setRankingProfit(double rankingProfit) {
                this.rankingProfit = rankingProfit;
            }

            public int getRankingSum() {
                return rankingSum;
            }

            public void setRankingSum(int rankingSum) {
                this.rankingSum = rankingSum;
            }

            public int getRankingUser() {
                return rankingUser;
            }

            public void setRankingUser(int rankingUser) {
                this.rankingUser = rankingUser;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getUserAddress() {
                return userAddress;
            }

            public void setUserAddress(String userAddress) {
                this.userAddress = userAddress;
            }
        }
    }
}
