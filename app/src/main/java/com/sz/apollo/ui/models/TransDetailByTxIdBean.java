package com.sz.apollo.ui.models;

public class TransDetailByTxIdBean {
    /**
     * code : 200
     * data : {"recordId":1,"fromAddress":"aaa","toAddress":"bbb","amount":1,"symbol":"AOT","txId":"sssssssssssssssss","type":1,"state":1,"remark":"none","createdAt":159007754}
     * success : true
     * message : OK
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;

    public TransDetailByTxIdBean() {
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
         * recordId : 1
         * fromAddress : aaa
         * toAddress : bbb
         * amount : 1.0
         * symbol : AOT
         * txId : sssssssssssssssss
         * type : 1
         * state : 1
         * remark : none
         * createdAt : 159007754
         */

        private int recordId;
        private String fromAddress;
        private String toAddress;
        private double amount;
        private String symbol;
        private String txId;
        private int type;
        private int state;
        private String remark;
        private int createdAt;

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getTxId() {
            return txId;
        }

        public void setTxId(String txId) {
            this.txId = txId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(int createdAt) {
            this.createdAt = createdAt;
        }
    }
}
