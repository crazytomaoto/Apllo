package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.transaction;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.eosio.chain.transaction.Transaction;

/**
 * 原始交易
 *
 * @author wangyan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignedTransactionToPush {
//    private int code;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }

    /**
     * 交易ID
     */
    private String txId;
    /**
     * 压缩
     */
    private String compression;
    /**
     * 交易体
     */
    private Transaction transaction;
    /**
     * transaction的签名
     */
    private String[] signatures;

    public SignedTransactionToPush() {

    }

    public SignedTransactionToPush(String txId, String compression, Transaction transaction, String[] signatures) {
        this.txId = txId;
        this.compression = compression;
        this.transaction = transaction;
        this.signatures = signatures;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String[] getSignatures() {
        return signatures;
    }

    public void setSignatures(String[] signatures) {
        this.signatures = signatures;
    }

}
