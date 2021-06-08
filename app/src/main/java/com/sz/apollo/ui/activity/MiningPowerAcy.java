package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterMinersNBO;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.NboMiningPowerBean;
import com.sz.apollo.ui.models.NboPowerInfoBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 矿池算力
 */
public class MiningPowerAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_profits)
    TextView tvProfits;
    @BindView(R.id.tv_power_own)
    TextView tvPowerOwn;
    @BindView(R.id.tv_power_push)
    TextView tvPowerPush;
    @BindView(R.id.lv_miners)
    ListView lvMiners;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.ll_miners)
    LinearLayout llMiners;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.tv_theme)
    TextView tvTheme;
    private List<NboMiningPowerBean.DataBeanX.DataBean> list, listGet;
    private AdapterMinersNBO adapterMinersNBO;
    private String tag = "";
    private static String addmore = "ADDMORE";
    private static String refresh = "REFRESH";
    private int pageNumber = 1;
    private int pageSize = 10;
    private NboMiningPowerBean nboPollBean;
    private String coinType;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            coinType = bundle.getString("coinType");
        }
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.COMPUTATIONALPOWER:
                hud.dismiss();
                nboPollBean = (NboMiningPowerBean) message.obj;
                listGet = nboPollBean.getData().getData();
                if (tag.equals(addmore)) {
                    if (listGet.size() == 0) {
                        ToastUtil.show(this, getString(R.string.tip174));
                    } else {
                        list.addAll(listGet);
                    }
                } else {
                    if (null != list && list.size() > 0) {
                        list.clear();
                    }
                    list.addAll(listGet);
                }
                refreshLayout.complete();
                adapterMinersNBO.setList(list);
                if (list.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    llMiners.setVisibility(View.GONE);
                } else {
                    llEmpty.setVisibility(View.GONE);
                    llMiners.setVisibility(View.VISIBLE);
                }
                break;
            case GlobalMessageType.RequestCode.POWERINFO:
                NboPowerInfoBean infoBean = (NboPowerInfoBean) message.obj;
                if (null != infoBean) {
                    NboPowerInfoBean.DataBean dataBean = infoBean.getData();
                    if (null != dataBean) {
                        tvProfits.setText(Util.getStringFromDecimal(dataBean.getProfitSum(), 4));
                        tvPowerOwn.setText(Util.getStringFromDecimal(dataBean.getRankPower(), 4));
                        tvPowerPush.setText(Util.getStringFromDecimal(dataBean.getExtensionPower(), 4));
                    }
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                refreshLayout.complete();
                String error = (String) message.obj;
                ToastUtil.show(this, error);
                llEmpty.setVisibility(View.VISIBLE);
                llMiners.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mining_power_nbo);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(getString(R.string.tip212));
        tvTheme.setText(getString(R.string.tip216) + "(" + coinType + ")");
        list = new ArrayList<>();
        listGet = new ArrayList<>();
        adapterMinersNBO = new AdapterMinersNBO(this);
        lvMiners.setAdapter(adapterMinersNBO);
        initLoading(this);
        adapterMinersNBO.setList(list);
        ivEmpty.setImageResource(R.drawable.ic_empty_nbo);
        tvEmpty.setTextColor(getResources().getColor(R.color.color_purple5));

        if (coinType.equals(Constant.TYPE_AOT)) {
            ivEmpty.setImageResource(R.drawable.ic_empty);
            tvEmpty.setTextColor(getResources().getColor(R.color.gray));
        }
        if (coinType.equals(Constant.TYPE_NBO)) {
            ivEmpty.setImageResource(R.drawable.ic_empty_nbo);
            tvEmpty.setTextColor(getResources().getColor(R.color.color_purple5));
        }
        if (coinType.equals(Constant.TYPE_DDAO)) {
            ivEmpty.setImageResource(R.drawable.ic_empty_ddao);
            tvEmpty.setTextColor(getResources().getColor(R.color.color_purple5));
        }
        if (coinType.equals(Constant.TYPE_LON)) {
            ivEmpty.setImageResource(R.drawable.ic_empty_lon);
            tvEmpty.setTextColor(getResources().getColor(R.color.color_purple5));
        }

        hud.show();
        iRequest.computationalPower(coinType, DaoUtil.selectNowWallet().getAddress(), pageNumber, pageSize);
        iRequest.powerInfo(coinType, DaoUtil.selectNowWallet().getAddress());
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                tag = addmore;
                pageNumber++;
                iRequest.computationalPower(coinType, DaoUtil.selectNowWallet().getAddress(), pageNumber, pageSize);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tag = refresh;
                pageNumber = 1;
                iRequest.powerInfo(coinType, DaoUtil.selectNowWallet().getAddress());
                iRequest.computationalPower(coinType, DaoUtil.selectNowWallet().getAddress(), pageNumber, pageSize);
            }
        });
    }
}
