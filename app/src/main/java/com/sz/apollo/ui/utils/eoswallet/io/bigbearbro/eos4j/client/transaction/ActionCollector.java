package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.transaction;

import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.eosio.chain.action.Action;

import java.util.List;


public interface ActionCollector {
	public List<Action> collectActions();
}
