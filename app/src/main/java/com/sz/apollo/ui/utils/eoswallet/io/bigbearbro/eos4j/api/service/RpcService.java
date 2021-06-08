package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.service;

import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.AbiJsonToBinRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetAccountRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetAccountsRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetBlockRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetCurrencyBalanceRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.GetTransactionRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.PushTransactionRequest;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.AbiJsonToBinResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetAccountResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetAccountsResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetBlockResult;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetInfoResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetTransactionResult;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.PushTransactionResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 
 * @author wangyan
 *
 */
public interface RpcService {

	@GET("/v1/chain/get_info")
	Call<GetInfoResults> getChainInfo();

	@POST("/v1/chain/get_block")
	Call<GetBlockResult> getBlock(@Body GetBlockRequest requestFields);

	@POST("/v1/chain/get_account")
	Call<GetAccountResults> getAccount(@Body GetAccountRequest requestFields);

	@POST("/v1/chain/push_transaction")
	Call<PushTransactionResults> pushTransaction(@Body PushTransactionRequest request);

	@POST("/v1/chain/abi_json_to_bin")
    Call<AbiJsonToBinResults> abiJsonToBin(@Body AbiJsonToBinRequest abiJsonToBinRequest);

	@POST("/v1/chain/get_currency_balance")
	Call<List<String>> getCurrencyBalance(@Body GetCurrencyBalanceRequest request);

	@POST("/v1/history/get_transaction")
	Call<GetTransactionResult> getTransaction(@Body GetTransactionRequest request);

	@POST("/v1/history/get_key_accounts")
	Call<GetAccountsResults> getAccounts(@Body GetAccountsRequest request);

}
