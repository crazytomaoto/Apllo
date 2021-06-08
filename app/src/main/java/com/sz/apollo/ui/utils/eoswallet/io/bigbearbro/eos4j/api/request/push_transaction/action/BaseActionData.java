package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.request.push_transaction.action;


import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.pack.PackUtils;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.utils.ByteBuffer;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.utils.Hex;

public class BaseActionData {

	public String toString() {
		ByteBuffer bb = new ByteBuffer();
		PackUtils.packObj(this, bb);
		return Hex.bytesToHexString(bb.getBuffer());
	}
}
