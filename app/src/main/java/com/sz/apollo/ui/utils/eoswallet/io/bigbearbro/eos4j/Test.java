/**
 * Copyright (c) 2020-2030 The linn developers
 */
package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j;

import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.ui.models.JsonRpcBean;
import com.sz.apollo.ui.utils.MyWeb3jUtil;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Test {

    public static void main(String[] args) {
//        test();
        test2();
    }


    public static void test2() {
        JsonRpcBean jsonRpcBean = new JsonRpcBean();
        jsonRpcBean.setId(1);
        jsonRpcBean.setMethod("eth_getLogs");
        jsonRpcBean.setJsonrpc("2.0");
        List<JsonRpcBean.ParamsBean> listP = new ArrayList<>();
        jsonRpcBean.setParams(listP);
        JsonRpcBean.ParamsBean data = new JsonRpcBean.ParamsBean();
        listP.add(data);
        List<String> listTopic = new ArrayList<>();
        data.setTopics(listTopic);
        data.setAddress(RequestHost.usdtContract);
        data.setFromBlock("0x1");
        listTopic.add("0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef");
        listTopic.add("0xa3c62F8bbaa7434c45a1dd598f3bbC2d09419cF3");//from过滤
        listTopic.add(null);//to  过滤
//        data.setAddress(RequestHost.eceToken);
        String json = JSON.toJSONString(jsonRpcBean);
        Log.e("web3", "json++\n" + json);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(RequestHost.url);
                params.setAsJsonContent(true);
                params.setBodyContent(json);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("web3", "result" + result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("web3", ex.toString());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                    }

                    @Override
                    public void onFinished() {
                        Log.e("web3", "onFinished");
                    }
                });

            }
        }
        ) {
        }.start();
    }

}
