package com.sz.apollo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSON;
import com.flyco.roundview.RoundTextView;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.EthWalletUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 以太坊钱包备份提示页----创建以太坊钱包的页面
 */
public class BackupEthAcy extends BasicActivity {
    @BindView(R.id.tv_backup_no)
    RoundTextView tvBackupNo;
    private boolean isFromChargeBackup;
    private int backupType;
    private UserWalletBean userWalletBean;
    private String pk, address, publicKey, phrase, keyStore;
    private FingerprintCallback fingerprintCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_eth);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        userWalletBean = new UserWalletBean();
        if (isFromChargeBackup) {
            tvBackupNo.setVisibility(View.GONE);
        } else {
            tvBackupNo.setVisibility(View.VISIBLE);
            phrase = EthWalletUtil.generateMnemonics();
            ECKeyPair ecKeyPair = EthWalletUtil.generateKeyPair(phrase);
            pk = ecKeyPair.getPrivateKey().toString(16);
            publicKey = ecKeyPair.getPublicKey().toString(16);
            WalletFile aWallet = null;
            try {
                aWallet = Wallet.createLight(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS), ecKeyPair);
                address = "0x" + aWallet.getAddress();
                keyStore = JSON.toJSONString(aWallet);
            } catch (CipherException e) {
                e.printStackTrace();
            }

            userWalletBean.setIsBackedUp("0");
            userWalletBean.setAddress(address);
            userWalletBean.setPhrase(phrase);
            userWalletBean.setPk(pk);
            userWalletBean.setPublicKey(publicKey);
            userWalletBean.setKeystore(keyStore);
            userWalletBean.setType(Constant.TYPE_ETH);
            userWalletBean.setRemark(Constant.ETH_REMARK);
            userWalletBean.setWalletExistWay("a");
            userWalletBean.setIsAddUSDT("0");
            iRequest.addAccountEth(userWalletBean.getPk(), Util.getDeviceOnlyNum(this), userWalletBean.getAddress());
        }
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                if (backupType == 1) {
                    UiHelper.startBackupPhraseAcy(BackupEthAcy.this, isFromChargeBackup, userWalletBean);
                } else {
                    UiHelper.startBackupPKOrKeyAcy(BackupEthAcy.this, backupType, userWalletBean);
                }
            }

            @Override
            public void onFailed() {
                ToastUtil.show(BackupEthAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(BackupEthAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(BackupEthAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(BackupEthAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(BackupEthAcy.this);
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

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            isFromChargeBackup = bundle.getBoolean("isFromChargeBackup");
            backupType = bundle.getInt("backupType");
        }
    }

    @OnClick({R.id.tv_backup, R.id.tv_backup_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_backup:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (isFromChargeBackup) {
                    userWalletBean = DaoUtil.selectNowWallet();
                }
                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(BackupEthAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(BackupEthAcy.this, R.color.colorPrimary))
                            .build();
                } else {
                    DialogUtil.dialogPwd(this, new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {
                            if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                if (backupType == 1) {
                                    UiHelper.startBackupPhraseAcy(BackupEthAcy.this, isFromChargeBackup, userWalletBean);
                                } else {
                                    UiHelper.startBackupPKOrKeyAcy(BackupEthAcy.this, backupType, userWalletBean);
                                }
                            } else {
                                ToastUtil.show(BackupEthAcy.this, getString(R.string.error_pwd));
                            }
                        }

                        @Override
                        public void cancle() {
                        }
                    });
                }

                break;
            case R.id.tv_backup_no:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                //更新选中钱包
                UserWalletBean nowWallet = DaoUtil.selectNowWallet();
                if (null != nowWallet) {
                    nowWallet.setIsNowWallet("0");
                    DaoUtil.updateWallet(nowWallet);
                }

                userWalletBean.setIsNowWallet("1");
                DaoUtil.addWallet(userWalletBean);

                ApolloApplication.getInstance().finishAllActivity();
                UiHelper.startHomepageActivity(this);
                break;
        }
    }
}
