package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 重置钱包
 */
public class ResetWalletAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.btn_sure)
    RoundTextView btnSure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_wallet);
        ButterKnife.bind(this);
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {
        tvTitle.setText(R.string.tip110);
        btnSure.getDelegate().setBackgroundColor(getColor(R.color.gray6));
        btnSure.setTextColor(getColor(R.color.gray4));
        btnSure.setEnabled(false);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    btnSure.getDelegate().setBackgroundColor(getColor(R.color.gray6));
                    btnSure.setTextColor(getColor(R.color.gray4));
                    btnSure.setEnabled(false);
                } else {
                    btnSure.getDelegate().setBackgroundColor(getColor(R.color.color_copy));
                    btnSure.setTextColor(getColor(R.color.white));
                    btnSure.setEnabled(true);
                }
            }
        });
    }

    @OnClick(R.id.btn_sure)
    public void onViewClicked() {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        PlatformConfig.setValue(Constant.SpConstant.WALLET_PASS, "");
        DaoUtil.deleteAll();
        ApolloApplication.getInstance().finishAllActivity();
        UiHelper.startSetPinCodeAcy(this);
    }
}
