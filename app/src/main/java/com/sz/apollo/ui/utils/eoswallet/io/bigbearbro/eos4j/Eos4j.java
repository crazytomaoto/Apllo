package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetAccountRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetAccountsRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetBlockRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetCurrencyBalanceRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetTransactionRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.PushTransactionRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetAccountResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetBlockResult;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetInfoResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetTransactionResult;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.PushTransactionResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.service.RpcService;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.exception.ApiException;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.exception.InvalidParameterException;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.http.Generator;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.pack.PackUtils;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.transaction.SignedTransactionToPush;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.transaction.TransactionBuilder;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.crypto.util.Sha;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.eosio.chain.transaction.Transaction;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.utils.ByteBuffer;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.utils.Hex;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;


/**
 * EOS client
 *
 * @author wuwei
 */
public class Eos4j {

    private RpcService rpcService;

    private TransactionBuilder txBuilder;

    public Eos4j(String baseUrl) {
        this(baseUrl, null);
    }

    public Eos4j(String baseUrl, String host) {
        rpcService = Generator.createService(RpcService.class, baseUrl, host);
        txBuilder = TransactionBuilder.newInstance(this);
    }

    /**
     * 获取交易构造器
     *
     * @return
     */
    public TransactionBuilder txBuilder() {
        return txBuilder;
    }

    /**
     * 根据交易体计算交易id
     *
     * @param transaction
     * @return
     */
    public String calcTransactionId(Transaction transaction) {
        ByteBuffer bf = new ByteBuffer();
        PackUtils.packObj(transaction, bf);
        return Hex.bytesToHexString(Sha.SHA256(bf.getBuffer()));
    }

    /**
     * 创建账户
     *
     * @param pk         私钥
     * @param creator    创建者
     * @param newAccount 新账户
     * @param owner      公钥
     * @param active     公钥
     * @param buyRam     ram
     * @return
     * @throws IOException
     * @throws ApiException
     * @throws Exception
     */
    public PushTransactionResults createAccount(String pk, String creator, String newAccount, String owner,
                                                String active, Long buyRam) throws ApiException, IOException {
        return createAccount(pk, creator, newAccount, owner, active, buyRam, null, null, null);
    }

    /**
     * 创建账户
     *
     * @param pk               私钥
     * @param creator          创建者
     * @param newAccount       新账户
     * @param owner            公钥
     * @param active           公钥
     * @param buyRam           购买空间数量
     * @param stakeNetQuantity 网络抵押
     * @param stakeCpuQuantity cpu抵押
     * @param transfer         抵押资产是否转送给对方，0自己所有，1对方所有
     * @return
     * @throws IOException
     * @throws ApiException
     * @throws Exception
     */
    public PushTransactionResults createAccount(String pk, String creator, String newAccount, String owner,
                                                String active, Long buyRam, String stakeNetQuantity, String stakeCpuQuantity, Long transfer)
            throws ApiException, IOException {
        return pushTransaction(txBuilder.buildNewAccountRawTx(pk, creator, newAccount, owner, active, buyRam,
                stakeNetQuantity, stakeCpuQuantity, transfer));
    }

    /**
     * 获得账户信息
     *
     * @param accountName 账户名称
     * @return
     * @throws IOException
     */
    public GetAccountResults getAccount(String accountName) throws IOException {
        GetAccountRequest req = new GetAccountRequest();
        req.setAccountName(accountName);
        try {
            return Generator.executeSync(rpcService.getAccount(req));
        } catch (ApiException e) {
            return null;
        }
    }

    /**
     * 获取账户信息
     *
     * @param publicKey
     * @return
     * @throws ApiException
     * @throws IOException
     */
    public List<String> getAccounts(String publicKey) throws ApiException, IOException {
        GetAccountsRequest request = new GetAccountsRequest();
        request.setPublicKey(publicKey);
        return Generator.executeSync(rpcService.getAccounts(request)).getAccountNames();
    }

    /**
     * 获得区块信息
     *
     * @param blockNumOrId 区块ID或者高度
     * @return
     * @throws IOException
     */
    public GetBlockResult getBlock(String blockNumOrId) throws IOException {
        GetBlockRequest req = new GetBlockRequest();
        req.setBlockNumOrId(blockNumOrId);
        try {
            return Generator.executeSync(rpcService.getBlock(req));
        } catch (ApiException e) {
            return null;
        }
    }

    /**
     * 获得链信息
     *
     * @return
     * @throws IOException
     * @throws ApiException
     */
    public GetInfoResults getChainInfo() throws ApiException, IOException {
        return Generator.executeSync(rpcService.getChainInfo());
    }

    /**
     * 获取账户余额
     *
     * @param account 指定账户
     * @param code    合约地址
     * @return
     * @throws IOException
     * @throws ApiException
     */
    public Map<String, BigDecimal> getCurrencyBalance(String account, String code) throws ApiException, IOException {
        if (account == null || code == null) {
            throw new InvalidParameterException("account or code cannot be null");
        }
        GetCurrencyBalanceRequest req = new GetCurrencyBalanceRequest();
        req.setAccount(account);
        req.setCode(code);
        Call<List<String>> call = rpcService.getCurrencyBalance(req);
        List<String> list = Generator.executeSync(call);
        Map<String, BigDecimal> ret = new HashMap<String, BigDecimal>();
        if (list != null) {
            list.forEach(it -> {
                String[] s = it.split(" ");
                String coinTypeCode = s[1];
                BigDecimal value = new BigDecimal(s[0]);
                ret.put(coinTypeCode, value);
            });
        }
        return ret;
    }

    /**
     * 获取指定账户account在合约账户code下的symbol代币余额
     *
     * @param account 账户
     * @param code    合约地址
     * @param symbol  代币
     * @return
     * @throws ApiException
     * @throws IOException
     */
    public BigDecimal getCurrencyBalance(String account, String code, String symbol) throws ApiException, IOException {
        if (account == null || code == null) {
            throw new InvalidParameterException("account or code cannot be null");
        }
        if (symbol == null) {
            throw new InvalidParameterException("symbol cannot be null");
        }
        GetCurrencyBalanceRequest req = new GetCurrencyBalanceRequest();
        req.setAccount(account);
        req.setCode(code);
        req.setSymbol(symbol);
        List<String> list = Generator.executeSync(rpcService.getCurrencyBalance(req));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return new BigDecimal(list.get(0).split(" ")[0]);
    }

    /**
     * 获取交易信息
     *
     * @param transactionId
     * @return
     * @throws IOException
     */
    public GetTransactionResult getTransaction(String transactionId) throws IOException {
        if (transactionId == null) {
            throw new InvalidParameterException("transactionId cannot be null");
        }
        GetTransactionRequest req = new GetTransactionRequest();
        req.setId(transactionId);
        try {
            GetTransactionResult t = Generator.executeSync(rpcService.getTransaction(req));
            if (t == null || t.getTrx() == null || !transactionId.equals(t.getId())) {
                return null;
            }
            return t;
        } catch (ApiException e) {
            return null;
        }
    }

    /**
     * 发送交易请求
     *
     * @param pushTransaction
     * @return
     * @throws ApiException
     * @throws IOException
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public PushTransactionResults pushTransaction(SignedTransactionToPush pushTransaction)
            throws ApiException, IOException {
        return pushTransaction(pushTransaction.getCompression(), pushTransaction.getTransaction(),
                pushTransaction.getSignatures());
    }

    /**
     * 发送交易请求
     *
     * @param compression     压缩
     * @param pushTransaction 交易
     * @param signatures      签名
     * @return
     * @throws IOException
     * @throws ApiException
     * @throws Exception
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public PushTransactionResults pushTransaction(String compression, Transaction pushTransaction, String[] signatures)
            throws ApiException, IOException {
        return Generator.executeSync(
                rpcService.pushTransaction(new PushTransactionRequest(compression, pushTransaction, signatures)));
    }

    /**
     * 转账
     *
     * @param pk              私钥
     * @param contractAccount 合约账户
     * @param from            从
     * @param to              到
     * @param quantity        币种金额
     * @param memo            留言
     * @return
     * @throws IOException
     * @throws ApiException
     * @throws Exception
     */
    public PushTransactionResults transfer(String pk, String contractAccount, String from, String to, String quantity,
                                           String memo) throws ApiException, IOException {
        return pushTransaction(txBuilder.buildTransferRawTx(pk, contractAccount, from, to, quantity, memo));
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public PushTransactionResults transfergas(String pk, String contractAccount, String from, String to, String quantity,
                                              String gas, String memo) throws ApiException, IOException {
        return pushTransaction(txBuilder.buildTransfergasRawTx(pk, contractAccount, from, to, quantity, gas, memo));
    }

    public SignedTransactionToPush getSignedTransactionToPush(String pk, String contractAccount, String from, String to, String quantity, String gas,
                                                              String memo) throws ApiException, IOException {
        return txBuilder.buildTransfergasRawTx(pk, contractAccount, from, to, quantity, gas, memo);
    }

    /**
     * 投票
     *
     * @param pk
     * @param voter
     * @param proxy
     * @param producers
     * @return
     * @throws ApiException
     * @throws IOException
     */
    public PushTransactionResults voteProducer(String pk, String voter, String proxy, List<String> producers)
            throws ApiException, IOException {
        return pushTransaction(txBuilder.buildVoteProducerRawTx(pk, voter, proxy, producers));
    }

    public static final String NUMBERS_AND_LETTERS = "12345abcdefghijklmnopqrstuvwxyz";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 随机生成一个eos账号
     *
     * @return
     */
    public static String generateAccount() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(getRandom(LOWER_CASE_LETTERS, 1));
        sBuilder.append(getRandom(NUMBERS_AND_LETTERS, 11));
        return sBuilder.toString();
    }

    public static String getRandom(String source, int length) {
        return (source == null || source.equals("")) ? null : getRandom(source.toCharArray(), length);
    }

    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }

        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

}
