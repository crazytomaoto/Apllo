package com.sz.apollo.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.charts.LineChartA;
import com.github.mikephil.charting.data.Entry;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.NboPollBean;
import com.sz.apollo.ui.models.NboStateBean;
import com.sz.apollo.ui.models.NoticeBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.LineChartManager;
import com.sz.apollo.ui.utils.TimeUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.views.AutoVerticalScrollTextView;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * DDAO矿池
 */
public class MiningPoolLonAcy extends BasicActivity {
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_profits)
    TextView tvProfits;
    @BindView(R.id.chart)
    LineChartA chart;
    @BindView(R.id.tv_best)
    TextView tvBest;
    @BindView(R.id.tv_least)
    TextView tvLeast;
    @BindView(R.id.ll_chart)
    LinearLayout llChart;
    @BindView(R.id.ll_active_miner)
    LinearLayout llActiveMiner;
    protected String[] XTetValues;
    @BindView(R.id.ll_flash_exchange)
    LinearLayout llFlashExchange;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.tv_bulletin)
    AutoVerticalScrollTextView tvBulletin;
    @BindView(R.id.iv_invite)
    GifImageView ivInvite;
    @BindView(R.id.tv_what)
    TextView tvWhat;
    @BindView(R.id.tv_theme)
    TextView tvTheme;
    private ArrayList<Entry> entriesXYValuse;
    private LineChartManager lineChartManager1;
    private int state;//(0未激活,1已激活未购买矿机,2已开启挖矿)
    private BalanceBean balanceBean;
    private NoticeBean noticeBean;
    private List<NoticeBean.DataBeanX.DataBean> listNotice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_ming_lon);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        switch (message.what) {
            case GlobalMessageType.RequestCode.ERROR:
                String error = (String) message.obj;
                if (!StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                hud.dismiss();
                refreshLayout.complete();
                break;
            case GlobalMessageType.RequestCode.QUERY_NOTICE:
                noticeBean = (NoticeBean) message.obj;
                if (null != noticeBean) {
                    if (listNotice.size() > 0) {
                        listNotice.clear();
                    }
                    listNotice = noticeBean.getData().getData();
                    if (listNotice.size() > 0) {
                        llNotice.setVisibility(View.VISIBLE);
                        tvBulletin.setData(listNotice);
                        if (tvBulletin != null) {
                            tvBulletin.start();
                        }
                    } else {
                        llNotice.setVisibility(View.GONE);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.COINSTATE:
                NboStateBean nboStateBean = (NboStateBean) message.obj;
                if (null != nboStateBean) {
                    state = nboStateBean.getData().getState();
                    if (state == 2) {
                        llActiveMiner.setVisibility(View.GONE);
                    } else {
                        llActiveMiner.setVisibility(View.VISIBLE);
                    }
                }
                iRequest.orePool(Constant.TYPE_LON, DaoUtil.selectNowWallet().getAddress());
                break;
            case GlobalMessageType.RequestCode.APOLLO_ONE_KIND_BALANCE:
                JSONObject jsonObject = (JSONObject) message.obj;
                balanceBean = new BalanceBean();
                if (null != jsonObject) {
                    Map<String, String> map = Util.json2map(jsonObject.getString("data"));
                    /**
                     * 当前币
                     */
                    if (map.containsKey(Constant.TYPE_AOC)) {
                        balanceBean.setType(Constant.TYPE_AOC);
                        balanceBean.setBalance(map.get(Constant.TYPE_AOC));
                    }
                    hud.dismiss();
                    refreshLayout.complete();
                }
                break;
            case GlobalMessageType.RequestCode.OREPOOL:
                NboPollBean nboPollBean = (NboPollBean) message.obj;
                if (null != nboPollBean) {
                    NboPollBean.DataBean dataBean = nboPollBean.getData();
                    if (null != dataBean) {
                        tvBest.setText(Util.getStringFromDecimal(dataBean.getOptimumNmber(), 2));
                        tvLeast.setText(Util.getStringFromDecimal(dataBean.getHoldingNumber(), 2));
                        tvProfits.setText(Util.getStringFromDecimal(dataBean.getProfitSum(), 4));
                        List<NboPollBean.DataBean.ProfitlogListBean> listBeans = dataBean.getProfitlogList();
                        if (null != listBeans && listBeans.size() > 0) {
                            Collections.reverse(listBeans);
                            List<NboPollBean.DataBean.ProfitlogListBean> newList = new ArrayList<>();
                            if (listBeans.size() > 7) {
                                for (int index = 0; index < 7; index++) {
                                    newList.add(listBeans.get(index));
                                }
                                Collections.reverse(newList);
                            }
                            if (newList.size() > 1) {
                                try {
                                    double YValueF;
                                    entriesXYValuse = new ArrayList<Entry>();
                                    XTetValues = new String[newList.size() + 1];
                                    for (int i = 0; i < newList.size(); i++) {
                                        YValueF = newList.get(i).getProfitSum();
                                        entriesXYValuse.add(new Entry(i, (float) YValueF));
                                        XTetValues[i] = TimeUtil.getTime3(newList.get(i).getCreatedAt());
                                    }
                                    //展示图表
                                    lineChartManager1 = new LineChartManager(chart, this, getResources().getColor(R.color.page_color3));
                                    lineChartManager1.showLineChart(this, newList, "", getResources().getColor(R.color.color_blue));
                                    //设置曲线填充色 以及 MarkerView
                                    Drawable drawable = getResources().getDrawable(R.drawable.fade_blue_line);
                                    lineChartManager1.setChartFillDrawable(drawable);
                                    lineChartManager1.setMarkerView(this);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                chart.setVisibility(View.GONE);
                            }
                        } else {
                            chart.setVisibility(View.GONE);
                        }
                    } else {
                        chart.setVisibility(View.GONE);
                    }
                }
                if (state != 2) {
                    iRequest.getBalance(DaoUtil.selectNowWallet().getAddress(), Constant.TYPE_AOC);
                } else {
                    hud.dismiss();
                    refreshLayout.complete();
                }
                break;
            case GlobalMessageType.RequestCode.ENABLENBOMINE:
                JSONObject jsonObjectEnable = (JSONObject) message.obj;
                hud.dismiss();
                if (null != jsonObjectEnable) {
                    if (jsonObjectEnable.getBoolean("success")) {
                        ToastUtil.show(this, jsonObjectEnable.getString("message"));
                        llActiveMiner.setVisibility(View.GONE);
                        llFlashExchange.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }


    private void initView() {
        tvTitle.setText(R.string.tip243);
        listNotice = new ArrayList<>();
        tvTheme.setText(getString(R.string.tip211) + "(" + Constant.TYPE_LON + ")");
        final GifDrawable gifDrawable = (GifDrawable) ivInvite.getDrawable();
        gifDrawable.start();
        initLoading(this);
        hud.show();
        iRequest.getPollAddressState(Constant.TYPE_LON, DaoUtil.selectNowWallet().getAddress());
        iRequest.queryNotice(1, 10, "", 4);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            iRequest.getPollAddressState(Constant.TYPE_LON, DaoUtil.selectNowWallet().getAddress());
            iRequest.queryNotice(1, 10, "", 4);
        });
        refreshLayout.setEnableLoadmore(false);
        tvBulletin.setTextViewOnClick((view, position) -> {
            UiHelper.startWebAcyAcy(this, listNotice.get(position).getTitle(), listNotice.get(position).getUrl());
        });
        tvWhat.setText(getString(R.string.tip213) + Constant.TYPE_LON);
    }

    @OnClick({R.id.iv_detail, R.id.rl_all_notice, R.id.ll_active_miner, R.id.ll_miner_power, R.id.ll_introduce, R.id.iv_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_detail:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startProfitDetailAcy(this, Constant.TYPE_LON);
                break;
            case R.id.rl_all_notice:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startNoticeAllAcy(this, 4);
                break;
            case R.id.ll_active_miner:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (state == 0) {
                    DialogUtil.dialogTips(this, getString(R.string.tip223), getString(R.string.tip224), new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {

                        }

                        @Override
                        public void cancle() {

                        }
                    });
                    return;
                }
                if (state == 1) {
                    if (Double.parseDouble(balanceBean.getBalance()) < 8) {
                        ToastUtil.show(this, getString(R.string.tip244));
                        return;
                    } else {
                        DialogUtil.dialogActiveNbo(this, Constant.TYPE_LON, new IPassGetInput() {
                            @Override
                            public void getTextString(String pass) {
                                if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                    hideInput();
                                    activeNbo();
                                } else {
                                    ToastUtil.show(MiningPoolLonAcy.this, getString(R.string.error_pwd));
                                }
                            }

                            @Override
                            public void cancle() {

                            }
                        });
                    }

                } else {

                }
                break;
            case R.id.ll_miner_power:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startMiningPowerAcy(this, Constant.TYPE_LON);
                break;
            case R.id.ll_introduce:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startWebAcyAcy(this, getString(R.string.tip213) + Constant.TYPE_LON, RequestHost.WebUrls.introduceLon);
                break;
            case R.id.iv_invite:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startShareNboAcy(this, Constant.TYPE_LON);
                break;
        }
    }

    private void activeNbo() {
        hud.show();
        iRequest.enableNboMine(Constant.TYPE_LON, DaoUtil.selectNowWallet().getPk(), Util.getDeviceOnlyNum(this), DaoUtil.selectNowWallet().getAddress());
    }
}
