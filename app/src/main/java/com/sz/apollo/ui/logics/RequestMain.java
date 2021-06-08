package com.sz.apollo.ui.logics;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hysd.android.platform_ub.base.logic.BaseLogic;
import com.hysd.android.platform_ub.net.base.ProtocolType;
import com.hysd.android.platform_ub.net.http.ResponseDataType;
import com.sz.apollo.R;
import com.sz.apollo.api.request.DynaRequest;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.models.AddressSharePowerBean;
import com.sz.apollo.ui.models.AddressStateBean;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.BasePowerBean;
import com.sz.apollo.ui.models.CreateApolloBean;
import com.sz.apollo.ui.models.EthTransRecordBean;
import com.sz.apollo.ui.models.FlashRateBean;
import com.sz.apollo.ui.models.FlashRecordBean;
import com.sz.apollo.ui.models.FlashResultBean;
import com.sz.apollo.ui.models.ImportApolloBean;
import com.sz.apollo.ui.models.MineRewardBean;
import com.sz.apollo.ui.models.NboMiningPowerBean;
import com.sz.apollo.ui.models.NboPollBean;
import com.sz.apollo.ui.models.NboPowerInfoBean;
import com.sz.apollo.ui.models.NboProfitDetailBean;
import com.sz.apollo.ui.models.NboStateBean;
import com.sz.apollo.ui.models.NoticeBean;
import com.sz.apollo.ui.models.PriceBean;
import com.sz.apollo.ui.models.QueryPowerBean;
import com.sz.apollo.ui.models.RecordBlockHashBean;
import com.sz.apollo.ui.models.ResonanceBean;
import com.sz.apollo.ui.models.ShareSystemBean;
import com.sz.apollo.ui.models.TimePowerBean;
import com.sz.apollo.ui.models.TransApolloBean;
import com.sz.apollo.ui.models.TransDetailByTxIdBean;
import com.sz.apollo.ui.models.TransRecordBean;
import com.sz.apollo.ui.models.VersionBean;
import com.sz.apollo.ui.models.WebInfoBean;
import com.sz.apollo.ui.utils.AndroidRSAUtils;
import com.sz.apollo.ui.utils.MyWeb3jUtil;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.GetAccountResults;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.eosio.chain.authority.KeyWeight;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.eosio.chain.authority.Permission;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sz.apollo.application.ApolloApplication.eos4j;
import static com.sz.apollo.commons.constants.Constant.PUBLICKEY;

/**
 * Created by andmin on 2020/05/03.
 * 请求接口的主要实现类
 */

public class RequestMain extends BaseLogic implements IRequest {

    public RequestMain(Context context) {
        super(context);
    }

    @Override
    public void queryETHBalance(String address, String type) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                EthGetBalance ethGetBalance = null;
                try {
                    ethGetBalance = MyWeb3jUtil.getWeb3jInstance().ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
                    if (ethGetBalance != null) {
                        BigDecimal nig = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
                        String resultBalance = nig.stripTrailingZeros().toPlainString();
                        BalanceBean balanceBean = new BalanceBean();
                        balanceBean.setBalance(resultBalance);
                        balanceBean.setType(Constant.TYPE_ETH);
                        sendMessage(GlobalMessageType.RequestCode.GETETHBALANCE, balanceBean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.GETETHBALANCE_ERROR, ethGetBalance.getError());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void getTokenOfUsdt(String address, String type) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    EthCall ethCall = MyWeb3jUtil.getWeb3jInstance().ethCall(Transaction.createEthCallTransaction(address, RequestHost.usdtContract, "0x70a08231000000000000000000000000" + address.substring(2)), DefaultBlockParameterName.PENDING).send();
                    if (ethCall != null) {
                        String result = ethCall.getValue().substring(2);
                        //得到16进制去除0x0000……的16进制字符串
                        int index = Util.getIndexNoneZore(result);
                        BalanceBean balanceBean = new BalanceBean();
                        if (index > 0) {
                            String noZeroResult = result.substring(index);
                            String str = new BigInteger(noZeroResult, 16).toString(10);
                            String money = (Double.parseDouble(str) / (Math.pow(10, 6))) + "";
                            balanceBean.setBalance(money);
                            balanceBean.setType(Constant.TYPE_U);
                        } else {
                            balanceBean.setBalance("0");
                            balanceBean.setType(Constant.TYPE_U);
                        }
                        balanceBean.setAddress(address);
                        sendMessage(GlobalMessageType.RequestCode.GETUSDTBALANCE, balanceBean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.GETUSDTALANCE_ERROR, ethCall.getError());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void getTokenOfApollo(String address, boolean iHome) {
        final Map<String, BigDecimal>[] balanceMap = new Map[]{null};
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    balanceMap[0] = eos4j.getCurrencyBalance(address, Constant.APOLLO_CONTRACT_ADDRESS);
                    List<BalanceBean> list = new ArrayList<>();
                    if (null != balanceMap[0] && balanceMap[0].size() > 0) {
                        BalanceBean balanceAotBean = new BalanceBean();
                        balanceAotBean.setType(Constant.TYPE_AOT);
                        if (balanceMap[0].containsKey(Constant.TYPE_AOT)) {
                            balanceAotBean.setBalance(balanceMap[0].get(Constant.TYPE_AOT).stripTrailingZeros().toPlainString());
                        } else {
                            balanceAotBean.setBalance("0");
                        }
                        list.add(balanceAotBean);

                        BalanceBean balanceAocBean = new BalanceBean();
                        balanceAocBean.setType(Constant.TYPE_AOC);
                        if (balanceMap[0].containsKey(Constant.TYPE_AOC)) {
                            balanceAocBean.setBalance(balanceMap[0].get(Constant.TYPE_AOC).stripTrailingZeros().toPlainString());
                        } else {
                            balanceAocBean.setBalance("0");
                        }
                        list.add(balanceAocBean);
                    } else {
                        BalanceBean balanceAot = new BalanceBean();
                        balanceAot.setType(Constant.TYPE_AOT);
                        balanceAot.setBalance("0");
                        BalanceBean balanceAoc = new BalanceBean();
                        balanceAoc.setType(Constant.TYPE_AOC);
                        balanceAoc.setBalance("0");
                        list.add(balanceAot);
                        list.add(balanceAoc);
                    }
                    if (iHome) {
                        sendMessage(GlobalMessageType.RequestCode.GETAPOLLOBALANCE, list);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.GETAPOLLOBALANCE_NOHOME, list);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void createApolloAccount(String publicKey, String privateKey, String deviceNumber) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {

                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    CreateApolloBean transRecordBean = JSON.parseObject(dataString, CreateApolloBean.class);
                    if (transRecordBean != null) {
                        sendMessage(GlobalMessageType.RequestCode.CREATEAPOLLOACCOUNT, transRecordBean);
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("publicKey", publicKey);
        jsonData.put("privateKey", privateKey);
        jsonData.put("deviceNumber", deviceNumber);
        setParams(request, "createAccount", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getTransactionsByAccount(String account, String symbol, int pageNumber, int pageSize) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {

                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    TransRecordBean transRecordBean = JSON.parseObject(dataString, TransRecordBean.class);

                    if (transRecordBean != null && transRecordBean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.GETTRANSRECORD, transRecordBean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, transRecordBean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("account", account);
        jsonData.put("symbol", symbol);
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        setParams(request, "getTransactionsByAccount", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getAccounts(String publicKey) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {

                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    ImportApolloBean bean = JSON.parseObject(dataString, ImportApolloBean.class);

                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.GETACCOUNTS, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });

        JSONObject jsonData = new JSONObject();
        jsonData.put("publicKey", publicKey);
        setParams(request, "getAccounts", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void tranApollo(String privateKey, String deviceNumber, String fromAddress, String toAddress, double amount, String symbol, String memo) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {

                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    TransApolloBean bean = JSON.parseObject(dataString, TransApolloBean.class);

                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.SENDEOS, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });

        JSONObject jsonData = new JSONObject();
        jsonData.put("privateKey", privateKey);
        jsonData.put("deviceNumber", deviceNumber);
        jsonData.put("fromAddress", fromAddress);
        jsonData.put("toAddress", toAddress);
        jsonData.put("amount", amount);
        jsonData.put("symbol", symbol);
        jsonData.put("memo", memo);
        setParams(request, "sendEos", jsonData);

        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);


    }


    @Override
    public void getRewardList(String address, int pageNumber, int pageSize, int state) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    MineRewardBean bean = JSON.parseObject(dataString, MineRewardBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.WARD_LIST, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });

        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        setParams(request, "rewardList", jsonData);

        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getShareList(String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    ShareSystemBean bean = JSON.parseObject(dataString, ShareSystemBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.QUERY_CHILD, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "queryChild", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getPrice(String symbols) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    PriceBean bean = JSON.parseObject(dataString, PriceBean.class);

                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.SYMBOL_PRICE, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });


        JSONObject jsonData = new JSONObject();
        jsonData.put("symbols", symbols);
        setParams(request, "symbolPrice", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void queryPower(String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    QueryPowerBean bean = JSON.parseObject(dataString, QueryPowerBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.QUERY_POWER, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "queryPower", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void queryNotice(int pageNumber, int pageSize, String keyword, int noticeType) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    NoticeBean bean = JSON.parseObject(dataString, NoticeBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.QUERY_NOTICE, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        jsonData.put("keyword", "");
        jsonData.put("noticeType", noticeType);
        setParams(request, "noticeList", jsonData);
        request.setModel(RequestHost.Common.PUBLICABC);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void accountState(String deviceNumber) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    JSONObject object = JSON.parseObject(dataString);
                    if (null != object) {
                        if (object.getInteger("code") == 200) {
                            sendMessage(GlobalMessageType.RequestCode.ACCOUNT_STATE, object);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, object.getString("message"));
                        }
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("deviceNumber", deviceNumber);
        setParams(request, "accountState", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void queryFlashRate(String symbol, String toSymbol) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        FlashRateBean bean = JSON.parseObject(dataString, FlashRateBean.class);
                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.FLASH_RATE, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });

        JSONObject jsonData = new JSONObject();
        jsonData.put("symbol", symbol);
        jsonData.put("toSymbol", toSymbol);
        setParams(request, "flashExchangeRate", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void flashExchangeList(String address, int pageNumber, int pageSize) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    FlashRecordBean bean = JSON.parseObject(dataString, FlashRecordBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.FLASH_LIST, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        setParams(request, "flashExchangeList", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void queryBasePowerList(int pageNumber, int pageSize, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    BasePowerBean bean = JSON.parseObject(dataString, BasePowerBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.BASE_POWER_LIST, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });

        JSONObject jsonData = new JSONObject();
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        jsonData.put("address", address);
        setParams(request, "commonPowerChangeList", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);

    }

    @Override
    public void queryTimePowerList(String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    TimePowerBean bean = JSON.parseObject(dataString, TimePowerBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.TIME_POWER_LIST, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });

        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "queryTimeDetail", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void queryShare(String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    AddressSharePowerBean bean = JSON.parseObject(dataString, AddressSharePowerBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.QUERY_SHARE, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "queryShareDetail", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getEthTransactionsByAddress2(int pageNumber, int pageSize, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    EthTransRecordBean bean = JSON.parseObject(dataString, EthTransRecordBean.class);
                    if (bean != null && bean.getCode() == 200) {
                        sendMessage(GlobalMessageType.RequestCode.ETH_TRANS_RECORD, bean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        jsonData.put("address", address);
        setParams(request, "getEthTransactionsByAddress2", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getTransactionsByAccount2(String account, int pageNumber, int pageSize) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    sendMessage(GlobalMessageType.RequestCode.GET_RECORD_BY_ADDRESS, dataString);
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data_invalid));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("account", account);
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        setParams(request, "getTransactionsByAccount2", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void reqBodyAotModelList(String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        ResonanceBean bean = JSON.parseObject(dataString, ResonanceBean.class);
                        if (bean != null && bean.getCode() == 200) {
                            sendMessage(GlobalMessageType.RequestCode.GET_AOT_MODEL_LIST, bean);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "aotModelList", jsonData);
        request.setModel(RequestHost.Common.PUBLICABC);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getTransactionByHash(String hash) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        sendMessage(GlobalMessageType.RequestCode.GET_TRANS_BY_HAH, dataString);
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("hash", hash);
        setParams(request, "getTransactionByHash", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);

    }

    @Override
    public void flashExchange(int type, String fromAddress, String toAddress, double amount, String symbol, String txId, String deviceNumber) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        FlashResultBean bean = JSON.parseObject(dataString, FlashResultBean.class);

                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.FLASH_EXCHANGE, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("type", type);
        jsonData.put("fromAddress", fromAddress);
        jsonData.put("toAddress", toAddress);
        jsonData.put("amount", amount);
        jsonData.put("symbol", symbol);
        jsonData.put("txId", txId);
        jsonData.put("deviceNumber", deviceNumber);
        setParams(request, "flashExchange", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void importAccount(String publicKey, String privateKey, String deviceNumber, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        JSONObject jsonObject = JSON.parseObject(dataString);
                        sendMessage(GlobalMessageType.RequestCode.IMPORT_ACCOUNT, jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("publicKey", publicKey);
        jsonData.put("privateKey", privateKey);
        jsonData.put("deviceNumber", deviceNumber);
        jsonData.put("address", address);
        setParams(request, "importAccount", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void transList(String address, String symbol, int pageNumber, int pageSize) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    TransRecordBean bean = JSON.parseObject(dataString, TransRecordBean.class);
                    if (null != bean) {
                        if (bean.getCode() == 200) {
                            sendMessage(GlobalMessageType.RequestCode.TRANS_LIST_APOLLO, bean);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                        }
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        jsonData.put("symbol", symbol);
        jsonData.put("pageNumber", pageNumber);
        jsonData.put("pageSize", pageSize);
        setParams(request, "transList", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void transDetail(String txId) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    TransDetailByTxIdBean bean = JSON.parseObject(dataString, TransDetailByTxIdBean.class);

                    if (null != bean) {
                        if (bean.getCode() == 200) {
                            sendMessage(GlobalMessageType.RequestCode.TRANS_DETAIL, bean);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                        }
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("txId", txId);
        setParams(request, "transDetail", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void addressState(String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    AddressStateBean bean = JSON.parseObject(dataString, AddressStateBean.class);

                    if (null != bean) {
                        if (bean.getCode() == 200) {
                            sendMessage(GlobalMessageType.RequestCode.ADDRESS_STATE, bean);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                        }
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "addressState", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void latestVersion() {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    VersionBean bean = JSON.parseObject(dataString, VersionBean.class);
                    if (null != bean) {
                        if (bean.getCode() == 200) {
                            sendMessage(GlobalMessageType.RequestCode.LATEST_VERSION, bean);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                        }
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("type", 1);
        setParams(request, "latestVersion", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getWebInfo() {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                    WebInfoBean bean = JSON.parseObject(dataString, WebInfoBean.class);
                    if (null != bean) {
                        if (bean.getCode() == 200) {
                            sendMessage(GlobalMessageType.RequestCode.GET_WEB_INFO, bean);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                        }
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        setParams(request, "getWebInfo", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getBlock(String blockNumber) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        RecordBlockHashBean bean = JSON.parseObject(dataString, RecordBlockHashBean.class);
                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.GET_BLOCK, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("blockNumber", blockNumber);
        setParams(request, "getBlock", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void checkPublicKeyIsExist(String publicKey, String account) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    GetAccountResults accountResults = eos4j.getAccount(account);
                    String aaaa = JSONObject.toJSONString(accountResults);
                    List<Permission> permissionList = accountResults.getPermissions();
                    boolean isExist = false;
                    for (Permission p : permissionList) {
                        List<KeyWeight> listKey = p.getRequiredAuth().getKeys();
                        for (KeyWeight key : listKey) {
                            if (key.getKey().equals(publicKey)) {
                                isExist = true;
                                break;
                            }
                        }
                    }
                    sendMessage(GlobalMessageType.RequestCode.ACCOUNT_IS_EXIT, isExist);
                } catch (IOException e) {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                }
            }
        }.start();
    }

    @Override
    public void withdraw(int userRewardId) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        JSONObject jsonObject = JSONObject.parseObject(dataString);
                        if (null != jsonObject) {
                            sendMessage(GlobalMessageType.RequestCode.WITHDRAW, jsonObject);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("userRewardId", userRewardId);
        setParams(request, "withdraw", jsonData);
        request.setModel(RequestHost.User.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getBalanceOfNbo(String address, boolean isHome) {
        final Map<String, BigDecimal>[] balanceMap = new Map[]{null};
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    balanceMap[0] = eos4j.getCurrencyBalance(address, Constant.NBO_CONTRACT_ADDRESS);
                    List<BalanceBean> list = new ArrayList<>();
                    if (null != balanceMap[0] && balanceMap[0].size() > 0) {
                        if (balanceMap[0].containsKey(Constant.TYPE_NBO)) {
                            BalanceBean balanceBean = new BalanceBean();
                            balanceBean.setBalance(balanceMap[0].get(Constant.TYPE_NBO).stripTrailingZeros().toPlainString());
                            balanceBean.setType(Constant.TYPE_NBO);
                            list.add(balanceBean);
                        } else {
                            BalanceBean balanceAoc = new BalanceBean();
                            balanceAoc.setType(Constant.TYPE_NBO);
                            balanceAoc.setBalance("0");
                            list.add(balanceAoc);
                        }
                    } else {
                        BalanceBean balanceNbo = new BalanceBean();
                        balanceNbo.setType(Constant.TYPE_NBO);
                        balanceNbo.setBalance("0");
                        list.add(balanceNbo);
                    }
                    if (isHome) {
                        sendMessage(GlobalMessageType.RequestCode.GETNBOBALANCE_HOME, list);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.GETNBOBALANCE, list);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void orePool(String type, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        NboPollBean bean = JSON.parseObject(dataString, NboPollBean.class);
                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.OREPOOL, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "orePool", jsonData);
        switch (type) {
            case Constant.TYPE_NBO:
                request.setModel(RequestHost.Nbo.PUBLIC);
                break;
            case Constant.TYPE_DDAO:
                request.setModel(RequestHost.DDAO.PUBLIC);
                break;
            case Constant.TYPE_LON:
                request.setModel(RequestHost.LON.PUBLIC);
                break;
        }

        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void computationalPower(String type, String address, int page, int size) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        NboMiningPowerBean bean = JSON.parseObject(dataString, NboMiningPowerBean.class);
                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.COMPUTATIONALPOWER, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("pageNumber", page);
        jsonData.put("pageSize", size);
        jsonData.put("address", address);
        setParams(request, "childList", jsonData);
        switch (type) {
            case Constant.TYPE_NBO:
                request.setModel(RequestHost.Nbo.PUBLIC);
                break;
            case Constant.TYPE_DDAO:
                request.setModel(RequestHost.DDAO.PUBLIC);
                break;
            case Constant.TYPE_LON:
                request.setModel(RequestHost.LON.PUBLIC);
                break;
        }
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void orePoolDetails(String type, String address, int page, int size) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        NboProfitDetailBean bean = JSON.parseObject(dataString, NboProfitDetailBean.class);
                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.OREPOOLDETAILS, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        jsonData.put("pageNumber", page);
        jsonData.put("pageSize", size);
        setParams(request, "profitDetail", jsonData);
        switch (type) {
            case Constant.TYPE_NBO:
                request.setModel(RequestHost.Nbo.PUBLIC);
                break;
            case Constant.TYPE_DDAO:
                request.setModel(RequestHost.DDAO.PUBLIC);
                break;
            case Constant.TYPE_LON:
                request.setModel(RequestHost.LON.PUBLIC);
                break;
        }

        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getPollAddressState(String type, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        NboStateBean bean = JSON.parseObject(dataString, NboStateBean.class);
                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.COINSTATE, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });


        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "addressState", jsonData);
        switch (type) {
            case Constant.TYPE_NBO:
                request.setModel(RequestHost.Nbo.PUBLIC);
                break;
            case Constant.TYPE_DDAO:
                request.setModel(RequestHost.DDAO.PUBLIC);
                break;
            case Constant.TYPE_LON:
                request.setModel(RequestHost.LON.PUBLIC);
                break;
        }
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void enableNboMine(String type, String privateKey, String deviceNumber, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        JSONObject jsonObject = JSONObject.parseObject(dataString);
                        if (null != jsonObject) {
                            sendMessage(GlobalMessageType.RequestCode.ENABLENBOMINE, jsonObject);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });


        JSONObject jsonData = new JSONObject();
        jsonData.put("privateKey", privateKey);
        jsonData.put("deviceNumber", deviceNumber);
        jsonData.put("address", address);
        request.setModel(RequestHost.Wallet.public_);

        switch (type) {
            case Constant.TYPE_NBO:
                setParams(request, "enableNboMine", jsonData);
                break;
            case Constant.TYPE_DDAO:
                setParams(request, "enableDdaoMine", jsonData);
                break;
            case Constant.TYPE_LON:
                setParams(request, "enableLonMine", jsonData);
                break;
        }
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void checkFlashNbo(String deviceNumber) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        JSONObject jsonObject = JSONObject.parseObject(dataString);
                        if (null != jsonObject) {
                            sendMessage(GlobalMessageType.RequestCode.CHECKFLASHNBO, jsonObject);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });

        JSONObject jsonData = new JSONObject();
        jsonData.put("deviceNumber", deviceNumber);
        setParams(request, "checkFlashNbo", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void powerInfo(String type, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        NboPowerInfoBean bean = JSON.parseObject(dataString, NboPowerInfoBean.class);
                        if (null != bean) {
                            if (bean.getCode() == 200) {
                                sendMessage(GlobalMessageType.RequestCode.POWERINFO, bean);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.ERROR, bean.getMessage());
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        setParams(request, "powerInfo", jsonData);
        switch (type) {
            case Constant.TYPE_NBO:
                request.setModel(RequestHost.Nbo.PUBLIC);
                break;
            case Constant.TYPE_DDAO:
                request.setModel(RequestHost.DDAO.PUBLIC);
                break;
            case Constant.TYPE_LON:
                request.setModel(RequestHost.LON.PUBLIC);
                break;
        }
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void withdrawProfit(int profitId, String symbols) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        JSONObject jsonObject = JSONObject.parseObject(dataString);
                        if (null != jsonObject) {
                            sendMessage(GlobalMessageType.RequestCode.WITHDRAWPROFIT, jsonObject);
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("profitId", profitId);
        setParams(request, "withdrawProfit", jsonData);
        switch (symbols) {
            case Constant.TYPE_NBO:
                request.setModel(RequestHost.Nbo.PUBLIC);
                break;
            case Constant.TYPE_DDAO:
                request.setModel(RequestHost.DDAO.PUBLIC);
                break;
            case Constant.TYPE_LON:
                request.setModel(RequestHost.LON.PUBLIC);
                break;
        }
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void getBalance(String address, String symbols) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
                if (ProtocolType.ResponseEvent.SUCCESS == event) {
                    try {
                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
                        JSONObject jsonObject = JSONObject.parseObject(dataString);
                        if (null != jsonObject) {
                            if (symbols.equals(Constant.ALL_APOLLO_KINDS)) {
                                sendMessage(GlobalMessageType.RequestCode.APOLLO_BALANCE, jsonObject);
                            } else {
                                sendMessage(GlobalMessageType.RequestCode.APOLLO_ONE_KIND_BALANCE, jsonObject);
                            }
                        } else {
                            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
                    }
                } else {
                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("address", address);
        jsonData.put("symbols", symbols);
        setParams(request, "getBalance", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    @Override
    public void addAccountEth(String privateKey, String deviceNumber, String address) {
        DynaRequest request = new DynaRequest(mcontext, (invoker, event, result) -> {
            if (ProtocolType.ResponseEvent.isFinish(event)) {
//                if (ProtocolType.ResponseEvent.SUCCESS == event) {
//                    try {
//                        String dataString = AndroidRSAUtils.deCodeByPublic(result.data.getJsonContent(), PUBLICKEY);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }  else {
//                    sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.tip245));
//                }
            }
        });
        JSONObject jsonData = new JSONObject();
        jsonData.put("privateKey", privateKey);
        jsonData.put("deviceNumber", deviceNumber);
        jsonData.put("address", address);
        setParams(request, "addAccountEth", jsonData);
        request.setModel(RequestHost.Wallet.public_);
        request.exec(ResponseDataType.HttpMethod.POST);
    }

    public void setParams(DynaRequest request, String funcName, JSONObject jsonData) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("func", funcName);
            jsonBody.put("data", jsonData);
            String content = jsonBody.toJSONString();
            String enResult = AndroidRSAUtils.enCode(content, PUBLICKEY);
            request.addParam("reqBody", enResult);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage(GlobalMessageType.RequestCode.ERROR, mcontext.getString(R.string.error_data));
        }
    }

}
