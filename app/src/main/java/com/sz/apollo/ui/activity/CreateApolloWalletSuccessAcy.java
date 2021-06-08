package com.sz.apollo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.CreateApolloBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.crypto.EccTool;
import com.sz.apollo.ui.utils.eoswallet.party.loveit.bip44forjava.utils.Bip44Utils;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 备份账户---创建钱包--阿波罗--成功后保存
 */
public class CreateApolloWalletSuccessAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.rl_tip)
    RelativeLayout rlTip;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.ll_copy)
    LinearLayout llCopy;
    private UserWalletBean userWalletBean;
    private String pk, publicKey, eosAccount;
    private FingerprintCallback fingerprintCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_apl_wallet_success);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.CREATEAPOLLOACCOUNT:
                CreateApolloBean createApolloBean = (CreateApolloBean) message.obj;
                if (null != createApolloBean) {
                    if (createApolloBean.getCode() == 200) {
                        switch1.setEnabled(true);
                        btnSure.setEnabled(true);
                        hud.dismiss();
                        eosAccount = createApolloBean.getData();
                        tvAccount.setText(eosAccount);
                    } else {
                        switch1.setEnabled(false);
                        btnSure.setEnabled(false);
                        hud.dismiss();
                        ToastUtil.show(this, createApolloBean.getMessage());
                    }
                }
                break;
        }
    }

    private void initView() {
        tvTitle.setText(R.string.save_account);
        initLoading(this);
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                rlTip.setVisibility(View.GONE);
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("PrivateKey:\n" + pk);
                llCopy.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailed() {
                switch1.setChecked(false);
                llCopy.setVisibility(View.GONE);
                ToastUtil.show(CreateApolloWalletSuccessAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(CreateApolloWalletSuccessAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(CreateApolloWalletSuccessAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(CreateApolloWalletSuccessAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateApolloWalletSuccessAcy.this);
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
        userWalletBean = new UserWalletBean();
        try {
            List<String> words = null;//助记词
            words = Bip44Utils.generateMnemonicWords();
            byte[] seed = Bip44Utils.getDefaultPathPrivateKeyBytes(words, 194);
            pk = EccTool.privateKeyFromSeed(seed);
            publicKey = EccTool.privateToPublic(pk);
            hud.show();
            hud.setCancellable(false);
            iRequest.createApolloAccount(publicKey, pk, Util.getDeviceOnlyNum(this));

        } catch (Exception e) {
            e.printStackTrace();
        }

        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!buttonView.isPressed()) {
                return;
            }
            if (isChecked) {
                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(CreateApolloWalletSuccessAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(CreateApolloWalletSuccessAcy.this, R.color.colorPrimary))
                            .build();
                } else {
                    DialogUtil.dialogPwd(CreateApolloWalletSuccessAcy.this, new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {
                            if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                rlTip.setVisibility(View.GONE);
                                tvContent.setVisibility(View.VISIBLE);
                                tvContent.setText("PrivateKey:\n" + pk);
                                llCopy.setVisibility(View.VISIBLE);
                            } else {
                                ToastUtil.show(CreateApolloWalletSuccessAcy.this, getString(R.string.tip140));
                                llCopy.setVisibility(View.GONE);
                                switch1.setChecked(false);
                            }
                        }

                        @Override
                        public void cancle() {
                            switch1.setChecked(false);
                        }
                    });
                }
            }
        });
    }

    @OnClick({R.id.btn_sure, R.id.ll_copy})
    public void onViewClicked(View v) {

        switch (v.getId()) {
            case R.id.btn_sure:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }

                //更新选中钱包
                UserWalletBean nowWallet = DaoUtil.selectNowWallet();
                if (null != nowWallet) {
                    nowWallet.setIsNowWallet("0");
                    DaoUtil.updateWallet(nowWallet);
                }
                userWalletBean.setAddress(eosAccount);
                userWalletBean.setIsBackedUp("1");
                userWalletBean.setIsNowWallet("1");
                userWalletBean.setPk(pk);
                userWalletBean.setPublicKey(publicKey);
                userWalletBean.setType(Constant.TYPE_APOLLO);
                userWalletBean.setRemark(Constant.TYPE_APOLLO);
                userWalletBean.setPhrase("");
                userWalletBean.setKeystore("");
                userWalletBean.setWalletExistWay("a");
                userWalletBean.setIsAddUSDT("0");
                DaoUtil.addWallet(userWalletBean);
                ApolloApplication.getInstance().finishAllActivity();
                UiHelper.startHomepageActivity(this);
                break;
            case R.id.ll_copy:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                Util.copy(this,
                        "Address:\n" + eosAccount + "\n\n\n" +tvContent.getText().toString());
                ToastUtil.show(this, getString(R.string.t_copied));
                break;
        }

    }
}
