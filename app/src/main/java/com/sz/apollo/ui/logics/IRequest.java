package com.sz.apollo.ui.logics;

import com.hysd.android.platform_ub.base.logic.ILogic;

/**
 * Created by Administrator on 2020/5/02.
 */

public interface IRequest extends ILogic {
    /**
     * eth余额
     *
     * @param address
     * @param type
     */
    void queryETHBalance(String address, String type);

    /**
     * USDT余额
     *
     * @param address
     * @param type
     */
    void getTokenOfUsdt(String address, String type);

    /**
     * AOT/AOC余额
     *
     * @param address
     */
    void getTokenOfApollo(String address, boolean isHome);

    /**
     * 创建阿波罗账户
     *
     * @param publicKey    阿波罗公钥
     * @param privateKey   阿波罗账户
     * @param deviceNumber 识别码
     */
    void createApolloAccount(String publicKey, String privateKey, String deviceNumber);

    /**
     * 按账号获取交易记录列表
     *
     * @param account 账号
     * @param symbol  币种
     * @param current 当前页 默认0开始
     * @param limit   每页几条 默认5
     */
    void getTransactionsByAccount(String account, String symbol, int current, int limit);

    /**
     * 按公钥获取账号
     *
     * @param publicKey
     */
    void getAccounts(String publicKey);

    /**
     * @param privateKey
     * @param fromAddress
     * @param deviceNumber
     * @param toAddress
     * @param amount
     * @param symbol
     * @param memo
     */
    void tranApollo(String privateKey, String deviceNumber, String fromAddress, String toAddress, double amount, String symbol, String memo);

    /**
     * 用户挖矿奖励记录
     *
     * @param address
     * @param pageNumber
     * @param pageSize
     * @param state      状态（0：未提现 1：提现中 2：已提现）
     */
    void getRewardList(String address, int pageNumber, int pageSize, int state);

    /**
     * 体系信息
     *
     * @param address
     */
    void getShareList(String address);

    /**
     * 获取币种价格
     *
     * @param symbols
     */
    void getPrice(String symbols);

    /**
     * 第二页数据
     *
     * @param address
     */
    void queryPower(String address);

    /**
     * 获取公告信息
     *
     * @param pageNumber
     * @param pageSize
     * @param keyword
     * @param noticeType 公告类型（1：AOC矿场 2：NBO矿场,3:DDAO矿场，4：LON矿场）
     */
    void queryNotice(int pageNumber, int pageSize, String keyword, int noticeType);


    /**
     * 查询手机账户状态
     *
     * @param
     */
    void accountState(String deviceNumber);

    /**
     * 获取闪兑汇率
     *
     * @param symbol
     */
    void queryFlashRate(String symbol, String toSymbol);

    /**
     * 获取闪兑记录
     *
     * @param address
     * @param pageNumber
     * @param pageSize
     */
    void flashExchangeList(String address, int pageNumber, int pageSize);

    /**
     * 获取基础算力变动记录
     *
     * @param pageNumber
     * @param pageSize
     * @param address
     */
    void queryBasePowerList(int pageNumber, int pageSize, String address);

    /**
     * 查询地址工时算力详情
     *
     * @param address
     */
    void queryTimePowerList(String address);

    /**
     * 查询地址分享算力详情
     *
     * @param address
     */
    void queryShare(String address);

    /**
     * 以太坊交易记录
     *
     * @param pageNumber
     * @param pageSize
     * @param address
     */
    void getEthTransactionsByAddress2(int pageNumber, int pageSize, String address);

    /**
     * 区块浏览器 按账号获取交易记录列表
     *
     * @param acount
     * @param current
     * @param limit
     */
    void getTransactionsByAccount2(String acount, int current, int limit);

    /**
     * 共振接口
     *
     * @param address
     */
    void reqBodyAotModelList(String address);

    /**
     * 区块浏览器-按hash获取交易记录
     *
     * @param hash
     */
    void getTransactionByHash(String hash);

    /**
     * 闪兑
     *
     * @param type        闪兑类型（1：USDT 2：ETH 3：BTC）
     * @param fromAddress
     * @param toAddress
     * @param amount
     * @param symbol
     * @param txId
     */
    void flashExchange(int type, String fromAddress, String toAddress, double amount, String symbol, String txId, String deviceNumber);

    /**
     * 导入账户
     *
     * @param publicKey
     * @param privateKey
     * @param deviceNumber
     * @param address
     */
    void importAccount(String publicKey, String privateKey, String deviceNumber, String address);

    /**
     * 获取转账记录
     *
     * @param address
     * @param symbol
     * @param pageNumber
     * @param pageSize
     */
    void transList(String address, String symbol, int pageNumber, int pageSize);

    /**
     * 获取转账交易详情
     *
     * @param txId
     */
    void transDetail(String txId);

    /**
     * 本地址矿池的激活状态
     *
     * @param address
     */
    void addressState(String address);

    /**
     * 获取最新版本
     *
     * @param type android 1 io2
     */
    void latestVersion();

    /**
     * 区块浏览器 查询基本信息
     */
    void getWebInfo();


    /**
     * 区块浏览器 按高度获取交易记录列表
     */
    void getBlock(String blockNumber);

    /**
     * @param publicKey
     * @param account
     */
    void checkPublicKeyIsExist(String publicKey, String account);

    /**
     * 挖矿奖励提现
     *
     * @param userRewardId
     */
    void withdraw(int userRewardId);

    /**
     * NBO余额
     *
     * @param address
     */
    void getBalanceOfNbo(String address, boolean isHome);

    /**
     * NBO矿池
     * type----NBO,DDAO,LON
     *
     * @param address
     */
    void orePool(String type, String address);

    /**
     * NBO矿池算力
     *
     * @param address
     */
    void computationalPower(String type, String address, int page, int size);

    /**
     * NBO收益详情
     *
     * @param address
     */
    void orePoolDetails(String type, String address, int page, int size);

    /**
     * 获取矿机的激活状态
     *
     * @param address
     * @param type
     */
    void getPollAddressState(String type, String address);

    /**
     * 激活矿机NBO
     *
     * @param privateKey
     * @param deviceNumber
     * @param address
     */
    void enableNboMine(String type, String privateKey, String deviceNumber, String address);

    /**
     * 检测NBO闪兑
     *
     * @param deviceNumber
     */
    void checkFlashNbo(String deviceNumber);

    /**
     * NBO矿池算力
     *
     * @param address
     */
    void powerInfo(String type, String address);

    /**
     * NBO提现收益
     */
    void withdrawProfit(int profitId, String symbols);

    /**
     * 获取账户币种余额
     *
     * @param address
     * @param symbols
     */
    void getBalance(String address, String symbols);

    /**
     * 新增ETH账户
     *
     * @param privateKey
     * @param deviceNumber
     * @param address
     */
    void addAccountEth(String privateKey, String deviceNumber, String address);

}

