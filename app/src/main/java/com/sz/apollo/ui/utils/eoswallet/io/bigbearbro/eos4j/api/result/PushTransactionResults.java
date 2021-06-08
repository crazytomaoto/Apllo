package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.eosio.chain.trace.TransactionTrace;


/**
 * @author wangyan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushTransactionResults {
    @JsonProperty("code")
    private int code;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("processed")
    private TransactionTrace processed;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionTrace getProcessed() {
        return processed;
    }

    public void setProcessed(TransactionTrace processed) {
        this.processed = processed;
    }

}
