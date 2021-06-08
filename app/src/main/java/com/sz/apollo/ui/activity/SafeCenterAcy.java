package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 安全设置
 */
public class SafeCenterAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fly_pin)
    FrameLayout flyPin;
    @BindView(R.id.fly_reset)
    FrameLayout flyReset;
    @BindView(R.id.fly_no_pass_pay)
    FrameLayout flyNoPassPay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.tip90);
    }

    @OnClick({R.id.fly_pin, R.id.fly_reset, R.id.fly_no_pass_pay})
    public void onViewClicked(View view) {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.fly_pin:
                UiHelper.startChangePinAcy(this);
                break;
            case R.id.fly_reset:
                UiHelper.startResetWalletAcy(this);
                break;
            case R.id.fly_no_pass_pay:
                UiHelper.startNoPassPayAcy(this);
                break;
        }
    }
}
