package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterMiningPool;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.AddressStateBean;
import com.sz.apollo.ui.models.NoticeBean;
import com.sz.apollo.ui.models.QueryPowerBean;
import com.sz.apollo.ui.models.ResonanceBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.views.AutoListView;
import com.sz.apollo.ui.views.AutoVerticalScrollTextView;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 阿波罗矿池
 */
public class MiningPoolApolloAcy extends BasicActivity {
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_block_height)
    TextView tvBlockHeight;
    @BindView(R.id.tv_02)
    TextView tv02;
    @BindView(R.id.tv_count_today)
    TextView tvCountToday;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_account_state)
    TextView tvAccountState;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.tv_bulletin)
    AutoVerticalScrollTextView tvBulletin;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.tv_all_power)
    TextView tvAllPower;
    @BindView(R.id.tv_my_power_percentage)
    TextView tvMyPowerPercentage;
    @BindView(R.id.tv_v)
    TextView tvV;
    @BindView(R.id.tv_s)
    TextView tvS;
    @BindView(R.id.tv_my_power)
    TextView tvMyPower;
    @BindView(R.id.tv_base_power)
    TextView tvBasePower;
    @BindView(R.id.tv_time_power)
    TextView tvTimePower;
    @BindView(R.id.tv_share_power)
    TextView tvSharePower;
    @BindView(R.id.tv_resonance_cell)
    TextView tvResonanceCell;
    @BindView(R.id.tv_resonance_cell_left)
    TextView tvResonanceCellLeft;
    @BindView(R.id.lv_pool)
    AutoListView lvPool;
    @BindView(R.id.btn_mine_now)
    Button btnMineNow;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lottie_yeMiAn)
    LottieAnimationView lottieYeMiAn;
    @BindView(R.id.lottie_laBa)
    LottieAnimationView lottieLaBa;
    @BindView(R.id.lottie_mining)
    LottieAnimationView lottieMining;
    @BindView(R.id.lottie_jiLu)
    LottieAnimationView lottieJiLu;
    private AdapterMiningPool adapterMiningPool;
    private List<ResonanceBean.DataBeanX.DataBean> list;
    private QueryPowerBean queryPowerBean;
    private QueryPowerBean.DataBean dataBean;
    private NoticeBean noticeBean;
    private List<NoticeBean.DataBeanX.DataBean> listNotice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_ming_apollo);
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
            case GlobalMessageType.RequestCode.ADDRESS_STATE:
                AddressStateBean addressStateBean = (AddressStateBean) message.obj;
                if (null != addressStateBean) {
                    boolean isActive = addressStateBean.getData().isEnable();
                    if (isActive) {
                        tvAccountState.setVisibility(View.GONE);
                    } else {
                        tvAccountState.setVisibility(View.VISIBLE);
                        tvAccountState.setText(getString(R.string.tip41));
                    }
                }
                break;
            case GlobalMessageType.RequestCode.QUERY_POWER:
                queryPowerBean = (QueryPowerBean) message.obj;
                if (null != queryPowerBean) {
                    dataBean = queryPowerBean.getData();
                    if (null != dataBean) {
                        setDataForView(dataBean);
                    }
                }
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
//                refreshLayout.complete();
                break;
            case GlobalMessageType.RequestCode.GET_AOT_MODEL_LIST:
                ResonanceBean resonanceBean = (ResonanceBean) message.obj;
                if (null != resonanceBean) {
                    tvResonanceCellLeft.setText(Util.getStringFromDecimal(resonanceBean.getData().getTotalSurplus(), 2) + " AOT");
                    list = resonanceBean.getData().getData();
                    if (list.size() > 0) {
                        adapterMiningPool.setList(list);
                        lvPool.setVisibility(View.VISIBLE);
                        tvResonanceCell.setText("MP" + resonanceBean.getData().getData().get(0).getLevel());
                    } else {
                        lvPool.setVisibility(View.GONE);
                    }
                }
                hud.dismiss();
                refreshLayout.complete();
                break;
        }
    }

    @SuppressLint("StringFormatInvalid")
    private void setDataForView(QueryPowerBean.DataBean dataBean) {

        tvBlockHeight.setText(dataBean.getBlockHeight() + "");
        tvCountToday.setText(dataBean.getBlockRewardDay() + "");
        tvAllPower.setText(Util.getStringFromDecimal(dataBean.getAllTotalPower(), 2));
        tvMyPowerPercentage.setText(Util.getStringFromDecimal(dataBean.getPowerRate() * 100, 6) + "%");
        tvMyPower.setText(String.format(getString(R.string.t_my_power), Util.getStringFromDecimal(dataBean.getTotalPower(), 2)));
        tvV.setText("V" + dataBean.getLevel());
        tvS.setText("S" + dataBean.getLeaderLevel());
        tvBasePower.setText(Util.getStringFromDecimal(dataBean.getCommonPower(), 2));
        tvTimePower.setText(Util.getStringFromDecimal(dataBean.getTimePower(), 2));
        tvSharePower.setText(Util.getStringFromDecimal(dataBean.getSharePower() + dataBean.getLeaderPower(), 2));
        tvBalance.setText(Util.getStringFromDecimal(dataBean.getTotalProfit() + dataBean.getLeaderPower(), 2));
    }

    private void initView() {
        adapterMiningPool = new AdapterMiningPool(this);
        list = new ArrayList<>();
        listNotice = new ArrayList<>();
        tvTitle.setText(getString(R.string.mining_pool));
        lvPool.setAdapter(adapterMiningPool);
        adapterMiningPool.setList(list);
        refreshLayout.setEnableLoadmore(false);
        tvAccount.setText(DaoUtil.selectNowWallet().getAddress());
        initLoading(this);

        hud.show();
        iRequest.queryPower(DaoUtil.selectNowWallet().getAddress());
        iRequest.queryNotice(1, 10, "", 1);
        iRequest.reqBodyAotModelList(DaoUtil.selectNowWallet().getAddress());
        iRequest.addressState(DaoUtil.selectNowWallet().getAddress());


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iRequest.queryPower(DaoUtil.selectNowWallet().getAddress());
                iRequest.queryNotice(1, 10, "", 1);
                iRequest.reqBodyAotModelList(DaoUtil.selectNowWallet().getAddress());
                iRequest.addressState(DaoUtil.selectNowWallet().getAddress());
            }
        });
        tvBulletin.setTextViewOnClick((view, position) -> {
            UiHelper.startWebAcyAcy(this, listNotice.get(position).getTitle(), listNotice.get(position).getUrl());
        });
    }

    @OnClick({R.id.rl_address_book, R.id.rl_contact, R.id.rl_all_bulletinf, R.id.rl_power_detail, R.id.btn_mine_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_contact:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startShareSystemAcy(this);
                break;
            case R.id.rl_address_book:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                lottieYeMiAn.playAnimation();
                UiHelper.startProfitDetailAcy(this, Constant.TYPE_AOT);
                break;
            case R.id.rl_power_detail:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                lottieJiLu.playAnimation();
                UiHelper.startPowerDetailAcy(this);
                break;
            case R.id.rl_all_bulletinf:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startNoticeAllAcy(this,1);
                break;
            case R.id.btn_mine_now:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startFlashExchangeAcy(this);
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (tvBulletin != null) {
            tvBulletin.stop();
        }
    }
}
