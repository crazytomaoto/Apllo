package com.sz.apollo.ui.models;

import java.util.List;

public class NboProfitDetailBean {
    /**
     * code : 200
     * data : {"total":1,"data":[{"createdAt":1597661100,"currency":"NBO","extensionProfit":29.216,"extensionSum":1.18044372836E7,"extensionUser":104021.4004,"id":412,"nissanSum":6640,"profitSum":35.627,"rankingProfit":6.411,"rankingSum":395127,"rankingUser":763,"state":0,"txId":null,"updatedAt":1597661100,"userAddress":"yry4ejzqi3ih"}]}
     * success : true
     * message : OK
     * timestamp : 1597754414101
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;
    private long timestamp;

    public NboProfitDetailBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
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

    public static class DataBeanX {
        /**
         * total : 1
         * data : [{"createdAt":1597661100,"currency":"NBO","extensionProfit":29.216,"extensionSum":1.18044372836E7,"extensionUser":104021.4004,"id":412,"nissanSum":6640,"profitSum":35.627,"rankingProfit":6.411,"rankingSum":395127,"rankingUser":763,"state":0,"txId":null,"updatedAt":1597661100,"userAddress":"yry4ejzqi3ih"}]
         */

        private int total;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * createdAt : 1597661100
             * currency : NBO
             * extensionProfit : 29.216
             * extensionSum : 1.18044372836E7
             * extensionUser : 104021.4004
             * id : 412
             * nissanSum : 6640.0
             * profitSum : 35.627
             * rankingProfit : 6.411
             * rankingSum : 395127
             * rankingUser : 763
             * state : 0
             * txId : null
             * updatedAt : 1597661100
             * userAddress : yry4ejzqi3ih
             */

            private int createdAt;
            private String currency;
            private double extensionProfit;
            private double extensionSum;
            private double extensionUser;
            private int id;
            private double nissanSum;
            private double profitSum;
            private double rankingProfit;
            private int rankingSum;
            private int rankingUser;
            private int state;
            private Object txId;
            private int updatedAt;
            private String userAddress;

            public int getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(int createdAt) {
                this.createdAt = createdAt;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public double getExtensionProfit() {
                return extensionProfit;
            }

            public void setExtensionProfit(double extensionProfit) {
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

            public double getNissanSum() {
                return nissanSum;
            }

            public void setNissanSum(double nissanSum) {
                this.nissanSum = nissanSum;
            }

            public double getProfitSum() {
                return profitSum;
            }

            public void setProfitSum(double profitSum) {
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

            public Object getTxId() {
                return txId;
            }

            public void setTxId(Object txId) {
                this.txId = txId;
            }

            public int getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(int updatedAt) {
                this.updatedAt = updatedAt;
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
