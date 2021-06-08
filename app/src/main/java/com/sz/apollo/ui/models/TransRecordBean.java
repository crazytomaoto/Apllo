package com.sz.apollo.ui.models;

import java.util.List;

public class TransRecordBean {
    /**
     * code : 200
     * data : {"total":1,"data":[{"createdAt":1596424288,"amount":1,"otherAddress":"kskommacxhxs","txId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","id":2,"state":1,"type":2}]}
     * success : true
     * message : OK
     * timestamp : 1596425510123
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;
    private long timestamp;

    public TransRecordBean() {
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
         * data : [{"createdAt":1596424288,"amount":1,"otherAddress":"kskommacxhxs","txId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","id":2,"state":1,"type":2}]
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
             * createdAt : 1596424288
             * amount : 1.0
             * otherAddress : kskommacxhxs
             * txId : 4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25
             * id : 2
             * state : 1
             * type : 2
             */

            private int createdAt;
            private double amount;
            private String otherAddress;
            private String txId;
            private int id;
            private int state;
            private int type;

            public int getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(int createdAt) {
                this.createdAt = createdAt;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getOtherAddress() {
                return otherAddress;
            }

            public void setOtherAddress(String otherAddress) {
                this.otherAddress = otherAddress;
            }

            public String getTxId() {
                return txId;
            }

            public void setTxId(String txId) {
                this.txId = txId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
