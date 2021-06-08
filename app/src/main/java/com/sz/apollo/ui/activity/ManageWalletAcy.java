package com.sz.apollo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 钱包管理页面
 */
public class ManageWalletAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.ll_card_name)
    LinearLayout llCardName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_export_phrase)
    LinearLayout llExportPhrase;
    @BindView(R.id.ll_export_pk)
    LinearLayout llExportPk;
    @BindView(R.id.ll_export_key)
    LinearLayout llExportKey;
    @BindView(R.id.btn_delete)
    TextView btnDelete;
    private String getAddress;
    private UserWalletBean nowWallet;
    private FingerprintCallback fingerprintCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_wallet);
        ButterKnife.bind(this);
        iniview();
    }

    private void iniview() {
        tvTitle.setText(R.string.t_manage);
        nowWallet = DaoUtil.selectNowWallet();
        tvCardName.setText(nowWallet.getRemark());
        if (nowWallet.getType().equals(Constant.TYPE_APOLLO)) {
            tvAddress.setText(nowWallet.getAddress());
            llExportPhrase.setVisibility(View.GONE);
            llExportKey.setVisibility(View.GONE);
        } else {
            if (!nowWallet.getWalletExistWay().equals("a")) {
                llExportPhrase.setVisibility(View.GONE);
            }
            tvAddress.setText(StringUtils.hideMiddleString(nowWallet.getAddress(), 15, 5));
        }
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                uiGo();
            }

            @Override
            public void onFailed() {
                ToastUtil.show(ManageWalletAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(ManageWalletAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(ManageWalletAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(ManageWalletAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageWalletAcy.this);
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

    @OnClick({R.id.ll_card_name, R.id.ll_export_phrase, R.id.ll_export_pk, R.id.ll_export_key, R.id.ll_address, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_card_name:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startChangeCardNameAcy(this);
                break;
            case R.id.ll_address:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                Util.copy(this, nowWallet.getAddress());
                ToastUtil.show(this, getString(R.string.t_copied));
                break;
            case R.id.ll_export_phrase:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startBackupEthAcy(this, true, 1);
                break;
            case R.id.ll_export_pk:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startBackupEthAcy(this, true, 2);
                break;
            case R.id.ll_export_key:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startBackupEthAcy(this, true, 3);
                break;
            case R.id.btn_delete:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(ManageWalletAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(ManageWalletAcy.this, R.color.colorPrimary))
                            .build();
                } else {
                    DialogUtil.dialogPwd(this, new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {
                            if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                //判断钱包是否还有，没有就去创建
                                hideInput();
                                uiGo();
                                //有就进入
                            } else {
                                ToastUtil.show(ManageWalletAcy.this, getString(R.string.error_pwd));
                            }
                        }

                        @Override
                        public void cancle() {

                        }
                    });
                }
                break;
        }
    }

    private void uiGo() {
        //判断钱包是否还有，没有就去创建
        DaoUtil.deleteWallet(nowWallet);
        List<UserWalletBean> list = DaoUtil.selectAllWallet();
        if (list.size() == 0) {
            UiHelper.startChooseTypeAcy(ManageWalletAcy.this);
            finish();
        } else {
            nowWallet = list.get(0);
            nowWallet.setIsNowWallet("1");
            DaoUtil.updateWallet(nowWallet);
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        nowWallet = DaoUtil.selectNowWallet();
        tvCardName.setText(nowWallet.getRemark());
    }
}
