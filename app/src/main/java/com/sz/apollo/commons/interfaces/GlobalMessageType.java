package com.sz.apollo.commons.interfaces;

public interface GlobalMessageType {

    /**
     * 通用模块消息类型
     */
    interface CommonMessageType {
        int BASE = 0x10000000;
        /**
         * 用户Token过期
         */
        int USER_TOKEN_EXPIRE = BASE + 1;
        int DIALOG_SUCCESS = BASE + 2;//成功提示弹窗，1.5秒跳转
        int DIALOG_ONEBUTTON = BASE + 3;//提示弹窗，确定按钮
        int DIALOG_TOAST = BASE + 4;//首页刷新时间
        int DIALOG_SUCCESS_START = BASE + 5;//成功提示弹窗，1.5秒跳转
        int DIALOG_SUCCESS_START_MINING = BASE + 6;//成功提示弹窗，1.5秒跳转
        int DIALOG_SUCCESS_TWO = BASE + 7;//成功提示弹窗，1.5秒跳转
        int GUIDE_DELAY = BASE + 8;//启动页延迟
        int REQUEST_ERROR = BASE + 9;//请求失败
    }

    interface MainRequest {
        int BASE = 0x11000000;
        int NET_ERROR = BASE + 7;//断网
        int NET_OK = BASE + 8;//断网
    }

    interface RequestCode {
        int BASE = 0x12000000;
        int ERROR = BASE + 1;
        int CREATEAPOLLOACCOUNT = BASE + 2;
        int LOGIN_AGAIN = BASE + 3;//重新登陆
        int GETETHBALANCE = BASE + 4;
        int GETETHBALANCE_ERROR = BASE + 5;
        int GETUSDTBALANCE = BASE + 6;
        int GETUSDTALANCE_ERROR = BASE + 7;
        int GETAPOLLOBALANCE = BASE + 8;
        int GETTRANSRECORD = BASE + 9;
        int GETACCOUNTS = BASE + 10;
        int SENDEOS = BASE + 11;
        int WARD_LIST = BASE + 12;
        int QUERY_CHILD = BASE + 13;
        int SYMBOL_PRICE = BASE + 14;
        int QUERY_POWER = BASE + 15;
        int QUERY_NOTICE = BASE + 16;
        int ACCOUNT_STATE = BASE + 17;
        int FLASH_RATE = BASE + 18;
        int FLASH_LIST = BASE + 19;
        int BASE_POWER_LIST = BASE + 20;
        int TIME_POWER_LIST = BASE + 21;
        int QUERY_SHARE = BASE + 22;
        int ETH_TRANS_RECORD = BASE + 23;
        int GET_RECORD_BY_ADDRESS = BASE + 24;
        int GET_AOT_MODEL_LIST = BASE + 25;
        int GET_TRANS_BY_HAH = BASE + 26;
        int FLASH_EXCHANGE = BASE + 27;
        int IMPORT_ACCOUNT = BASE + 28;
        int TRANS_LIST_APOLLO = BASE + 29;
        int TRANS_DETAIL = BASE + 30;
        int ADDRESS_STATE = BASE + 31;
        int LATEST_VERSION = BASE + 32;
        int GET_WEB_INFO = BASE + 33;
        int GET_BLOCK = BASE + 34;
        int ACCOUNT_IS_EXIT = BASE + 35;
        int WITHDRAW = BASE + 36;
        int GETNBOBALANCE = BASE + 37;
        int GETNBOBALANCE_HOME = BASE + 38;
        int GETAPOLLOBALANCE_NOHOME = BASE + 39;
        int OREPOOL = BASE + 40;
        int COMPUTATIONALPOWER = BASE + 41;
        int OREPOOLDETAILS = BASE + 42;
        int COINSTATE = BASE + 43;
        int ENABLENBOMINE = BASE + 44;
        int CHECKFLASHNBO = BASE + 45;
        int POWERINFO = BASE + 46;
        int WITHDRAWPROFIT = BASE + 47;
        int SENDUAOT = BASE + 48;
        int SENDUNBO = BASE + 49;
        int APOLLO_BALANCE = BASE + 50;
        int APOLLO_ONE_KIND_BALANCE = BASE + 51;
    }

}