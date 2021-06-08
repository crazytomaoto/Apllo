package com.sz.apollo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.flyco.roundview.RoundTextView;
import com.gyf.barlibrary.ImmersionBar;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建钱包--阿波罗
 */
public class ChooseApolloWalletWayAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_import)
    RoundTextView btnImport;
    @BindView(R.id.btn_create)
    RoundTextView btnCreate;
    FingerprintCallback fingerprintCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_apl_wallet_way);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarColor(R.color.page_color2)//这里的颜色，你可以自定义。
                .init();
        initView();
    }

    private void initView() {
//        tvTitle.setText(getString(R.string.create_address));
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                UiHelper.startCreateApolloWalletSuccessAcy(ChooseApolloWalletWayAcy.this);
            }

            @Override
            public void onFailed() {
                ToastUtil.show(ChooseApolloWalletWayAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(ChooseApolloWalletWayAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(ChooseApolloWalletWayAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(ChooseApolloWalletWayAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseApolloWalletWayAcy.this);
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

    @OnClick({R.id.btn_import, R.id.btn_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_import:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startImportApolloAcy(this);
                break;
            case R.id.btn_create:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(ChooseApolloWalletWayAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(ChooseApolloWalletWayAcy.this, R.color.colorPrimary))
                            .build();
                } else {
                    DialogUtil.dialogPwd(this, new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {
                            if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                UiHelper.startCreateApolloWalletSuccessAcy(ChooseApolloWalletWayAcy.this);
                            } else {
                                ToastUtil.show(ChooseApolloWalletWayAcy.this, getString(R.string.error_pwd));
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
}
