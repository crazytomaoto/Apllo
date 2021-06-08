package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改pin码
 */
public class ChangePinAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_pin_now)
    EditText edPinNow;
    @BindView(R.id.ed_pin_new)
    EditText edPinNew;
    @BindView(R.id.ed_pin_re)
    EditText edPinRe;
    private String nowPass, newPass, rePass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.tip116);

    }

    @OnClick(R.id.btn_sure)
    public void onViewClicked() {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        nowPass = edPinNow.getText().toString().trim();
        newPass = edPinNew.getText().toString().trim();
        rePass = edPinRe.getText().toString().trim();
        if (StringUtils.isEmpty(nowPass)) {
            ToastUtil.show(this, getString(R.string.tip112));
            return;
        }
        if (StringUtils.isEmpty(newPass)) {
            ToastUtil.show(this, getString(R.string.tip113));
            return;
        }
        if (newPass.length() < 6) {
            ToastUtil.show(this, getString(R.string.tip142));
            return;
        }
        if (StringUtils.isEmpty(rePass)) {
            ToastUtil.show(this, getString(R.string.tip114));
            return;
        }
        if (!newPass.equals(rePass)) {
            ToastUtil.show(this, getString(R.string.tip120));
            return;
        }
        if (!nowPass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
            ToastUtil.show(this, getString(R.string.tip119));
            return;
        }
        PlatformConfig.setValue(Constant.SpConstant.WALLET_PASS, newPass);
        ToastUtil.show(this, getString(R.string.tip121));
        hideInput();
        finish();
    }
}
