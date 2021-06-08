package com.sz.apollo.ui.models;

import java.util.List;

public class NboProfitDetail {
    /**
     * code : 200
     * data : {"profitlogList":[{"id":268,"userAddress":"3rvap4gf2wps","rankingSum":838,"rankingUser":42,"rankingProfit":216.515506,"extensionSum":2878264.376696,"extensionUser":618107.836515,"extensionProfit":"927.720821","nissanSum":"8640.000000","profitSum":1144,"createdAt":"2020-08-12T07:43:12.000+0000","state":0},{"id":311,"userAddress":"3rvap4gf2wps","rankingSum":838,"rankingUser":42,"rankingProfit":216.515506,"extensionSum":2878264.376696,"extensionUser":618107.836515,"extensionProfit":"927.720821","nissanSum":"8640.000000","profitSum":1144,"createdAt":"2020-08-12T08:43:15.000+0000","state":0}]}
     * success : true
     * message : OK
     * timestamp : 1597233382899
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public NboProfitDetail() {
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
        private List<ProfitlogListBean> profitlogList;

        public List<ProfitlogListBean> getProfitlogList() {
            return profitlogList;
        }

        public void setProfitlogList(List<ProfitlogListBean> profitlogList) {
            this.profitlogList = profitlogList;
        }

        public static class ProfitlogListBean {
            /**
             * id : 268
             * userAddress : 3rvap4gf2wps
             * rankingSum : 838
             * rankingUser : 42
             * rankingProfit : 216.515506
             * extensionSum : 2878264.376696
             * extensionUser : 618107.836515
             * extensionProfit : 927.720821
             * nissanSum : 8640.000000
             * profitSum : 1144
             * createdAt : 2020-08-12T07:43:12.000+0000
             * state : 0
             */

            private int id;
            private String userAddress;
            private int rankingSum;
            private int rankingUser;
            private double rankingProfit;
            private double extensionSum;
            private double extensionUser;
            private String extensionProfit;
            private String nissanSum;
            private int profitSum;
            private long createdAt;
            private int state;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserAddress() {
                return userAddress;
            }

            public void setUserAddress(String userAddress) {
                this.userAddress = userAddress;
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

            public double getRankingProfit() {
                return rankingProfit;
            }

            public void setRankingProfit(double rankingProfit) {
                this.rankingProfit = rankingProfit;
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

            public String getExtensionProfit() {
                return extensionProfit;
            }

            public void setExtensionProfit(String extensionProfit) {
                this.extensionProfit = extensionProfit;
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

            public long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(long createdAt) {
                this.createdAt = createdAt;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
