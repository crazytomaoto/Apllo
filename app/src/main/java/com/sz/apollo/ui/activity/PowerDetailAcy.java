package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterBasisPowerDetail;
import com.sz.apollo.ui.adapters.AdapterDatePowerDetail;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.AddressSharePowerBean;
import com.sz.apollo.ui.models.BasePowerBean;
import com.sz.apollo.ui.models.NoticeBean;
import com.sz.apollo.ui.models.TimePowerBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.TimeUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 算力详情
 */
public class PowerDetailAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_basis_power)
    TextView tvBasisPower;
    @BindView(R.id.tv_date_power)
    TextView tvDatePower;
    @BindView(R.id.tv_share_power)
    TextView tvSharePower;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.tv_my_time)
    TextView tvMyTime;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.lv_one)
    ListView lvOne;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.tv_word_date)
    TextView tvWordDate;
    @BindView(R.id.lv_two)
    ListView lvTwo;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.tv_amount_share)
    TextView tvAmountShare;
    @BindView(R.id.tv_amount_share_effective)
    TextView tvAmountShareEffective;
    @BindView(R.id.tv_num_sys)
    TextView tvNumSys;
    @BindView(R.id.tv_account_fs)
    TextView tvAccountFs;
    @BindView(R.id.tv_pool_basis_power)
    TextView tvPoolBasisPower;
    @BindView(R.id.ll_three)
    LinearLayout llThree;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    private AdapterBasisPowerDetail adapterBasisPowerDetail;
    private AdapterDatePowerDetail adapterDatePowerDetail;
    private List<BasePowerBean.DataBeanX.DataBean> listOne, listOneGet;
    private List<TimePowerBean.DataBean.TimeRulesBean> listTwo;
    private int mposition = 1, pageNumber = 1, pageSize = 10;
    private UserWalletBean userWalletBean;
    private String tag = "";
    private static String addmore = "ADDMORE";
    private static String refresh = "REFRESH";

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_detail);
        ButterKnife.bind(this);
        initView();
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                refreshLayout.complete();
                String error = (String) message.obj;
                if (!StringUtils.isEmpty(error)) {
                    if (pageNumber == 1) {
                        ToastUtil.show(this, error);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.BASE_POWER_LIST:
                BasePowerBean basePowerBean = (BasePowerBean) message.obj;
                listOneGet = basePowerBean.getData().getData();
                if (tag.equals(addmore)) {
                    if (listOneGet.size() == 0) {
                        ToastUtil.show(this, getString(R.string.tip174));
                    } else {
                        listOne.addAll(listOneGet);
                    }
                } else {
                    if (null != listOne && listOne.size() > 0) {
                        listOne.clear();
                    }
                    listOne.addAll(listOneGet);
                }
                refreshLayout.complete();
                hud.dismiss();
                adapterBasisPowerDetail.setList(listOne);
                if (listOne.size() == 0) {
                    lvOne.setVisibility(View.GONE);
                } else {
                    lvOne.setVisibility(View.VISIBLE);
                }
                tvValue.setText(Util.getStringFromDecimal(basePowerBean.getData().getCommonPower(), 2));
                break;
            case GlobalMessageType.RequestCode.TIME_POWER_LIST:
                hud.dismiss();
                TimePowerBean timePowerBean = (TimePowerBean) message.obj;
                if (null != timePowerBean) {
                    TimePowerBean.DataBean dataBean = timePowerBean.getData();
                    if (null != dataBean) {
                        refreshLayout.complete();
                        tvMyTime.setText(String.format(getString(R.string.tip65), dataBean.getDays()));
                        tvValue.setText(Util.getStringFromDecimal(dataBean.getTimePower(), 2));
                        listTwo = timePowerBean.getData().getTimeRules();
                        if (null != listTwo && listTwo.size() > 0) {
                            adapterDatePowerDetail.setList(listTwo);
                        }
                    }
                }
                break;
            case GlobalMessageType.RequestCode.QUERY_SHARE:
                hud.dismiss();
                refreshLayout.complete();
                AddressSharePowerBean sharePowerBean = (AddressSharePowerBean) message.obj;
                if (null != sharePowerBean) {
                    AddressSharePowerBean.DataBean dataBean = sharePowerBean.getData();
                    if (null != dataBean) {
                        tvAmountShare.setText(dataBean.getFirstChildCount() + "");
                        tvAmountShareEffective.setText(dataBean.getValidChildCount() + "");
                        tvNumSys.setText(dataBean.getMaxAddressTier() + "");
                        tvAccountFs.setText(dataBean.getValidAddressTier() + "");
                        tvPoolBasisPower.setText(Util.getStringFromDecimal(dataBean.getAllCommonPower(), 2));
                        tvValue.setText(Util.getStringFromDecimal(dataBean.getSharePower(), 2));
                    }
                }
                break;
        }
    }

    private void initView() {
        tvTitle.setText(R.string.tip75);
        userWalletBean = DaoUtil.selectNowWallet();
        adapterBasisPowerDetail = new AdapterBasisPowerDetail(this);
        lvOne.setAdapter(adapterBasisPowerDetail);

        adapterDatePowerDetail = new AdapterDatePowerDetail(this);
        lvTwo.setAdapter(adapterDatePowerDetail);

        listOne = new ArrayList<>();
        listOneGet = new ArrayList<>();
        listTwo = new ArrayList<>();

        adapterBasisPowerDetail.setList(listOne);
        adapterDatePowerDetail.setList(listTwo);
        mposition = 1;
        tvBasisPower.setSelected(true);
        setBg(tvBasisPower);
        tvMyTime.setVisibility(View.GONE);
        tvType.setText(R.string.tip52);

        initLoading(this);
        hud.show();
        iRequest.queryBasePowerList(pageNumber, pageSize, userWalletBean.getAddress());

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                tag = addmore;
                pageNumber++;
                if (mposition == 1) {
                    iRequest.queryBasePowerList(pageNumber, pageSize, userWalletBean.getAddress());
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tag = refresh;
                pageNumber = 1;
                if (mposition == 1) {
                    iRequest.queryBasePowerList(pageNumber, pageSize, userWalletBean.getAddress());
                }
                if (mposition == 2) {
                    iRequest.queryTimePowerList(userWalletBean.getAddress());
                }
                if (mposition == 3) {
                    iRequest.queryShare(userWalletBean.getAddress());
                }
            }
        });


    }

    @OnClick({R.id.tv_basis_power, R.id.tv_date_power, R.id.tv_share_power})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_basis_power:
                if (!tvBasisPower.isSelected()) {
                    setBg(tvBasisPower);
                    mposition = 1;
                    llOne.setVisibility(View.VISIBLE);
                    llTwo.setVisibility(View.GONE);
                    llThree.setVisibility(View.GONE);
                    tvMyTime.setVisibility(View.GONE);
                    tvType.setText(R.string.tip52);
                    tag = refresh;
                    pageNumber = 1;
                    iRequest.queryBasePowerList(pageNumber, pageSize, userWalletBean.getAddress());
                    hud.show();
                    refreshLayout.setEnableLoadmore(true);
                }
                break;
            case R.id.tv_date_power:
                if (!tvDatePower.isSelected()) {
                    setBg(tvDatePower);
                    mposition = 2;
                    llOne.setVisibility(View.GONE);
                    llTwo.setVisibility(View.VISIBLE);
                    llThree.setVisibility(View.GONE);
                    tvMyTime.setVisibility(View.VISIBLE);
                    tvType.setText(R.string.tip53);
                    tag = refresh;
                    pageNumber = 1;
                    hud.show();
                    iRequest.queryTimePowerList(userWalletBean.getAddress());
                    refreshLayout.setEnableLoadmore(false);
                }
                break;
            case R.id.tv_share_power:
                if (!tvSharePower.isSelected()) {
                    setBg(tvSharePower);
                    mposition = 3;
                    llOne.setVisibility(View.GONE);
                    llTwo.setVisibility(View.GONE);
                    llThree.setVisibility(View.VISIBLE);
                    tvMyTime.setVisibility(View.GONE);
                    tvType.setText(R.string.tip54);
                    tag = refresh;
                    pageNumber = 1;
                    hud.show();
                    iRequest.queryShare(userWalletBean.getAddress());
                    refreshLayout.setEnableLoadmore(false);
                }
                break;
        }
    }

    private void setBg(TextView tv) {
        tvBasisPower.setSelected(false);
        tvDatePower.setSelected(false);
        tvSharePower.setSelected(false);
        tv.setSelected(true);
    }
}
