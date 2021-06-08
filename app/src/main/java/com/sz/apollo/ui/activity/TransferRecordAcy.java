package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.TransferRecordApolloAdapter;
import com.sz.apollo.ui.adapters.TransferRecordEthAdapter;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.EthTransRecordBean;
import com.sz.apollo.ui.models.TransRecordBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 转账记录页面
 */
public class TransferRecordAcy extends BasicActivity {
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.iv_up)
    ImageView ivUp;
    @BindView(R.id.iv_list_book)
    ImageView ivListBook;
    @BindView(R.id.lv_record)
    ListView lvRecord;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    private TransferRecordApolloAdapter recordApolloAdapter;
    private List<TransRecordBean.DataBeanX.DataBean> listApollo, listApolloGet;

    private TransferRecordEthAdapter recordEthAdapter;
    private List<EthTransRecordBean.DataBean> listETH, listEthGet;
    private int position;
    private String walletType;
    private BalanceBean balanceBean;
    private UserWalletBean nowWallet;
    private int pageSize = 10, pageNumber = 1;
    private String tag = "";
    private static String addmore = "ADDMORE";
    private static String refresh = "REFRESH";
    private TransRecordBean recordBean;
    private EthTransRecordBean ethTransRecordBean;

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.ERROR:
                String error = (String) message.obj;
                ToastUtil.show(this, error);
                break;
            case GlobalMessageType.RequestCode.TRANS_LIST_APOLLO:
                hud.dismiss();
                recordBean = (TransRecordBean) message.obj;
                listApolloGet = recordBean.getData().getData();
                if (tag.equals(addmore)) {
                    if (listApolloGet.size() == 0) {
                        ToastUtil.show(this, getString(R.string.tip174));
                    } else {
                        listApollo.addAll(listApolloGet);
                    }
                } else {
                    if (null != listApollo && listApollo.size() > 0) {
                        listApollo.clear();
                    }
                    listApollo.addAll(listApolloGet);
                }
                refreshLayout.complete();
                recordApolloAdapter.setListApollo(listApollo);
                setLvInvisibleOrNot(listApollo);
                break;
            case GlobalMessageType.RequestCode.ETH_TRANS_RECORD:
                hud.dismiss();
                ethTransRecordBean = (EthTransRecordBean) message.obj;
                listEthGet = ethTransRecordBean.getData();
                if (tag.equals(addmore)) {
                    if (listEthGet.size() == 0) {
                        ToastUtil.show(this, getString(R.string.tip174));
                    } else {
                        listETH.addAll(listEthGet);
                    }
                } else {
                    if (null != listETH && listETH.size() > 0) {
                        listETH.clear();
                    }
                    listETH.addAll(listEthGet);
                }
                refreshLayout.complete();
                recordEthAdapter.setList(listETH);
                setLvInvisibleOrNot(listETH);
                break;
            case GlobalMessageType.RequestCode.GETAPOLLOBALANCE_NOHOME:
            case GlobalMessageType.RequestCode.GETNBOBALANCE:
                List<BalanceBean> listBalance = (List<BalanceBean>) message.obj;
                boolean hasBalance = false;
                balanceBean = new BalanceBean();
                for (BalanceBean bean : listBalance) {
                    if (bean.getType().equals(walletType)) {
                        balanceBean = bean;
                        hasBalance = true;
                        break;
                    }
                }
                if (hasBalance == true) {
                    tvBalance.setText(Util.getStringFromDecimal(balanceBean.getBalance(), 4));
                } else {
                    tvBalance.setText("0.0000");
                }
                hud.dismiss();
                break;
            case GlobalMessageType.RequestCode.APOLLO_ONE_KIND_BALANCE:
                JSONObject jsonObject = (JSONObject) message.obj;
                balanceBean = new BalanceBean();
                if (null != jsonObject) {
                    Map<String, String> map = Util.json2map(jsonObject.getString("data"));
                    if (map.containsKey(walletType)) {
                        balanceBean.setType(walletType);
                        balanceBean.setBalance(map.get(walletType));
                        tvBalance.setText(Util.getStringFromDecimal(balanceBean.getBalance(), 4));
                    }
                    hud.dismiss();
                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_record);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            position = bundle.getInt("position");
            balanceBean = (BalanceBean) bundle.getSerializable("balanceBean");
        }
    }

    /**
     * 过滤字段
     *
     * @param selectType 1 收入 2支出 3 全部
     */
    private void setDataForSelected(int selectType) {
        if (balanceBean.getType().equals(Constant.TYPE_AOT) || balanceBean.getType().equals(Constant.TYPE_AOC)) {
            List<TransRecordBean.DataBeanX.DataBean> list_apollo = new ArrayList<>();
            if (selectType == 1) {
                for (TransRecordBean.DataBeanX.DataBean dataBean : listApollo) {
                    if (dataBean.getType() == 1) {
                        list_apollo.add(dataBean);
                    }
                }
            }
            if (selectType == 2) {
                for (TransRecordBean.DataBeanX.DataBean dataBean : listApollo) {
                    if (dataBean.getType() == 2) {
                        list_apollo.add(dataBean);
                    }
                }
            }
            if (selectType == 3) {
                list_apollo.addAll(listApollo);
            }
            recordApolloAdapter.setListApollo(list_apollo);
            setLvInvisibleOrNot(list_apollo);
        }
    }


    private void initView() {
        nowWallet = DaoUtil.selectNowWallet();
        listApollo = new ArrayList<>();
        listApolloGet = new ArrayList<>();
        listETH = new ArrayList<>();
        listEthGet = new ArrayList<>();
        recordApolloAdapter = new TransferRecordApolloAdapter(this);
        recordEthAdapter = new TransferRecordEthAdapter(this, nowWallet.getAddress());
        lvRecord.setAdapter(recordApolloAdapter);
        tvTitle.setText(balanceBean.getType());
        walletType = balanceBean.getType();
        initLoading(this);
        if (nowWallet.getType().equals(Constant.TYPE_APOLLO)) {
            lvRecord.setAdapter(recordApolloAdapter);
            recordApolloAdapter.setListApollo(listApollo);
        }
        lvRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!nowWallet.getType().equals(Constant.TYPE_ETH)) {
                    UiHelper.startTransDetailOneAcy(TransferRecordAcy.this, listApollo.get(position).getTxId(), balanceBean.getType());
                } else {

                }
            }
        });
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                tag = addmore;
                pageNumber++;
                if (nowWallet.getType().equals(Constant.TYPE_APOLLO)) {
                    iRequest.transList(nowWallet.getAddress(), balanceBean.getType(), pageNumber, pageSize);//
                } else {
                    iRequest.getEthTransactionsByAddress2(pageNumber, pageSize, nowWallet.getAddress());
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tag = refresh;
                pageNumber = 1;
                if (nowWallet.getType().equals(Constant.TYPE_APOLLO)) {
                    iRequest.transList(nowWallet.getAddress(), balanceBean.getType(), pageNumber, pageSize);//
                } else {
                    iRequest.getEthTransactionsByAddress2(pageNumber, pageSize, nowWallet.getAddress());
                }
//                if (walletType.equals(Constant.TYPE_NBO)) {
//                    iRequest.getBalanceOfNbo(nowWallet.getAddress(),false);//cult33r3awr2
//                } else {
//                    iRequest.getTokenOfApollo(nowWallet.getAddress(), false);//cult33r3awr2
//                }
                iRequest.getBalance(nowWallet.getAddress(), walletType);
            }
        });

    }

    private void getData() {
        if (nowWallet.getType().equals(Constant.TYPE_APOLLO)) {
            hud.show();
            iRequest.transList(nowWallet.getAddress(), walletType, pageNumber, pageSize);//
//            if (walletType.equals(Constant.TYPE_NBO)) {
//                iRequest.getBalanceOfNbo(nowWallet.getAddress(),false);//cult33r3awr2
//            } else {
//                iRequest.getTokenOfApollo(nowWallet.getAddress(), false);//cult33r3awr2
//            }
            iRequest.getBalance(nowWallet.getAddress(), walletType);
        }
    }

    private void setLvInvisibleOrNot(List list) {
        if (nowWallet.getType().equals(Constant.TYPE_APOLLO)) {
            if (list.size() == 0) {
                lvRecord.setVisibility(View.GONE);
                llEmpty.setVisibility(View.VISIBLE);
            } else {
                lvRecord.setVisibility(View.VISIBLE);
                llEmpty.setVisibility(View.GONE);
            }
        }
    }

    @OnClick({R.id.iv_down, R.id.iv_up, R.id.ll_send, R.id.ll_receive, R.id.iv_list_book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_down:
                setDataForSelected(1);
                break;
            case R.id.iv_up:
                setDataForSelected(2);
                break;
            case R.id.iv_list_book:
                setDataForSelected(3);
                break;
            case R.id.ll_send:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (nowWallet.getType().equals(Constant.TYPE_APOLLO)) {
                    UiHelper.translateApolloAcy(this, false, "", walletType);
                }
                if (nowWallet.getType().equals(Constant.TYPE_ETH)) {
                    UiHelper.startTranslateEthAcy(this, balanceBean.getType());
                }
                break;
            case R.id.ll_receive:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                DialogUtil.dialogReceive(this, balanceBean.getType(), nowWallet.getAddress());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
