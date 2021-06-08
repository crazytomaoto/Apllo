package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSON;
import com.google.zxing.client.android.utils.ScanUtils;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.TransBean;
import com.sz.apollo.ui.models.TransResultBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.MyWeb3jUtil;
import com.sz.apollo.ui.utils.PermissionUtils;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sz.apollo.commons.constants.Constant.GAS_LIMIT;

/**
 * 转账页面
 */
public class TranslateEthAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.edit_receive_address)
    EditText editReceiveAddress;
    @BindView(R.id.iv_contract)
    ImageView ivContract;
    @BindView(R.id.edit_amount)
    EditText editTranAmount;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.edit_sign)
    EditText editSign;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    //    @BindView(R.id.tv_available)
//    TextView tvAvailable;
//    @BindView(R.id.ed_sign)
//    EditText edSign;
    private UserWalletBean userWalletBean;
    private int gasLimit = 25200;
    private String tokenType;
    private BalanceBean balanceBean;
    private String balanceString;
    private FingerprintCallback fingerprintCallback;
    private BigInteger gasPrice;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            tokenType = bundle.getString("tokenType");
        }
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.GETETHBALANCE:
                hud.dismiss();
                if (tokenType.equals(Constant.TYPE_ETH)) {
                    BalanceBean balanceBean = (BalanceBean) message.obj;
                    balanceString = balanceBean.getBalance();
                    tvBalance.setText(getString(R.string.t_balance) + ":" + Util.getStringFromDecimal(balanceString, 4));
                }
                break;
            case GlobalMessageType.RequestCode.GETUSDTBALANCE:
                hud.dismiss();
                if (tokenType.equals(Constant.TYPE_U)) {
                    BalanceBean balanceBeanU = (BalanceBean) message.obj;
                    balanceString = balanceBeanU.getBalance();
                    tvBalance.setText(getString(R.string.t_balance) + ":" + Util.getStringFromDecimal(balanceString, 4));
                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_eth);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.t_transfer);
        initLoading(this);
        ivRight.setImageResource(R.drawable.ic_scan);
        userWalletBean = DaoUtil.selectNowWallet();
        tvType.setText(tokenType);
        hud.show();

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
        if (tokenType.equals(Constant.TYPE_ETH)) {
            iRequest.queryETHBalance(userWalletBean.getAddress(), Constant.TYPE_ETH);
            ivLogo.setBackgroundDrawable(getResources().getDrawable(R.drawable.logo_eth2));
        } else {
            iRequest.getTokenOfUsdt(userWalletBean.getAddress(), Constant.TYPE_U);
            ivLogo.setBackgroundDrawable(getResources().getDrawable(R.drawable.logo_usdt));
        }
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                try {
                    if (tokenType.equals(Constant.TYPE_ETH)) {
                        tranETH();
                    } else {
                        tranToken();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed() {
                ToastUtil.show(TranslateEthAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(TranslateEthAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(TranslateEthAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(TranslateEthAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(TranslateEthAcy.this);
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
    }

    @SuppressLint("NewApi")
    @OnClick({R.id.iv_right, R.id.iv_contract, R.id.btn_sure, R.id.ll_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                String[] permissions = PermissionUtils.getCamera();
                if (PermissionUtils.checkPermissions(this, permissions)) {
                    requestPermissions(permissions, PermissionUtils.REQUEST_CODE);
                } else {
                    UiHelper.startScanAcy(this);
                }
                break;
            case R.id.ll_type:
                UiHelper.startMoreTokenAcyTwo(this, tokenType, Constant.REQUEST_CODE);
                break;
            case R.id.btn_sure:
                String amount = editTranAmount.getText().toString().trim();
                if (StringUtils.isEmpty(amount)) {
                    ToastUtil.show(this, getString(R.string.tip36));
                    return;
                }

                double amountDouble = Double.parseDouble(amount);
                double balanceDouble = Double.parseDouble(balanceString);
                if (amountDouble >= balanceDouble) {
                    ToastUtil.show(this, getString(R.string.tip153));
                    return;
                }
                String toAddress = editReceiveAddress.getText().toString().trim();
                if (StringUtils.isEmpty(toAddress) || toAddress.length() != 42 || !toAddress.substring(0, 2).equals("0x")) {
                    ToastUtil.show(this, getString(R.string.tip170));
                    return;
                }
                if (toAddress.equals(userWalletBean.getAddress())) {
                    ToastUtil.show(this, getString(R.string.tip176));
                    return;
                }


                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(TranslateEthAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(TranslateEthAcy.this, R.color.colorPrimary))
                            .build();
                } else {
                    DialogUtil.dialogPwd(this, new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {
                            if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                try {
                                    if (tokenType.equals(Constant.TYPE_ETH)) {
                                        tranETH();
                                    } else {
                                        tranToken();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                ToastUtil.show(TranslateEthAcy.this, getString(R.string.error_pwd));
                            }
                        }

                        @Override
                        public void cancle() {
                        }
                    });
                }


//                try {
//                    if (tokenType.equals(Constant.TYPE_ETH)) {
//                        tranETH();
//                    } else {
//                        tranToken();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                UiHelper.startTransDetailOneAcy(this, "djdjdjdjddj", "AOT");
                break;
        }
    }


    private void tranToken() throws Exception {
        hud.show();
        EthGetTransactionCount ethGetTransactionCount = MyWeb3jUtil.getWeb3jInstance().ethGetTransactionCount(
                userWalletBean.getAddress()
                , DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        String amount = editTranAmount.getText().toString().trim();
        String signData = MyWeb3jUtil.transferERC20Token(userWalletBean, editReceiveAddress.getText().toString().trim(), amount
                , nonce, RequestHost.usdtContract, gasPrice,
                GAS_LIMIT);
        translate(signData);
    }

    private void tranETH() throws Exception {
        hud.show();
        EthGetTransactionCount ethGetTransactionCount = MyWeb3jUtil.getWeb3jInstance().ethGetTransactionCount(
                userWalletBean.getAddress()
                , DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//        int aaa = Integer.valueOf(String.valueOf(nonce));
//        nonce = new BigInteger((aaa + 1) + "");
        double amount = Double.parseDouble(editTranAmount.getText().toString().trim());
        BigDecimal money = new BigDecimal(amount);
        BigInteger gasLimitNow = new BigInteger(gasLimit + "");
        String signData = MyWeb3jUtil.signedEthTransactionData(
                userWalletBean,
                editReceiveAddress.getText().toString().trim(),
                nonce,
                gasPrice,
                GAS_LIMIT,
                money);
        translate(signData);
    }

    /**
     * 发起交易
     *
     * @param signedTransactionData 签名的交易哈希
     */
    private void translate(String signedTransactionData) {
        List<String> list = new ArrayList<>();
        TransBean transBean = new TransBean();
        transBean.setId(1);
        transBean.setMethod("eth_sendRawTransaction");
        transBean.setJsonrpc("2.0");
        transBean.setParams(list);
        list.add(signedTransactionData);
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
                        hud.dismiss();
                        TransResultBean transResultBean = JSON.parseObject(result, TransResultBean.class);
                        if (null != transResultBean) {
                            if (StringUtils.isEmpty(transResultBean.getResult())) {
                                ToastUtil.show(TranslateEthAcy.this, getString(R.string.tip144));
                            } else {
                                if (transResultBean.getResult().length() > 64) {
                                    ToastUtil.show(TranslateEthAcy.this, getString(R.string.tip145));
                                    finish();
                                } else {
                                    ToastUtil.show(TranslateEthAcy.this, getString(R.string.tip144));
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        hud.dismiss();
                        ToastUtil.show(TranslateEthAcy.this, getString(R.string.tip144));
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        hud.dismiss();
                        ToastUtil.show(TranslateEthAcy.this, getString(R.string.tip146));
                    }

                    @Override
                    public void onFinished() {
                        hud.dismiss();
                    }
                });

            }
        }.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        switch (requestCode) {
            case ScanUtils.SCAN_CODE:
                try {
                    Bundle bundle = data.getExtras();
                    assert bundle != null;
                    String scanResult = bundle.getString(ScanUtils.SCAN_DATA);
                    //将扫描出的信息显示出来
                    if (TextUtils.isEmpty(scanResult)) {
                        ToastUtil.show(this, getString(R.string.str_qr_error));
                        return;
                    }
                    onResultScanData(scanResult);
                } catch (Exception e) {
//                ToastUtil.show(this, "扫码识别返回：" + e.getMessage());
                }
                break;
            case Constant.REQUEST_CODE:
                if (resultCode == Constant.RESULT_CODE) {
                    tokenType = data.getStringExtra("nowType");
                    tvType.setText(tokenType);
                    hud.show();
                    if (tokenType.equals(Constant.TYPE_ETH)) {
                        iRequest.queryETHBalance(userWalletBean.getAddress(), Constant.TYPE_ETH);
                        ivLogo.setBackgroundDrawable(getResources().getDrawable(R.drawable.logo_eth2));
                    } else {
                        iRequest.getTokenOfUsdt(userWalletBean.getAddress(), Constant.TYPE_U);
                        ivLogo.setBackgroundDrawable(getResources().getDrawable(R.drawable.logo_usdt));
                    }
                }
                break;
        }
    }

    /**
     * 扫码返回的结果
     *
     * @param scanResult 结果
     */
    public void onResultScanData(String scanResult) {
        editReceiveAddress.setText(scanResult);
    }

}
