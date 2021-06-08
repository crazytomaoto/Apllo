package com.sz.apollo.ui.models;

import java.util.List;

public class TimePowerBean {
    /**
     * code : 200
     * data : {"timePower":0,"timeRules":[{"codeDesc":"工时等级1","codeGroup":"timeRule","codeKey":"timeFirst","codeType":0,"codeValue":"6","codeValue2":"0","configId":3,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级2","codeGroup":"timeRule","codeKey":"timeSecond","codeType":0,"codeValue":"12","codeValue2":"0.06","configId":4,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级3","codeGroup":"timeRule","codeKey":"timeThird","codeType":0,"codeValue":"18","codeValue2":"0.13","configId":5,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级4","codeGroup":"timeRule","codeKey":"timeFour","codeType":0,"codeValue":"24","codeValue2":"0.2","configId":6,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级5","codeGroup":"timeRule","codeKey":"timeFive","codeType":0,"codeValue":"30","codeValue2":"0.27","configId":7,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级6","codeGroup":"timeRule","codeKey":"timeSix","codeType":0,"codeValue":"99999","codeValue2":"0.34","configId":8,"createdAt":null,"updatedAt":null}],"days":482400}
     * success : true
     * message : OK
     * timestamp : 1596790779870
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;
    private long timestamp;

    public TimePowerBean() {
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class DataBean {
        /**
         * timePower : 0.0
         * timeRules : [{"codeDesc":"工时等级1","codeGroup":"timeRule","codeKey":"timeFirst","codeType":0,"codeValue":"6","codeValue2":"0","configId":3,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级2","codeGroup":"timeRule","codeKey":"timeSecond","codeType":0,"codeValue":"12","codeValue2":"0.06","configId":4,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级3","codeGroup":"timeRule","codeKey":"timeThird","codeType":0,"codeValue":"18","codeValue2":"0.13","configId":5,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级4","codeGroup":"timeRule","codeKey":"timeFour","codeType":0,"codeValue":"24","codeValue2":"0.2","configId":6,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级5","codeGroup":"timeRule","codeKey":"timeFive","codeType":0,"codeValue":"30","codeValue2":"0.27","configId":7,"createdAt":null,"updatedAt":null},{"codeDesc":"工时等级6","codeGroup":"timeRule","codeKey":"timeSix","codeType":0,"codeValue":"99999","codeValue2":"0.34","configId":8,"createdAt":null,"updatedAt":null}]
         * days : 482400
         */

        private double timePower;
        private int days;
        private List<TimeRulesBean> timeRules;

        public double getTimePower() {
            return timePower;
        }

        public void setTimePower(double timePower) {
            this.timePower = timePower;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public List<TimeRulesBean> getTimeRules() {
            return timeRules;
        }

        public void setTimeRules(List<TimeRulesBean> timeRules) {
            this.timeRules = timeRules;
        }

        public static class TimeRulesBean {
            /**
             * codeDesc : 工时等级1
             * codeGroup : timeRule
             * codeKey : timeFirst
             * codeType : 0
             * codeValue : 6
             * codeValue2 : 0
             * configId : 3
             * createdAt : null
             * updatedAt : null
             */

            private String codeDesc;
            private String codeGroup;
            private String codeKey;
            private int codeType;
            private String codeValue;
            private String codeValue2;
            private int configId;
            private Object createdAt;
            private Object updatedAt;

            public String getCodeDesc() {
                return codeDesc;
            }

            public void setCodeDesc(String codeDesc) {
                this.codeDesc = codeDesc;
            }

            public String getCodeGroup() {
                return codeGroup;
            }

            public void setCodeGroup(String codeGroup) {
                this.codeGroup = codeGroup;
            }

            public String getCodeKey() {
                return codeKey;
            }

            public void setCodeKey(String codeKey) {
                this.codeKey = codeKey;
            }

            public int getCodeType() {
                return codeType;
            }

            public void setCodeType(int codeType) {
                this.codeType = codeType;
            }

            public String getCodeValue() {
                return codeValue;
            }

            public void setCodeValue(String codeValue) {
                this.codeValue = codeValue;
            }

            public String getCodeValue2() {
                return codeValue2;
            }

            public void setCodeValue2(String codeValue2) {
                this.codeValue2 = codeValue2;
            }

            public int getConfigId() {
                return configId;
            }

            public void setConfigId(int configId) {
                this.configId = configId;
            }

            public Object getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Object createdAt) {
                this.createdAt = createdAt;
            }

            public Object getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(Object updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }
}
