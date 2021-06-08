package com.sz.apollo.ui.models;

import java.util.List;

public class PriceBean {
    /**
     * code : 200
     * data : {"data":[{"symbol":"AOT","price":0.1},{"symbol":"AOC","price":0.05}]}
     * success : true
     * message : OK
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;

    public PriceBean() {
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * symbol : AOT
             * price : 0.1
             */

            private String symbol;
            private double price;

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }
    }
}
