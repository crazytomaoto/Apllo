package com.sz.apollo.ui.models;

public class VersionBean {
    /**
     * code : 200
     * data : {"appVersionId":1,"appPath":"aaa","appVersionCode":1,"appVersionName":"V1.0.0","appDesc":"更新了bug","appType":1,"state":1,"createdAt":15908765554,"updatedAt":15908765554}
     * success : true
     * message : OK
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;

    public VersionBean() {
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
         * appVersionId : 1
         * appPath : aaa
         * appVersionCode : 1
         * appVersionName : V1.0.0
         * appDesc : 更新了bug
         * appType : 1
         * state : 1
         * createdAt : 15908765554
         * updatedAt : 15908765554
         */

        private int appVersionId;
        private String appPath;
        private int appVersionCode;
        private String appVersionName;
        private String appDesc;
        private int appType;
        private int state;
        private long createdAt;
        private long updatedAt;

        public int getAppVersionId() {
            return appVersionId;
        }

        public void setAppVersionId(int appVersionId) {
            this.appVersionId = appVersionId;
        }

        public String getAppPath() {
            return appPath;
        }

        public void setAppPath(String appPath) {
            this.appPath = appPath;
        }

        public int getAppVersionCode() {
            return appVersionCode;
        }

        public void setAppVersionCode(int appVersionCode) {
            this.appVersionCode = appVersionCode;
        }

        public String getAppVersionName() {
            return appVersionName;
        }

        public void setAppVersionName(String appVersionName) {
            this.appVersionName = appVersionName;
        }

        public String getAppDesc() {
            return appDesc;
        }

        public void setAppDesc(String appDesc) {
            this.appDesc = appDesc;
        }

        public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
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

        public long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
