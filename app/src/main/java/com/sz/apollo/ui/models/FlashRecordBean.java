package com.sz.apollo.ui.models;

import java.util.List;

public class FlashRecordBean {
    /**
     * code : 200
     * data : {"total":4,"data":[{"amount":1.101,"createdAt":1596273746,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":11,"lastAmount":11.01,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0x3e7aaa01ec6a2826d78af8463678d0108b74d4a039638e567c28752e359e544c","type":1},{"amount":2,"createdAt":1596275952,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":12,"lastAmount":20,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0xb77bc139984efa0f47950c90bf36a9e6b1899b4d49303464f45fe3e06d337905","type":1},{"amount":2,"createdAt":1596283676,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":17,"lastAmount":20,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0xe762db3bde3cdf96d79c5ca23a3e432f13cb7ca981c4f099687c5ff47f6467d9","type":1},{"amount":2,"createdAt":1596360557,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":18,"lastAmount":20,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0xd51404831989fc81f3e711f8b630d813289614e9a5484c7503f06c0b58ad877d","type":1}]}
     * success : true
     * message : OK
     * timestamp : 1596360886164
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;
    private long timestamp;

    public FlashRecordBean() {
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
         * total : 4
         * data : [{"amount":1.101,"createdAt":1596273746,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":11,"lastAmount":11.01,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0x3e7aaa01ec6a2826d78af8463678d0108b74d4a039638e567c28752e359e544c","type":1},{"amount":2,"createdAt":1596275952,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":12,"lastAmount":20,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0xb77bc139984efa0f47950c90bf36a9e6b1899b4d49303464f45fe3e06d337905","type":1},{"amount":2,"createdAt":1596283676,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":17,"lastAmount":20,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0xe762db3bde3cdf96d79c5ca23a3e432f13cb7ca981c4f099687c5ff47f6467d9","type":1},{"amount":2,"createdAt":1596360557,"fee":0,"fromAddress":"0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3","id":18,"lastAmount":20,"lastTxId":null,"price":"0.1000","status":0,"symbol":"AOT","toAddress":"1lwrb1wgr3o3","txId":"0xd51404831989fc81f3e711f8b630d813289614e9a5484c7503f06c0b58ad877d","type":1}]
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
             * amount : 1.101
             * createdAt : 1596273746
             * fee : 0.0
             * fromAddress : 0xa3c62f8bbaa7434c45a1dd598f3bbc2d09419cf3
             * id : 11
             * lastAmount : 11.01
             * lastTxId : null
             * price : 0.1000
             * status : 0
             * symbol : AOT
             * toAddress : 1lwrb1wgr3o3
             * txId : 0x3e7aaa01ec6a2826d78af8463678d0108b74d4a039638e567c28752e359e544c
             * type : 1
             */

            private double amount;
            private long createdAt;
            private double fee;
            private String fromAddress;
            private int id;
            private double lastAmount;
            private Object lastTxId;
            private String price;
            private int status;
            private String symbol;
            private String toAddress;
            private String txId;
            private int type;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(long createdAt) {
                this.createdAt = createdAt;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public String getFromAddress() {
                return fromAddress;
            }

            public void setFromAddress(String fromAddress) {
                this.fromAddress = fromAddress;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getLastAmount() {
                return lastAmount;
            }

            public void setLastAmount(double lastAmount) {
                this.lastAmount = lastAmount;
            }

            public Object getLastTxId() {
                return lastTxId;
            }

            public void setLastTxId(Object lastTxId) {
                this.lastTxId = lastTxId;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public String getToAddress() {
                return toAddress;
            }

            public void setToAddress(String toAddress) {
                this.toAddress = toAddress;
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
        }
    }
}
