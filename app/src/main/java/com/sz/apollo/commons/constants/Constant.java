package com.sz.apollo.commons.constants;

import java.math.BigInteger;

/**
 * Created by wty on 2017/8/22.
 */

public class Constant {

    /**
     * 请求超时时间
     */
    public static final int TIME_OUT_INT = 7 * 1000;
    public static final int REQUEST_CODE = 5 * 1001;//
    public static final int RESULT_CODE = 5 * 1002;//
    //登录
    public static final String TYPE_APOLLO = "Apollo";
    public static final String TYPE_ETH = "ETH";
    public static final String TYPE_U = "USDT";
    public static final String TYPE_AOT = "AOT";
    public static final String TYPE_AOC = "AOC";
    public static final String TYPE_NBO = "NBO";
    public static final String TYPE_DDAO = "DDAO";
    public static final String TYPE_LON = "LON";
    public static final String ETH_REMARK = "ETH WALLET";
    public static final String APOLLO_CONTRACT_ADDRESS = "token.aotc";
    public static final String NBO_CONTRACT_ADDRESS = "eosio.token";
    public static final String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6RNwBILF1PwEHHWRMJ38k3u5LzHaE8Euda7OGVM/bdZKUo5xAJlVNYoIxU3q8Ss1RdEV61J5GA1ztSbNbv5xKtzeBWUAR7QiQYynsfax1gvEXhKaGfkAShXuAR9+EAyhaLSweZnrawlMDcyTrdX2r9lpSfGCphrx2FNSCjf7+1QIDAQAB";
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(60000);
    public static final String RECEIVE_ADDRESS = "0x47FfDE893465B8aBacacF0b9607d784EFbBcb1dB";
    public static final String NEXADDRESS = "xzdg5a1drpoy";//未来交易所收款地址
    public static final String ALL_APOLLO_KINDS =
            TYPE_AOT + "," +
                    TYPE_AOC + "," +
                    TYPE_NBO + "," +
                    TYPE_DDAO + "," +
                    TYPE_LON;

    /**
     * SQL用
     */
    public static class SpConstant {
        /**
         * 钱包的键
         */
        public static final String FIRST = "FIRST";
        public static final String KINDTOKEN = "KINDTOKEN";
        public static final String WALLET_PASS = "WALLET_PASS";
        /**
         * 当前用户的信息
         */
        public static final String USER_TOKEN = "X-Apollo-Token";
        public static final String FINGER_PAY = "FINGER_PAY";
        /**
         * 当前用户语言
         */
        public static final String USER_LANGUANGE = "l";

        /**
         * 当前用户的手机号
         */

        public static class RequestCode {
//        int BASE = 0x10000000;
        }

        public static class BundleCode {
            int BASE = 0x1000;
            int ChooseCountry = BASE + 1;
        }
    }
}