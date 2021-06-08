package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.flyco.roundview.RoundTextView;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建钱包成功
 */
public class CreateEthSuccessAcy extends BasicActivity {
    @BindView(R.id.tv_finish)
    RoundTextView tvFinish;
    private UserWalletBean userWalletBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_eth_success);
        ButterKnife.bind(this);

    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            userWalletBean = (UserWalletBean) bundle.getSerializable("userWalletBean");
        }
    }

    @OnClick(R.id.tv_finish)
    public void onViewClicked() {
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
        userWalletBean.setIsBackedUp("1");
        DaoUtil.addWallet(userWalletBean);
        ApolloApplication.getInstance().finishAllActivity();
        UiHelper.startHomepageActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

}
