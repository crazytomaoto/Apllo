package com.sz.apollo.ui.models;

import java.util.List;

public class ResonanceBean {
    /**
     * code : 200
     * data : {"data":[{"createdAt":null,"level":75,"modelId":75,"price":0.2084,"state":0,"surplusAmount":167059.6808,"surplusPercent":1,"totalAmount":167059.6808,"updatedAt":null,"usdtAmount":34815.2375,"useAmount":0},{"createdAt":null,"level":76,"modelId":76,"price":0.2105,"state":0,"surplusAmount":168730.2776,"surplusPercent":1,"totalAmount":168730.2776,"updatedAt":null,"usdtAmount":35517.7234,"useAmount":0},{"createdAt":null,"level":77,"modelId":77,"price":0.2126,"state":0,"surplusAmount":170417.5804,"surplusPercent":1,"totalAmount":170417.5804,"updatedAt":null,"usdtAmount":36230.7776,"useAmount":0},{"createdAt":null,"level":78,"modelId":78,"price":0.2147,"state":0,"surplusAmount":172121.7562,"surplusPercent":1,"totalAmount":172121.7562,"updatedAt":null,"usdtAmount":36954.5411,"useAmount":0},{"createdAt":null,"level":79,"modelId":79,"price":0.2168,"state":0,"surplusAmount":173842.9738,"surplusPercent":1,"totalAmount":173842.9738,"updatedAt":null,"usdtAmount":37689.1567,"useAmount":0}],"totalSurplus":7.512940319296E8}
     * success : true
     * message : OK
     * timestamp : 1596509485763
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;
    private long timestamp;

    public ResonanceBean() {
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
         * data : [{"createdAt":null,"level":75,"modelId":75,"price":0.2084,"state":0,"surplusAmount":167059.6808,"surplusPercent":1,"totalAmount":167059.6808,"updatedAt":null,"usdtAmount":34815.2375,"useAmount":0},{"createdAt":null,"level":76,"modelId":76,"price":0.2105,"state":0,"surplusAmount":168730.2776,"surplusPercent":1,"totalAmount":168730.2776,"updatedAt":null,"usdtAmount":35517.7234,"useAmount":0},{"createdAt":null,"level":77,"modelId":77,"price":0.2126,"state":0,"surplusAmount":170417.5804,"surplusPercent":1,"totalAmount":170417.5804,"updatedAt":null,"usdtAmount":36230.7776,"useAmount":0},{"createdAt":null,"level":78,"modelId":78,"price":0.2147,"state":0,"surplusAmount":172121.7562,"surplusPercent":1,"totalAmount":172121.7562,"updatedAt":null,"usdtAmount":36954.5411,"useAmount":0},{"createdAt":null,"level":79,"modelId":79,"price":0.2168,"state":0,"surplusAmount":173842.9738,"surplusPercent":1,"totalAmount":173842.9738,"updatedAt":null,"usdtAmount":37689.1567,"useAmount":0}]
         * totalSurplus : 7.512940319296E8
         */

        private double totalSurplus;
        private List<DataBean> data;

        public double getTotalSurplus() {
            return totalSurplus;
        }

        public void setTotalSurplus(double totalSurplus) {
            this.totalSurplus = totalSurplus;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * createdAt : null
             * level : 75
             * modelId : 75
             * price : 0.2084
             * state : 0
             * surplusAmount : 167059.6808
             * surplusPercent : 1.0
             * totalAmount : 167059.6808
             * updatedAt : null
             * usdtAmount : 34815.2375
             * useAmount : 0.0
             */

            private Object createdAt;
            private int level;
            private int modelId;
            private double price;
            private int state;
            private double surplusAmount;
            private double surplusPercent;
            private double totalAmount;
            private Object updatedAt;
            private double usdtAmount;
            private double useAmount;

            public Object getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Object createdAt) {
                this.createdAt = createdAt;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getModelId() {
                return modelId;
            }

            public void setModelId(int modelId) {
                this.modelId = modelId;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public double getSurplusAmount() {
                return surplusAmount;
            }

            public void setSurplusAmount(double surplusAmount) {
                this.surplusAmount = surplusAmount;
            }

            public double getSurplusPercent() {
                return surplusPercent;
            }

            public void setSurplusPercent(double surplusPercent) {
                this.surplusPercent = surplusPercent;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
            }

            public Object getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(Object updatedAt) {
                this.updatedAt = updatedAt;
            }

            public double getUsdtAmount() {
                return usdtAmount;
            }

            public void setUsdtAmount(double usdtAmount) {
                this.usdtAmount = usdtAmount;
            }

            public double getUseAmount() {
                return useAmount;
            }

            public void setUseAmount(double useAmount) {
                this.useAmount = useAmount;
            }
        }
    }
}
