package com.common.project.config;

/**
 *  Event广播标识
 * @author by benny
 * @date on 2019/3/15.
 */

public class EventConstant {
    /**
     * 无网络
     */
    public static int NO_NET=0;
    public static final int NET_STATUS=1;


    /**
     * 从首页我的按钮点击未登录
     * 然后去注册成功自动登录并登录成功
     * */
    public static final int MINE_REGISTER_LOGIN_SUCCESS = 100;
    /**
     * 回退到登录界面
     * */
    public static final int GO_BACK_TO_LOGIN = 101;

    /**
     * 登录成功
     * */
    public static final int LOGIN_SUCCESS = 102;

	public static final int ADD_ADDRESS_SUCCESS = 103;

    /**修改（新增或者删除）自选数据*/
    public static final int UPDATE_OPTION =105;

    /**
     * 从K线图点击买入卖出跳转到BB交易界面
     * */
    public static final int KLINE_TO_BB = 106;

    /** 修改登录密码*/
    public static final int UPDATE_LOGIN_PASS = 107;

    /**设置计价方式*/
    public static final int SETTING_PRICE_METHOD = 108;
    /**设置语言*/
    public static final int SETTING_LANGUAGE = 109;

    /** 线上线下环境切换 */
    public static final int CHANGE_ENVIRONMENT = 110;

    /**
     * 从K线图点击买入卖出跳转到杠杆交易界面
     * */
    public static final int KLINE_TO_LEVER = 111;

    //长链接socket-->
    /**
     * K线买卖5推送
     * */
    public static final int KLINE_BUY_SELL_PUSH = 998;
    /**
     * 个人用户推送
     * */
    public static final int SINGLE_USER = 1000;
    /**
     * 币种详情
     * */
    public static final int COIN_DETAILS = 1001;
    /**
     * 首页数据
     * */
    public static final int HOME_DETAILS = 1002;
    /**
     * 交易首页推送最新成交
     * */
    public static final int TRADE_HOME_LATEST_DEAL = 1003;
    /**
     * K线头部推送
     * */
    public static final int KLINE_TOP_INFO = 1004;
    /**
     * K线底部成交量推送
     * */
    public static final int KLINE_BOTTON_DEAL = 1005;
    /**
     * K线推送
     * */
    public static final int KLINE = 1006;
    /**
     * 交易分时图推送
     * */
    public static final int TRADE_MINUTE = 1007;
    /**
     * 发布/修改广告成功
     */
    public static final int RELEASE_ADVERT = 1008;
    /**
     * 解除申请成功
     */
    public static final int UN_APPLY = 1009;
    /**
     * 上架下架成功刷新下广告列表   ---删除广告也要删除列表 2019年6月12日14:59:49
     */
    public static final int ADVERT_LIST_REFRESH = 1010;
    /**
     * 添加支付方式成功
     */
    public static final int ADD_PAYMENT_METHOD_SUCCESS = 1011;
    /**
     * 支付方式
     */
    public static final int CASH_ID = 1012;

    /**聊天推送*/
    public static final int CHAT_PUSH = 1013;

    /**会员升级成功*/
    public static final int VIP_UPDATE =1014;

    /**修改商户昵称*/
    public static final int UPDATE_BUSINESS =1015;

   /**点击交易按钮  跳转到交易Fragment*/
    public static final int GO_TRADING = 1016;

    /**判断是否是商家接口   主要用于*/
    public static final int iS_BUSINESS = 1017;

    /**设置安全验证成功（图形验证）**/
    public static final int SET_SAFE_VERIFICATION =1018;

    /** 下载新版本APK成功 */
    public static final int DOWN_LOAD_APK_SUCCESS = 1019;

    /** 下载新版本APK失败 */
    public static final int DOWN_LOAD_APK_DEFAULT = 1020;

    /** 获取激活的支付方式ID */
    public static final int ENABLE_CASH_ID = 1021;

    /** 异地登录 被挤下线 */
    public static final int OTHER_LOGIN = 1022;

    /** 绑定谷歌验证码成功 */
    public static final int BIND_GOOGLE_CODE = 1023;

    /** 绑定邮箱验证码成功 */
    public static final int BIND_EMAIL_CODE = 1024;

    /** 划转成功 */
    public static final int TRANSFER_COIN_SUCCESS_CODE = 1025;

    /**点击交易按钮  跳转到交易Fragment*/
    public static final int GO_FABI = 1026;

    /** 绑定手机验证码成功 */
    public static final int BIND_PHONE_CODE = 1029;

    /** 还币成功 */
    public static final int REPAY_COIN_SUCCESS = 1050;

    /**点击交易按钮  跳转到全仓杠杆交易Fragment*/
    public static final int GO_LEVER = 1051;

    /**点击交易按钮  跳转到逐仓杠杆交易Fragment*/
    public static final int GO_WARE_LEVER = 1052;

    /** 推送全仓交易总资产/总负债/风险率 */
    public static final int LEVER_QCGG_RISK_PUSH = 1053;

    /** 推送逐仓交易总资产/总负债/风险率 */
    public static final int LEVER_ZCGG_RISK_PUSH = 1054;

    /** K线页面买入 */
    public static final int KLINE_BUY = 1055;
    /** K线页面卖出 */
    public static final int KLINE_SELL= 1056;
    /** 版本更新界面进度更新通知 */
    public static final int PROGRESS_NOTIFY= 1057;
}
