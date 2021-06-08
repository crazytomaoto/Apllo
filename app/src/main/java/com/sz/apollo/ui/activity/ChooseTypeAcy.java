package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.room.Dao;

import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择钱包类型
 */
public class ChooseTypeAcy extends BasicActivity {
    @BindView(R.id.ll_apl)
    LinearLayout llApl;
    @BindView(R.id.ll_eth)
    LinearLayout llEth;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private List<UserWalletBean> list;

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);
        ButterKnife.bind(this);

        list = DaoUtil.selectAllWallet();
        if (null == list || list.size() == 0) {
            ivBack.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.ll_apl, R.id.ll_eth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_apl:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
//                List<UserWalletBean> listApollo = DaoUtil.selectAllWalletByType(Constant.TYPE_APOLLO);
//                if (null != listApollo && listApollo.size() == 5) {
//                    ToastUtil.show(this, getString(R.string.tip171));
//                    return;
//                }
                UiHelper.startCreateApolloWalletAcy(this);
                break;
            case R.id.ll_eth:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
//                List<UserWalletBean> listEth = DaoUtil.selectAllWalletByType(Constant.TYPE_ETH);
//                if (null != listEth && listEth.size() == 5) {
//                    ToastUtil.show(this, getString(R.string.tip171));
//                    return;
//                }
                UiHelper.startCreateEthWalletOneAcy(this);
                break;
        }
    }

    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (null == list || list.size() == 0) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    ToastUtil.show(this, getString(R.string.tips_exit));
                    exitTime = System.currentTimeMillis();
                } else {
                    ApolloApplication.getInstance().exit();
                }
                return true;
            }
        } else {
            finish();
            return false;
        }
        return false;
    }
}
