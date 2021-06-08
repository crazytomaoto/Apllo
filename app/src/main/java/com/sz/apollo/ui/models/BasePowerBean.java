package com.sz.apollo.ui.models;

import java.util.List;

public class BasePowerBean {
    /**
     * code : 200
     * data : {"total":1,"data":[{"recordId":1,"userAddressId":1,"address":"a","balance":1000,"validBalance":1000,"powerRate":0.04,"commonPower":40,"createdAt":1593597260096}]}
     * success : true
     * message : OK
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;

    public BasePowerBean() {
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

    public static class DataBeanX {
        /**
         * total : 1
         * data : [{"recordId":1,"userAddressId":1,"address":"a","balance":1000,"validBalance":1000,"powerRate":0.04,"commonPower":40,"createdAt":1593597260096}]
         */

        private int total;
        private double commonPower;
        private List<DataBean> data;

        public double getCommonPower() {
            return commonPower;
        }

        public void setCommonPower(double commonPower) {
            this.commonPower = commonPower;
        }

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
             * recordId : 1
             * userAddressId : 1
             * address : a
             * balance : 1000.0
             * validBalance : 1000.0
             * powerRate : 0.04
             * commonPower : 40.0
             * createdAt : 1593597260096
             */

            private int recordId;
            private int userAddressId;
            private String address;
            private double balance;
            private double validBalance;
            private double powerRate;
            private double commonPower;
            private long createdAt;

            public int getRecordId() {
                return recordId;
            }

            public void setRecordId(int recordId) {
                this.recordId = recordId;
            }

            public int getUserAddressId() {
                return userAddressId;
            }

            public void setUserAddressId(int userAddressId) {
                this.userAddressId = userAddressId;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getValidBalance() {
                return validBalance;
            }

            public void setValidBalance(double validBalance) {
                this.validBalance = validBalance;
            }

            public double getPowerRate() {
                return powerRate;
            }

            public void setPowerRate(double powerRate) {
                this.powerRate = powerRate;
            }

            public double getCommonPower() {
                return commonPower;
            }

            public void setCommonPower(double commonPower) {
                this.commonPower = commonPower;
            }

            public long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(long createdAt) {
                this.createdAt = createdAt;
            }
        }
    }
}
