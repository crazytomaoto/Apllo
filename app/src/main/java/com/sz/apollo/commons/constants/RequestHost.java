package com.sz.apollo.commons.constants;

/**
 * Created by wty on 2019/10/15.
 */
public class RequestHost {

    public static String eosNetUrl = "http://8.210.2.117:5555/";
    //    public static String ethNetUrl = "https://hknode.token.pro/eth/";
    public static String ethNetUrl = "https://mainnet.infura.io/v3/90db82f412be4563b87302c6f571ad2e";

    public static String usdtContract = "0xdac17f958d2ee523a2206206994597c13d831ec7";
    public static String ethContract = "0xe790930cba3f049412cf9ea242c34208873ee0a0";
    public static String url = "https://api.apollo.ac/";//
    //    public static String browserUrl = "http://app.apollo.ac/#/home";
//    public static String browserUrl = "https://app.apollo.ac/";

    public static String browserUrl = "https://apscan.onechance.io/#/home";

    public static class Wallet {
        public static final String BASE = "wallet/";
        public static final String public_ = BASE + "public";//
    }

    public static class User {
        public static final String BASE = "user/";
        public static final String public_ = BASE + "public";//
    }

    public static class Common {
        public static final String BASE = "common/";
        public static final String PUBLICABC = BASE + "public";//
    }

    public static class Nbo {
        public static final String BASE = "nbo/";
        public static final String PUBLIC = BASE + "public";//
    }

    public static class DDAO {
        public static final String BASE = "ddao/";
        public static final String PUBLIC = BASE + "public";//
    }

    public static class LON {
        public static final String BASE = "lon/";
        public static final String PUBLIC = BASE + "public";//
    }

    public static class WebUrls {
        public static final String helpCenter = "https://apolloinc.zendesk.com/hc/zh-cn";
        public static final String contactUs = "https://apolloinc.zendesk.com/hc/zh-cn/requests/new";

        public static final String introduceNbo = "https://apolloinc.zendesk.com/hc/zh-cn/categories/360000160156-%E4%BB%80%E4%B9%88%E6%98%AFNBO-";
        public static final String introduceDdao = "https://apolloinc.zendesk.com/hc/zh-cn/categories/360000159835-%E4%BB%80%E4%B9%88%E6%98%AFDDAO-";
        public static final String introduceLon = "https://apolloinc.zendesk.com/hc/zh-cn/categories/360000159815-%E4%BB%80%E4%B9%88%E6%98%AFLON-";
    }
}