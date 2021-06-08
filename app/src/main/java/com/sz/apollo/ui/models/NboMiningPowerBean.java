package com.sz.apollo.ui.models;

import java.util.List;

public class NboMiningPowerBean {
    /**
     * code : 200
     * data : {"total":2,"data":[{"childAddress":"baaaaaaa","totalAoc":10},{"childAddress":"aaaaaaaa","totalAoc":10}]}
     * success : true
     * message : OK
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;

    public NboMiningPowerBean() {
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
         * total : 2
         * data : [{"childAddress":"baaaaaaa","totalAoc":10},{"childAddress":"aaaaaaaa","totalAoc":10}]
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
             * childAddress : baaaaaaa
             * totalAoc : 10.0
             */

            private String childAddress;
            private double totalAoc;
            private int miningCount;

            public int getMiningCount() {
                return miningCount;
            }

            public void setMiningCount(int miningCount) {
                this.miningCount = miningCount;
            }

            public String getChildAddress() {
                return childAddress;
            }

            public void setChildAddress(String childAddress) {
                this.childAddress = childAddress;
            }

            public double getTotalAoc() {
                return totalAoc;
            }

            public void setTotalAoc(double totalAoc) {
                this.totalAoc = totalAoc;
            }
        }
    }
}
