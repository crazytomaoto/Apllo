package com.sz.apollo.ui.models;

import java.util.List;

public class WebInfoBean {
    /**
     * code : 200
     * data : {"accountNum":5468,"transNum":82012,"recent":[{"date":"2020-08-05","count":0},{"date":"2020-08-04","count":6},{"date":"2020-08-03","count":4},{"date":"2020-08-02","count":0},{"date":"2020-08-01","count":0},{"date":"2020-07-31","count":0},{"date":"2020-07-30","count":0}],"height":10271909}
     * success : true
     * message : OK
     * timestamp : 1596619088206
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public WebInfoBean() {
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
         * accountNum : 5468
         * transNum : 82012
         * recent : [{"date":"2020-08-05","count":0},{"date":"2020-08-04","count":6},{"date":"2020-08-03","count":4},{"date":"2020-08-02","count":0},{"date":"2020-08-01","count":0},{"date":"2020-07-31","count":0},{"date":"2020-07-30","count":0}]
         * height : 10271909
         */

        private int accountNum;
        private int transNum;
        private int height;
        private List<RecentBean> recent;

        public int getAccountNum() {
            return accountNum;
        }

        public void setAccountNum(int accountNum) {
            this.accountNum = accountNum;
        }

        public int getTransNum() {
            return transNum;
        }

        public void setTransNum(int transNum) {
            this.transNum = transNum;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public List<RecentBean> getRecent() {
            return recent;
        }

        public void setRecent(List<RecentBean> recent) {
            this.recent = recent;
        }

        public static class RecentBean {
            /**
             * date : 2020-08-05
             * count : 0
             */

            private String date;
            private int count;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
