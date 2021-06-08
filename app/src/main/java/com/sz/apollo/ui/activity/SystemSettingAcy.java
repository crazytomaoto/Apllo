package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 语言设置
 */
public class SystemSettingAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_lg)
    TextView tv_lg;
    @BindView(R.id.ll_lg_setting)
    LinearLayout llSetting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.tip94);
    }

    @OnClick({R.id.ll_lg_setting, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_lg_setting:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startLanguageAcy(this);
                break;
            case R.id.btn_exit:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                ApolloApplication.getInstance().exit();
                break;
        }

    }
}
