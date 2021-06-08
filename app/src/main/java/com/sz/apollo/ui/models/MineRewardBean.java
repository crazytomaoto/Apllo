package com.sz.apollo.ui.models;

import java.util.List;

public class MineRewardBean {
    /**
     * code : 200
     * data : {"total":8,"data":[{"address":"1lwrb1wgr3o3","createdAt":1596601090,"rewardAmount":0.4403,"rewardSymbol":"AOC","state":2,"txId":"c70d9f6346b8b9170437737480eb6d2d95f40a4fc35c6ecf9ee6175c05fd8ea1","updatedAt":1596802607,"userAddressId":52482,"userRewardId":74704},{"address":"1lwrb1wgr3o3","createdAt":1593660621,"rewardAmount":0.7046,"rewardSymbol":"AOC","state":2,"txId":"a3f26d8e2933a7f72d5b8c32f09a5d01345f1d4d6987ff7f0d2338299e022eaa","updatedAt":1596802191,"userAddressId":52482,"userRewardId":74705},{"address":"1lwrb1wgr3o3","createdAt":1593574046,"rewardAmount":0.7072,"rewardSymbol":"AOC","state":0,"txId":null,"updatedAt":1593574046,"userAddressId":52482,"userRewardId":74706},{"address":"1lwrb1wgr3o3","createdAt":1593488357,"rewardAmount":0.6763,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593488357,"userAddressId":52482,"userRewardId":74707},{"address":"1lwrb1wgr3o3","createdAt":1593401598,"rewardAmount":0.6895,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593401598,"userAddressId":52482,"userRewardId":74708},{"address":"1lwrb1wgr3o3","createdAt":1593315754,"rewardAmount":0.696,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593315754,"userAddressId":52482,"userRewardId":74709},{"address":"1lwrb1wgr3o3","createdAt":1593229556,"rewardAmount":0.7,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593229556,"userAddressId":52482,"userRewardId":74710},{"address":"1lwrb1wgr3o3","createdAt":1593143268,"rewardAmount":0.7013,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593143268,"userAddressId":52482,"userRewardId":74711}]}
     * success : true
     * message : OK
     * timestamp : 1596802608934
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;
    private long timestamp;

    public MineRewardBean() {
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
         * total : 8
         * data : [{"address":"1lwrb1wgr3o3","createdAt":1596601090,"rewardAmount":0.4403,"rewardSymbol":"AOC","state":2,"txId":"c70d9f6346b8b9170437737480eb6d2d95f40a4fc35c6ecf9ee6175c05fd8ea1","updatedAt":1596802607,"userAddressId":52482,"userRewardId":74704},{"address":"1lwrb1wgr3o3","createdAt":1593660621,"rewardAmount":0.7046,"rewardSymbol":"AOC","state":2,"txId":"a3f26d8e2933a7f72d5b8c32f09a5d01345f1d4d6987ff7f0d2338299e022eaa","updatedAt":1596802191,"userAddressId":52482,"userRewardId":74705},{"address":"1lwrb1wgr3o3","createdAt":1593574046,"rewardAmount":0.7072,"rewardSymbol":"AOC","state":0,"txId":null,"updatedAt":1593574046,"userAddressId":52482,"userRewardId":74706},{"address":"1lwrb1wgr3o3","createdAt":1593488357,"rewardAmount":0.6763,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593488357,"userAddressId":52482,"userRewardId":74707},{"address":"1lwrb1wgr3o3","createdAt":1593401598,"rewardAmount":0.6895,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593401598,"userAddressId":52482,"userRewardId":74708},{"address":"1lwrb1wgr3o3","createdAt":1593315754,"rewardAmount":0.696,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593315754,"userAddressId":52482,"userRewardId":74709},{"address":"1lwrb1wgr3o3","createdAt":1593229556,"rewardAmount":0.7,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593229556,"userAddressId":52482,"userRewardId":74710},{"address":"1lwrb1wgr3o3","createdAt":1593143268,"rewardAmount":0.7013,"rewardSymbol":"AOC","state":2,"txId":null,"updatedAt":1593143268,"userAddressId":52482,"userRewardId":74711}]
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
             * address : 1lwrb1wgr3o3
             * createdAt : 1596601090
             * rewardAmount : 0.4403
             * rewardSymbol : AOC
             * state : 2
             * txId : c70d9f6346b8b9170437737480eb6d2d95f40a4fc35c6ecf9ee6175c05fd8ea1
             * updatedAt : 1596802607
             * userAddressId : 52482
             * userRewardId : 74704
             */

            private String address;
            private int createdAt;
            private double rewardAmount;
            private String rewardSymbol;
            private int state;
            private String txId;
            private int updatedAt;
            private int userAddressId;
            private int userRewardId;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(int createdAt) {
                this.createdAt = createdAt;
            }

            public double getRewardAmount() {
                return rewardAmount;
            }

            public void setRewardAmount(double rewardAmount) {
                this.rewardAmount = rewardAmount;
            }

            public String getRewardSymbol() {
                return rewardSymbol;
            }

            public void setRewardSymbol(String rewardSymbol) {
                this.rewardSymbol = rewardSymbol;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getTxId() {
                return txId;
            }

            public void setTxId(String txId) {
                this.txId = txId;
            }

            public int getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(int updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int getUserAddressId() {
                return userAddressId;
            }

            public void setUserAddressId(int userAddressId) {
                this.userAddressId = userAddressId;
            }

            public int getUserRewardId() {
                return userRewardId;
            }

            public void setUserRewardId(int userRewardId) {
                this.userRewardId = userRewardId;
            }
        }
    }
}
