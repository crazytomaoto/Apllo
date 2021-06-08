package com.sz.apollo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IGetBackResult;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.FlashResultBean;
import com.sz.apollo.ui.models.TransBean;
import com.sz.apollo.ui.models.TransResultBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.MyWeb3jUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.utils.Convert;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sz.apollo.commons.constants.Constant.GAS_LIMIT;

/**
 * 闪兑--支付页面
 */
public class PaymentAotAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.btn_sure)
    TextView btnSure;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.ll_pass)
    LinearLayout llPass;
    private String theAddress;//以太坊地址
    private String amount, txHash;
    private List<UserWalletBean> listEthWallet;
    private List<BalanceBean> balanceBeans;
    private BigInteger gasPrice;
    /**
     * 所有请求的总的线程数
     */
    private ExecutorService executors;
    private FingerprintCallback fingerprintCallback;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            amount = bundle.getString("amount");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.GETUSDTBALANCE:
                BalanceBean balanceBean = (BalanceBean) message.obj;
                if (null != balanceBean) {
                    balanceBeans.add(balanceBean);
                }
                if (balanceBeans.size() == listEthWallet.size()) {
                    theAddress = balanceBeans.get(0).getAddress();
                    tvAccount.setText(StringUtils.hideMiddleString(theAddress, 13, 5));
                    hud.dismiss();
                }
                break;
            case GlobalMessageType.RequestCode.GETETHBALANCE:
                BalanceBean balanceBeanEth = (BalanceBean) message.obj;
                if (Double.parseDouble(balanceBeanEth.getBalance()) < 0.002) {
                    hud.dismiss();
                    ToastUtil.show(this, getString(R.string.tip179));
                    return;
                } else {
                    UserWalletBean userWalletBean = DaoUtil.selectWalletByAddress(balanceBeanEth.getAddress());
                    translate(userWalletBean);
                }
                break;
            case GlobalMessageType.RequestCode.SENDUAOT:
                iRequest.flashExchange(1, theAddress, DaoUtil.selectNowWallet().getAddress(), Double.parseDouble(amount), Constant.TYPE_AOT, txHash, Util.getDeviceOnlyNum(this));
                break;
            case GlobalMessageType.RequestCode.FLASH_EXCHANGE:
                hud.dismiss();
                FlashResultBean flashResultBean = (FlashResultBean) message.obj;
                if (null != flashResultBean) {
                    if (flashResultBean.getCode() == 200) {
                        ToastUtil.show(this, flashResultBean.getMessage());
                        finish();
                    }
                }
                break;

            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                String error = (String) message.obj;
                if (!StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                break;
        }
    }

    /**
     * 发起交易
     */
    private void translate(UserWalletBean userWalletBean) {
        EthGetTransactionCount ethGetTransactionCount = null;
        try {
            ethGetTransactionCount = MyWeb3jUtil.getWeb3jInstance().ethGetTransactionCount(
                    userWalletBean.getAddress()
                    , DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        String signData = null;//
        try {
            signData = MyWeb3jUtil.transferERC20Token(
                    userWalletBean,
                    Constant.RECEIVE_ADDRESS,
                    amount,
                    nonce,
                    RequestHost.usdtContract,
                    gasPrice,
                    GAS_LIMIT
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<>();
        TransBean transBean = new TransBean();
        transBean.setId(1);
        transBean.setMethod("eth_sendRawTransaction");
        transBean.setJsonrpc("2.0");
        transBean.setParams(list);
        list.add(signData);
        String json = JSON.toJSONString(transBean);
        new Thread() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(RequestHost.ethNetUrl);
                params.setAsJsonContent(true);
                params.setBodyContent(json);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        TransResultBean transResultBean = JSON.parseObject(result, TransResultBean.class);
                        if (null != transResultBean) {
                            if (StringUtils.isEmpty(transResultBean.getResult())) {
                                hud.dismiss();
                                ToastUtil.show(PaymentAotAcy.this, getString(R.string.tip144));
                            } else {
                                if (transResultBean.getResult().length() == 66) {
                                    txHash = transResultBean.getResult();
                                    sendEmptyMessage(GlobalMessageType.RequestCode.SENDUAOT);
                                } else {
                                    hud.dismiss();
                                    ToastUtil.show(PaymentAotAcy.this, getString(R.string.tip144));
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        hud.dismiss();
                        ToastUtil.show(PaymentAotAcy.this, getString(R.string.tip144));
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        hud.dismiss();
                        ToastUtil.show(PaymentAotAcy.this, getString(R.string.tip146));
                    }

                    @Override
                    public void onFinished() {
                    }
                });

            }
        }.start();
    }

    private void initView() {
        tvTitle.setText(R.string.tip82);
        tvAmount.setText(amount + "USDT");
        new Thread() {
            @Override
            public void run() {
                super.run();
                EthGasPrice ethGasPrice = null;
                try {
                    ethGasPrice = MyWeb3jUtil.getWeb3jInstance().ethGasPrice().send();
                    gasPrice = ethGasPrice.getGasPrice();
                    String gasPriceString = gasPrice.toString(10);
                    Long longPrice = Long.parseLong(gasPriceString) * 80 / 100;
                    gasPrice = BigInteger.valueOf(longPrice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        initLoading(this);
        hud.setCancellable(false);
        balanceBeans = new ArrayList<>();
        listEthWallet = new ArrayList<>();

        listEthWallet = DaoUtil.selectAllWalletByType(Constant.TYPE_ETH);
        if (null != listEthWallet && listEthWallet.size() > 0) {
            executors = Executors.newFixedThreadPool(listEthWallet.size());
            btnSure.setEnabled(true);
        } else {
            llAccount.setVisibility(View.GONE);
            btnSure.setEnabled(false);
            ToastUtil.show(this, getString(R.string.tip178));
            return;
        }
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                hud.show();
                getWalletEth(theAddress);
            }

            @Override
            public void onFailed() {
                ToastUtil.show(PaymentAotAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(PaymentAotAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(PaymentAotAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(PaymentAotAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentAotAcy.this);
                builder.setTitle(getString(R.string.biometricprompt_tip))
                        .setMessage(getString(R.string.biometricprompt_finger_add))
                        .setCancelable(false)
                        .setNegativeButton(getString(R.string.biometricprompt_finger_add_confirm), ((DialogInterface dialog, int which) -> {
                            Intent intent = new Intent(Settings.ACTION_FINGERPRINT_ENROLL);
                            startActivity(intent);
                        }
                        ))
                        .setPositiveButton(getString(R.string.biometricprompt_cancel), ((DialogInterface dialog, int which) -> {
                            dialog.dismiss();
                        }
                        ))
                        .create().show();
            }
        };
        if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
            llPass.setVisibility(View.GONE);
        } else {
            llPass.setVisibility(View.VISIBLE);
        }

        hud.show();
        //创建及执行
        executors = Executors.newFixedThreadPool(listEthWallet.size());
//执行上述Demo的runnable

        for (int i = 0; i < listEthWallet.size(); i++) {
            int finalI = i;
            executors.execute(() -> {
                try {
                    EthCall ethCall = MyWeb3jUtil.getWeb3jInstance().ethCall(Transaction.createEthCallTransaction(listEthWallet.get(finalI).getAddress(), RequestHost.usdtContract, "0x70a08231000000000000000000000000" + listEthWallet.get(finalI).getAddress().substring(2)), DefaultBlockParameterName.PENDING).send();
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
                        } else {
                            balanceBean.setBalance("0");
                        }
                        balanceBean.setType(Constant.TYPE_U);
                        balanceBean.setAddress(listEthWallet.get(finalI).getAddress());
                        sendMessage(GlobalMessageType.RequestCode.GETUSDTBALANCE, balanceBean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.GETUSDTALANCE_ERROR, ethCall.getError());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @OnClick({R.id.ll_account, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_account:
                DialogUtil.dialogPayForFlashExchange(this, theAddress, balanceBeans, new IGetBackResult() {
                    @Override
                    public void getTextString(Object result) {
                        if (null != result) {
                            BalanceBean getBean = (BalanceBean) result;
                            if (null != getBean) {
                                theAddress = getBean.getAddress();
                                tvAccount.setText(StringUtils.hideMiddleString(theAddress, 13, 5));
                            }
                        }
                    }
                });
                break;
            case R.id.btn_sure:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(PaymentAotAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(PaymentAotAcy.this, R.color.colorPrimary))
                            .build();
                    llPass.setVisibility(View.GONE);
                } else {
                    String pass = edPass.getText().toString().trim();
                    if (!StringUtils.isEmpty(pass) && pass.length() >= 6) {
                        if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                            hud.show();
                            getWalletEth(theAddress);
                        } else {
                            ToastUtil.show(this, getString(R.string.error_pwd));
                            return;
                        }
                    } else {
                        ToastUtil.show(this, getString(R.string.error_pwd));
                    }
                }

                break;
        }
    }

    private void getWalletEth(String theAddress) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                EthGetBalance ethGetBalance = null;
                try {
                    ethGetBalance = MyWeb3jUtil.getWeb3jInstance().ethGetBalance(theAddress, DefaultBlockParameterName.LATEST).send();
                    if (ethGetBalance != null) {
                        BigDecimal nig = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
                        String resultBalance = nig.stripTrailingZeros().toPlainString();
                        BalanceBean balanceBean = new BalanceBean();
                        balanceBean.setBalance(resultBalance);
                        balanceBean.setType(Constant.TYPE_ETH);
                        balanceBean.setAddress(theAddress);
                        sendMessage(GlobalMessageType.RequestCode.GETETHBALANCE, balanceBean);
                    } else {
                        sendMessage(GlobalMessageType.RequestCode.GETETHBALANCE_ERROR, ethGetBalance.getError());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        if (null != executors) {
            executors.shutdownNow();
            executors = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != executors) {
            executors.shutdownNow();
            executors = null;
        }
    }
}
