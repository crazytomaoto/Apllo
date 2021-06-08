package com.sz.apollo.ui.models;

import java.util.List;

public class NoticeBean {
    /**
     * code : 200
     * data : {"total":1,"data":[{"noticeId":1,"title":"测试","body":"测试1","url":"http://www.baidu.com","state":0,"createdAt":1593597260096}]}
     * success : true
     * message : OK
     */

    private int code;
    private DataBeanX data;
    private boolean success;
    private String message;

    public NoticeBean() {
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
         * data : [{"noticeId":1,"title":"测试","body":"测试1","url":"http://www.baidu.com","state":0,"createdAt":1593597260096}]
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
             * noticeId : 1
             * title : 测试
             * body : 测试1
             * url : http://www.baidu.com
             * state : 0
             * createdAt : 1593597260096
             */

            private int noticeId;
            private String title;
            private String body;
            private String url;
            private int state;
            private long createdAt;

            public int getNoticeId() {
                return noticeId;
            }

            public void setNoticeId(int noticeId) {
                this.noticeId = noticeId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
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


