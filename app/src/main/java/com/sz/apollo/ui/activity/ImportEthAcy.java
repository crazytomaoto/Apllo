package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.client.android.utils.ScanUtils;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.EthWalletUtil;
import com.sz.apollo.ui.utils.PermissionUtils;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 导入以太坊钱包
 */

public class ImportEthAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_tip1)
    TextView tvTip1;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.ll_key)
    LinearLayout llKey;
    @BindView(R.id.tv_create)
    Button tvCreate;
    private int way = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_eth_wallet);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (way == 1) {
            tvTitle.setText(R.string.tip24);
            edContent.setHint(R.string.tip27);
            tvTip1.setText(R.string.tip19);
            llKey.setVisibility(View.GONE);
        }
        if (way == 2) {
            tvTitle.setText(R.string.tip23);
            edContent.setHint(R.string.tip26);
            tvTip1.setText(R.string.tip18);
            llKey.setVisibility(View.GONE);
        }
        if (way == 3) {
            tvTitle.setText(R.string.tip25);
            edContent.setHint(R.string.tip28);
            tvTip1.setText(R.string.tip20);
            llKey.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            way = bundle.getInt("way");
        }
    }

    @SuppressLint("NewApi")
    @OnClick({R.id.iv_scan, R.id.tv_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                String[] permissions = PermissionUtils.getCamera();
                if (PermissionUtils.checkPermissions(this, permissions)) {
                    requestPermissions(permissions, PermissionUtils.REQUEST_CODE);
                } else {
                    UiHelper.startScanAcy(this);
                }
                break;
            case R.id.tv_create:

                if (ClickUtil.isNotFirstClick()) {
                    return;
                }

                String content = edContent.getText().toString().trim();
                String keystorePass = edPass.getText().toString().trim();

                UserWalletBean userWalletBean = new UserWalletBean();
                UserWalletBean nowWallet = DaoUtil.selectNowWallet();
                if (way == 1) {
                    if (StringUtils.isEmpty(content)) {
                        ToastUtil.show(this, getString(R.string.tip27));
                        return;
                    }
                    ECKeyPair ecKeyPair = EthWalletUtil.generateKeyPair(content);
                    String pk = ecKeyPair.getPrivateKey().toString(16);
                    String publicKey = ecKeyPair.getPublicKey().toString(16);
                    WalletFile aWallet = null;
                    try {
                        aWallet = Wallet.createLight(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS), ecKeyPair);
                        String address = "0x" + aWallet.getAddress();

                        if (null != DaoUtil.selectWalletByAddress(address)) {
                            ToastUtil.show(this, getString(R.string.tip151));
                            break;
                        } else {
                            if (null != nowWallet) {
                                nowWallet.setIsNowWallet("0");
                                DaoUtil.updateWallet(nowWallet);
                            }
                            String keyStore = JSON.toJSONString(aWallet);
                            userWalletBean.setIsBackedUp("0");
                            userWalletBean.setAddress(address);
                            userWalletBean.setPhrase("");
                            userWalletBean.setPk(pk);
                            userWalletBean.setPublicKey(publicKey);
                            userWalletBean.setKeystore(keyStore);
                            userWalletBean.setType(Constant.TYPE_ETH);
                            userWalletBean.setWalletExistWay("b");
                            userWalletBean.setRemark(Constant.ETH_REMARK);
                            userWalletBean.setIsNowWallet("1");
                            userWalletBean.setIsAddUSDT("0");
                            DaoUtil.addWallet(userWalletBean);
                            ApolloApplication.getInstance().finishAllActivity();
                            UiHelper.startHomepageActivity(this);
                            iRequest.addAccountEth(userWalletBean.getPk(), Util.getDeviceOnlyNum(this), userWalletBean.getAddress());
                            finish();
                        }
                    } catch (CipherException e) {
                        ToastUtil.show(this, getString(R.string.tip152));
                        break;
                    }
                }
                if (way == 2) {
                    if (StringUtils.isEmpty(content)) {
                        ToastUtil.show(this, getString(R.string.tip26));
                        return;
                    }
                    if (content.length() != 64) {
                        ToastUtil.show(this, getString(R.string.tip149));
                        return;
                    }

                    ECKeyPair ecKeyPair = null;
                    String pk = null;
                    String publicKey = null;
                    try {
                        ecKeyPair = EthWalletUtil.getKeyPair(content);
                        pk = ecKeyPair.getPrivateKey().toString(16);
                        publicKey = ecKeyPair.getPublicKey().toString(16);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtil.show(this, getString(R.string.tip149));
                        break;
                    }

                    WalletFile aWallet = null;
                    try {
                        aWallet = Wallet.createLight(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS), ecKeyPair);
                        String address = "0x" + aWallet.getAddress();

                        if (null != DaoUtil.selectWalletByAddress(address)) {
                            ToastUtil.show(this, getString(R.string.tip151));
                            break;
                        } else {
                            if (null != nowWallet) {
                                nowWallet.setIsNowWallet("0");
                                DaoUtil.updateWallet(nowWallet);
                            }
                            String keyStore = JSON.toJSONString(aWallet);
                            userWalletBean.setIsBackedUp("0");
                            userWalletBean.setAddress(address);
                            userWalletBean.setPhrase("");
                            userWalletBean.setPk(pk);
                            userWalletBean.setPublicKey(publicKey);
                            userWalletBean.setKeystore(keyStore);
                            userWalletBean.setType(Constant.TYPE_ETH);
                            userWalletBean.setWalletExistWay("b");
                            userWalletBean.setRemark(Constant.ETH_REMARK);
                            userWalletBean.setIsNowWallet("1");
                            userWalletBean.setIsAddUSDT("0");
                            DaoUtil.addWallet(userWalletBean);
                            ApolloApplication.getInstance().finishAllActivity();
                            UiHelper.startHomepageActivity(this);
                            iRequest.addAccountEth(userWalletBean.getPk(), Util.getDeviceOnlyNum(this), userWalletBean.getAddress());
                            finish();
                        }
                    } catch (CipherException e) {
                        ToastUtil.show(this, getString(R.string.tip152));
                        break;
                    }
                }
                if (way == 3) {
                    if (StringUtils.isEmpty(content)) {
                        ToastUtil.show(this, getString(R.string.tip28));
                        return;
                    }
                    if (StringUtils.isEmpty(keystorePass)) {
                        ToastUtil.show(this, getString(R.string.tip154));
                        return;
                    }

                    WalletFile aWallet = null;
                    String pk = "";
                    String publicKey = "";
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        aWallet = objectMapper.readValue(content, WalletFile.class);//
                        ECKeyPair keyPair = Wallet.decrypt(keystorePass, aWallet);
                        if (null != keyPair) {
                            pk = keyPair.getPrivateKey().toString(16);
                            publicKey = keyPair.getPublicKey().toString(16);
                        }
                        if (null != aWallet) {
                            String address = "0x" + aWallet.getAddress();
                            if (null != DaoUtil.selectWalletByAddress(address)) {
                                ToastUtil.show(this, getString(R.string.tip151));
                                break;
                            } else {
                                if (null != nowWallet) {
                                    nowWallet.setIsNowWallet("0");
                                    DaoUtil.updateWallet(nowWallet);
                                }
                                userWalletBean.setIsBackedUp("0");
                                userWalletBean.setAddress(address);
                                userWalletBean.setPhrase("");
                                userWalletBean.setPk(pk);
                                userWalletBean.setPublicKey(publicKey);
                                userWalletBean.setKeystore(content);
                                userWalletBean.setType(Constant.TYPE_ETH);
                                userWalletBean.setWalletExistWay("b");
                                userWalletBean.setRemark(Constant.ETH_REMARK);
                                userWalletBean.setIsNowWallet("1");
                                userWalletBean.setIsAddUSDT("0");
                                DaoUtil.addWallet(userWalletBean);
                                ApolloApplication.getInstance().finishAllActivity();
                                UiHelper.startHomepageActivity(this);
                                iRequest.addAccountEth(userWalletBean.getPk(), Util.getDeviceOnlyNum(this), userWalletBean.getAddress());
                                finish();
                            }
                        }
                    } catch (CipherException e) {
                        e.printStackTrace();
                        if (e.getMessage().equals("Invalid password provided")) {
                            ToastUtil.show(this, "密码不正确,导入失败");
                        }
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == ScanUtils.SCAN_CODE) {
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
                ToastUtil.show(this, "扫码识别返回：" + e.getMessage());
            }
        }
    }

    /**
     * 扫码返回的结果
     *
     * @param scanResult 结果
     */
    public void onResultScanData(String scanResult) {
        edContent.setText(scanResult);
    }
}
