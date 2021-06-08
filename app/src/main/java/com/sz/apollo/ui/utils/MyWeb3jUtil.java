package com.sz.apollo.ui.utils;


import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.ui.models.UserWalletBean;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ChainId;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * Date:2018/8/21
 * auther:wangtianyun
 * describe:
 */
public class MyWeb3jUtil {
    public static Web3j getWeb3jInstance() {
        Web3j web3j = Web3j.build(new HttpService(RequestHost.ethNetUrl));
        return web3j;
    }


    /**
     * ETH 转账离线签名
     * <p>
     * RawTransaction.createEtherTransaction（）//这个方法是ETH转账的方法
     *
     * @param to       转入的钱包地址
     * @param nonce    以太坊nonce
     * @param gasPrice gasPrice
     * @param gasLimit gasLimit
     * @param amount   转账的eth数量
     * @return 签名data
     */
    public static String signedEthTransactionData(
            UserWalletBean userWalletBean,
            String to,
            BigInteger nonce,
            BigInteger gasPrice,
            BigInteger gasLimit,
            BigDecimal amount) {
        // 把十进制的转换成ETH的Wei, 1ETH = 10^18 Wei
        BigDecimal amountInWei = Convert.toWei(amount.toString(), Convert.Unit.ETHER);
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, amountInWei.toBigInteger());
        ECKeyPair ecKeyPair = EthWalletUtil.getKeyPair(userWalletBean.getPk());
        Credentials credentials = Credentials.create(ecKeyPair);
        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String signedTransactionData = Numeric.toHexString(signMessage);
        return signedTransactionData;
    }

    private static String signData(RawTransaction rawTransaction, UserWalletBean userWalletBean) throws Exception {
        String lastString = null;
        ECKeyPair ecKeyPair = null;
        new Thread() {
            @Override
            public void run() {
                super.run();
                ECKeyPair ecKeyPair = EthWalletUtil.getKeyPair(userWalletBean.getPk());
                if (null != ecKeyPair) {
                    userWalletBean.setPk(ecKeyPair.getPrivateKey().toString(16));
                    userWalletBean.setPublicKey(ecKeyPair.getPublicKey().toString(16));
                    Credentials credentials = Credentials.create(ecKeyPair);
                    byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, ChainId.MAINNET, credentials);
                }
            }
        }.start();

//
        //生成凭证
        Credentials credentials = Credentials.create(ecKeyPair);
        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, ChainId.MAINNET, credentials);
        return Numeric.toHexString(signMessage);
    }

    public static String signContractTransaction(UserWalletBean userWalletBean, String contractAddress, String to, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, Double amount) throws Exception {
        BigDecimal realValue = BigDecimal.valueOf(amount * Math.pow(10.0, 18));
        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(to),
                        new Uint256(realValue.toBigInteger())), Collections.emptyList());


        String data = FunctionEncoder.encode(function);
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                contractAddress,
                data);
        return signData(rawTransaction, userWalletBean);
    }


    /**
     * ERC20 token转账
     *
     * @param userWalletBean
     * @param to
     * @param value
     * @param nonce
     * @param contractAddress
     * @param gasPrice
     * @param gasLimit
     * @return
     */
    public static String transferERC20Token(UserWalletBean userWalletBean,
                                            String to,
                                            String value,
                                            BigInteger nonce,
                                            String contractAddress,
                                            BigInteger gasPrice,
                                            BigInteger gasLimit) {
        //加载转账所需的凭证，用私钥
        Credentials credentials = Credentials.create(userWalletBean.getPk());
        BigDecimal amountInMWEI = Convert.toWei(value, Convert.Unit.MWEI);

        //获取nonce，交易笔数
        //创建RawTransaction交易对象
        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(to), new Uint256(amountInMWEI.toBigInteger())),
                Collections.singletonList(new TypeReference<Type>() {
                }));

        String encodedFunction = FunctionEncoder.encode(function);

        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce,
                gasPrice,
                gasLimit,
                contractAddress,
                encodedFunction);

        //签名Transaction，这里要对交易做签名
        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signMessage);
        return hexValue;
    }
}
