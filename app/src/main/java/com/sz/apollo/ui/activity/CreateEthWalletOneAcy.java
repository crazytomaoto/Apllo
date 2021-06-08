package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.sz.apollo.R;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建以太坊钱包---选择添加方式
 */
public class CreateEthWalletOneAcy extends BasicActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_eth_one);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarColor(R.color.page_color2)//这里的颜色，你可以自定义。
                .init();
    }

    @OnClick({R.id.tv_create, R.id.tv_import})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_create:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startBackupEthAcy(this, false, 1);
                break;
            case R.id.tv_import:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                DialogUtil.dialogChooseWayToImportEth(this);
                break;
        }
    }
}
