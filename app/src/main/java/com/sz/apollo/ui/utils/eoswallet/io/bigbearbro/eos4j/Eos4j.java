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
     * ?????????????????????
     *
     * @return
     */
    public TransactionBuilder txBuilder() {
        return txBuilder;
    }

    /**
     * ???????????????????????????id
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
     * ????????????
     *
     * @param pk         ??????
     * @param creator    ?????????
     * @param newAccount ?????????
     * @param owner      ??????
     * @param active     ??????
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
     * ????????????
     *
     * @param pk               ??????
     * @param creator          ?????????
     * @param newAccount       ?????????
     * @param owner            ??????
     * @param active           ??????
     * @param buyRam           ??????????????????
     * @param stakeNetQuantity ????????????
     * @param stakeCpuQuantity cpu??????
     * @param transfer         ????????????????????????????????????0???????????????1????????????
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
     * ??????????????????
     *
     * @param accountName ????????????
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
     * ??????????????????
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
     * ??????????????????
     *
     * @param blockNumOrId ??????ID????????????
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
     * ???????????????
     *
     * @return
     * @throws IOException
     * @throws ApiException
     */
    public GetInfoResults getChainInfo() throws ApiException, IOException {
        return Generator.executeSync(rpcService.getChainInfo());
    }

    /**
     * ??????????????????
     *
     * @param account ????????????
     * @param code    ????????????
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
     * ??????????????????account???????????????code??????symbol????????????
     *
     * @param account ??????
     * @param code    ????????????
     * @param symbol  ??????
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
     * ??????????????????
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
     * ??????????????????
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
     * ??????????????????
     *
     * @param compression     ??????
     * @param pushTransaction ??????
     * @param signatures      ??????
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
     * ??????
     *
     * @param pk              ??????
     * @param contractAccount ????????????
     * @param from            ???
     * @param to              ???
     * @param quantity        ????????????
     * @param memo            ??????
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
     * ??????
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
     * ??????????????????eos??????
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
