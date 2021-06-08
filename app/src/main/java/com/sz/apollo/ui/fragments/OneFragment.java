package com.sz.apollo.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.hysd.android.platform_ub.utils.ActivityUtil;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.HomeAdapter;
import com.sz.apollo.ui.basic.BasicFragment;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.PriceBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.models.VersionBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.views.AutoListView;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OneFragment extends BasicFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_three_point)
    ImageView ivThreePoint;
    @BindView(R.id.iv_wallet_logo)
    ImageView ivWalletLogo;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.lv_wallets)
    AutoListView lvWallets;
    @BindView(R.id.ll_zH)
    LinearLayout ll_zH;
    List<BalanceBean> list;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    private UserWalletBean theWallet;
    private HomeAdapter adapter;
    private double priceAOT, priceAOC, priceNBO, priceDDAO, priceLON;
    private String address;

    @Override
    protected void handleStateMessage(Message message) {
        switch (message.what) {
            case GlobalMessageType.RequestCode.GETETHBALANCE:
                BalanceBean balanceBean = (BalanceBean) message.obj;
                if (null != balanceBean) {
                    list.clear();
                    list.add(balanceBean);
                    if (!theWallet.getIsAddUSDT().equals("1")) {
                        if (null != adapter) {
                            lvWallets.requestLayout();
                            adapter.notifyDataSetChanged();
                            hud.dismiss();
                        }
                        refreshLayout.complete();
                    } else {
                        iRequest.getTokenOfUsdt(theWallet.getAddress(), Constant.TYPE_U);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.GETUSDTBALANCE:
                BalanceBean balanceBeanUSDT = (BalanceBean) message.obj;
                if (null != balanceBeanUSDT) {
                    list.add(balanceBeanUSDT);
                    if (null != adapter) {
                        lvWallets.requestLayout();
                        adapter.notifyDataSetChanged();
                        hud.dismiss();
                    }
                    refreshLayout.complete();
                }
                break;
            case GlobalMessageType.RequestCode.GETAPOLLOBALANCE:
                list = (List<BalanceBean>) message.obj;
                iRequest.getBalanceOfNbo(address, true);
                break;
            case GlobalMessageType.RequestCode.GETNBOBALANCE_HOME:
                List<BalanceBean> listNbo = (List<BalanceBean>) message.obj;
                if (null != listNbo && listNbo.size() > 0) {
                    list.addAll(listNbo);
                    adapter.setList(list);
                }
                iRequest.getPrice("AOT,AOC,NBO");
                break;
            case GlobalMessageType.RequestCode.SYMBOL_PRICE:
                PriceBean priceBean = (PriceBean) message.obj;
                if (null != priceBean) {
                    List<PriceBean.DataBeanX.DataBean> priceBeanList = priceBean.getData().getData();
                    List<BalanceBean> myList = new ArrayList<>();
                    if (null != priceBeanList && priceBeanList.size() > 0) {
                        for (PriceBean.DataBeanX.DataBean dataBean : priceBeanList) {
                            for (BalanceBean myBalanceBean : list) {
                                if (dataBean.getSymbol().equals(myBalanceBean.getType())) {
                                    myBalanceBean.setBalanceToU(dataBean.getPrice() * Double.parseDouble(myBalanceBean.getBalance()));
                                    myList.add(myBalanceBean);
                                }
                            }
                        }
                    }
                    list.clear();
                    list.addAll(myList);
                    setTvBalance(list);
                }
                break;
            case GlobalMessageType.RequestCode.LATEST_VERSION:
                VersionBean versionBean = (VersionBean) message.obj;
                if (null != versionBean) {
                    VersionBean.DataBean dataBean = versionBean.getData();
                    if (null != dataBean) {
                        compareVersion(dataBean);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.APOLLO_BALANCE:
                JSONObject jsonObject = (JSONObject) message.obj;
                if (null != jsonObject) {
                    Map<String, String> map = Util.json2map(jsonObject.getString("data"));
//                    List<BalanceBean> list = new ArrayList<>();
                    for (String key : map.keySet()) {
                        BalanceBean bean = new BalanceBean();
                        bean.setType(key);
                        bean.setBalance(map.get(key));
                        list.add(bean);
                    }
                }
                iRequest.getPrice(Constant.ALL_APOLLO_KINDS);
                break;
        }
    }

    private void compareVersion(VersionBean.DataBean dataBean) {
        int versionCode = ActivityUtil.getVersionCode(getActivity());
        try {
            if (dataBean.getAppVersionCode() > versionCode) {
                if (!StringUtils.isEmpty(dataBean.getAppPath())) {
                    DialogUtil.showVersionCheckDialog(getActivity(), dataBean.getAppVersionName(), dataBean.getAppDesc(), (v, d) -> {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        Uri targetUrl = Uri.parse(dataBean.getAppPath());
                        intent.setData(targetUrl);
                        startActivity(intent);
                    });
                } else {
                    ToastUtil.show(getActivity(), getString(R.string.tip189));
                }
            }
        } catch (Exception e) {
            ToastUtil.show(getActivity(), getString(R.string.tip189));
        }
    }

    private void setTvBalance(List<BalanceBean> myList) {
        hud.dismiss();
        refreshLayout.complete();
        if (theWallet.getType().equals(Constant.TYPE_APOLLO)) {
            double allValue = 0;
            for (BalanceBean balanceBean : list) {
                allValue += balanceBean.getBalanceToU();
            }
            tvBalance.setText(Util.getStringFromDecimal(allValue, 2));
            lvWallets.requestLayout();
            adapter.setList(myList);
        } else {

        }

    }


    @Override
    protected void lazyLoad() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_frg_one, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
//        hud = KProgressHUD.create(getActivity())
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
////                .setBackgroundColor(getResources().getColor(R.color.loading_color))
//                .setAnimationSpeed(2)
//                .setSize(60, 60);
//        hud.setCancellable(false);
        list = new ArrayList<>();

        theWallet = DaoUtil.selectNowWallet();
        address = theWallet.getAddress();

        adapter = new HomeAdapter(getActivity());
        lvWallets.setAdapter(adapter);
        lvWallets.setOnItemClickListener((parent, view, position, id) -> {
            if (ClickUtil.isNotFirstClick()) {
                return;
            }
            try {
                UiHelper.startTransferRecordAcy(getActivity(), position, list.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        adapter.setList(list);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                theWallet = DaoUtil.selectNowWallet();
                if (list.size() > 0) {
                    list.clear();
                }
                if (theWallet.getType().equals(Constant.TYPE_APOLLO)) {
//                    iRequest.getTokenOfApollo(theWallet.getAddress(), true);//cult33r3awr2
                    iRequest.getBalance(theWallet.getAddress(), Constant.ALL_APOLLO_KINDS);
                } else {
                    iRequest.queryETHBalance(theWallet.getAddress(), Constant.TYPE_ETH);
                }
            }
        });
        adapter.setNoType(theWallet.getType());
        iRequest.latestVersion();
    }

    @OnClick({R.id.rl_left_top, R.id.iv_three_point, R.id.iv_add, R.id.ll_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left_top:
                DialogUtil.dialogChooseIdentity(getActivity(), result -> {
                    if (!StringUtils.isEmpty((String) result)) {
                        theWallet = DaoUtil.selectNowWallet();
                        if (!address.equals(theWallet.getAddress())) {
                            address = theWallet.getAddress();
                            hud.show();
                            setDataForView();
                        } else {

                        }
                    }
                });
                break;
            case R.id.iv_three_point:
                UiHelper.startManageWalletAcy(getActivity());
                break;
            case R.id.ll_address:
                Util.copy(getActivity(), theWallet.getAddress());
                ToastUtil.show(getActivity(), getString(R.string.t_copied));
                break;
            case R.id.iv_add:
                UiHelper.startMoreTokenAcy(getActivity(), theWallet.getType());
                break;
        }
    }

    private void setDataForView() {
        tvTitle.setText(theWallet.getRemark());
        adapter.setNoType(theWallet.getType());
        if (theWallet.getType().equals(Constant.TYPE_APOLLO)) {
            ivWalletLogo.setImageDrawable(getActivity().getDrawable(R.drawable.ic_logo_a));
            ll_zH.setVisibility(View.VISIBLE);
            tvAddress.setText(theWallet.getAddress());
//            iRequest.getTokenOfApollo(theWallet.getAddress(), true);//cult33r3awr2
            if (list.size() > 0) {
                list.clear();
            }
            iRequest.getBalance(theWallet.getAddress(), Constant.ALL_APOLLO_KINDS);
        } else {
            hud.show();
            iRequest.addAccountEth(theWallet.getPk(), Util.getDeviceOnlyNum(getActivity()), theWallet.getAddress());
            ivWalletLogo.setImageDrawable(getActivity().getDrawable(R.drawable.ic_eth_3));
            ll_zH.setVisibility(View.INVISIBLE);
            tvAddress.setText(StringUtils.hideMiddleString(theWallet.getAddress(), 25, 10));
            iRequest.queryETHBalance(theWallet.getAddress(), Constant.TYPE_ETH);
//            iRequest.getPrice("ETH,USDT");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        theWallet = DaoUtil.selectNowWallet();
        address = theWallet.getAddress();
        tvTitle.setText(theWallet.getRemark());
        if (list.size() > 0) {
            list.clear();
            adapter.notifyDataSetChanged();
        }
        if (theWallet.getType().equals(Constant.TYPE_ETH)) {
            ivWalletLogo.setImageDrawable(getActivity().getDrawable(R.drawable.ic_eth_3));
            ll_zH.setVisibility(View.INVISIBLE);
            tvAddress.setText(StringUtils.hideMiddleString(theWallet.getAddress(), 25, 10));
            hud.show();
            iRequest.queryETHBalance(address, Constant.TYPE_ETH);
        } else {
            ivWalletLogo.setImageDrawable(getActivity().getDrawable(R.drawable.ic_logo_a));
            ll_zH.setVisibility(View.VISIBLE);
            tvAddress.setText(theWallet.getAddress());
//            iRequest.getTokenOfApollo(theWallet.getAddress(), true);//cult33r3awr2
            iRequest.getBalance(theWallet.getAddress(), Constant.ALL_APOLLO_KINDS);
        }
    }
}
