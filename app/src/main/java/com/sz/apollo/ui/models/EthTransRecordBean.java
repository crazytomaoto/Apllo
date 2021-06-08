package com.sz.apollo.ui.models;

import java.util.List;

public class EthTransRecordBean {
    /**
     * code : 200
     * data : [{"id":1,"symbol":"USDT","txHash":"0x111111","contract":"guide","fromAddress":"abc","toAddress":"def","addressList":"sdsdsdsdsd","height":1000,"receivedTime":null,"confirmations":5,"totalInput":10,"totalOutput":10,"actualAmount":10,"fees":1,"label":null,"type":2,"series":"ETH","status":0,"createdAt":null}]
     * success : true
     * message : OK
     */

    private int code;
    private boolean success;
    private String message;
    private List<DataBean> data;

    public EthTransRecordBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * symbol : USDT
         * txHash : 0x111111
         * contract : guide
         * fromAddress : abc
         * toAddress : def
         * addressList : sdsdsdsdsd
         * height : 1000
         * receivedTime : null
         * confirmations : 5
         * totalInput : 10.0
         * totalOutput : 10.0
         * actualAmount : 10.0
         * fees : 1.0
         * label : null
         * type : 2
         * series : ETH
         * status : 0
         * createdAt : null
         */

        private int id;
        private String symbol;
        private String txHash;
        private String contract;
        private String fromAddress;
        private String toAddress;
        private String addressList;
        private int height;
        private Object receivedTime;
        private int confirmations;
        private double totalInput;
        private double totalOutput;
        private double actualAmount;
        private double fees;
        private Object label;
        private int type;
        private String series;
        private int status;
        private long createdAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getTxHash() {
            return txHash;
        }

        public void setTxHash(String txHash) {
            this.txHash = txHash;
        }

        public String getContract() {
            return contract;
        }

        public void setContract(String contract) {
            this.contract = contract;
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

        public String getAddressList() {
            return addressList;
        }

        public void setAddressList(String addressList) {
            this.addressList = addressList;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Object getReceivedTime() {
            return receivedTime;
        }

        public void setReceivedTime(Object receivedTime) {
            this.receivedTime = receivedTime;
        }

        public int getConfirmations() {
            return confirmations;
        }

        public void setConfirmations(int confirmations) {
            this.confirmations = confirmations;
        }

        public double getTotalInput() {
            return totalInput;
        }

        public void setTotalInput(double totalInput) {
            this.totalInput = totalInput;
        }

        public double getTotalOutput() {
            return totalOutput;
        }

        public void setTotalOutput(double totalOutput) {
            this.totalOutput = totalOutput;
        }

        public double getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(double actualAmount) {
            this.actualAmount = actualAmount;
        }

        public double getFees() {
            return fees;
        }

        public void setFees(double fees) {
            this.fees = fees;
        }

        public Object getLabel() {
            return label;
        }

        public void setLabel(Object label) {
            this.label = label;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }
    }
}
