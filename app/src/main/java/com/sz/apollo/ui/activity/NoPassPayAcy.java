package com.sz.apollo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 免密支付
 */
public class NoPassPayAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_pin)
    EditText edPin;
    @BindView(R.id.btn_open)
    TextView btnOpen;
    @BindView(R.id.btn_close)
    TextView btnClose;
    @BindView(R.id.tipOne)
    TextView tipOne;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_pass_pay);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.tip111);
        boolean isOpenFingerPay = PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY);
        setButton(isOpenFingerPay);
    }

    private void setButton(boolean isOpenFingerPay) {
        if (isOpenFingerPay) {
            btnOpen.setVisibility(View.GONE);
            btnClose.setVisibility(View.VISIBLE);
            tipOne.setVisibility(View.GONE);
            edPin.setText(R.string.tip155);
            edPin.setEnabled(false);
        } else {
            btnOpen.setVisibility(View.VISIBLE);
            btnClose.setVisibility(View.GONE);
            tipOne.setVisibility(View.VISIBLE);
            edPin.setText("");
            edPin.setEnabled(true);
        }
    }

    private FingerprintCallback fingerprintCallback = new FingerprintCallback() {
        @Override
        public void onSucceeded() {
            ToastUtil.show(NoPassPayAcy.this, getString(R.string.biometricprompt_verify_success));
            PlatformConfig.setValue(Constant.SpConstant.FINGER_PAY, true);
            setButton(true);
        }

        @Override
        public void onFailed() {
            ToastUtil.show(NoPassPayAcy.this, getString(R.string.biometricprompt_verify_failed));
        }

        @Override
        public void onUsepwd() {
            ToastUtil.show(NoPassPayAcy.this, getString(R.string.fingerprint_usepwd));
        }

        @Override
        public void onCancel() {
            ToastUtil.show(NoPassPayAcy.this, getString(R.string.fingerprint_cancel));
        }

        @Override
        public void onHwUnavailable() {
            ToastUtil.show(NoPassPayAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
        }

        @Override
        public void onNoneEnrolled() {
            //弹出提示框，跳转指纹添加页面
            AlertDialog.Builder builder = new AlertDialog.Builder(NoPassPayAcy.this);
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

    @OnClick({R.id.btn_open, R.id.btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_open:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                String pin = edPin.getText().toString().trim();
                if (StringUtils.isEmpty(pin)) {
                    ToastUtil.show(this, getString(R.string.tip112));
                    return;
                }
                if (!pin.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                    ToastUtil.show(this, getString(R.string.tip119));
                    return;
                }
                FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(NoPassPayAcy.this);
                builder.callback(fingerprintCallback)
                        .fingerprintColor(ContextCompat.getColor(NoPassPayAcy.this, R.color.colorPrimary))
                        .build();
                break;

            case R.id.btn_close:
                PlatformConfig.setValue(Constant.SpConstant.FINGER_PAY, false);
                setButton(false);
                break;
        }


    }

}
