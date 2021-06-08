package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.TransDetailByTxIdBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交易详情1
 */
public class TransDetailOneAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_trade_type)
    TextView tvTradeType;
    @BindView(R.id.tv_trade_hash)
    TextView tvTradeHash;
    @BindView(R.id.tv_hash_detail)
    TextView tvHashDetail;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_state)
    TextView tvState;
    private String hash, transType;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            hash = bundle.getString("hash");
            transType = bundle.getString("transType");
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.TRANS_DETAIL:
                hud.dismiss();
                TransDetailByTxIdBean transRecordByHashBean = (TransDetailByTxIdBean) message.obj;
                if (null != transRecordByHashBean) {
                    TransDetailByTxIdBean.DataBean dataBean = transRecordByHashBean.getData();
                    if (null != dataBean) {
                        try {
                            String from = dataBean.getFromAddress();
                            String to = dataBean.getToAddress();
                            String quantity = Util.getStringFromDecimal(dataBean.getAmount(), 4);
//                            String[] splitArray = quantity.split(" ");
//                            quantity = splitArray[0];
                            int state = dataBean.getState();
                            if (state == 1) {
                                tvState.setText(getString(R.string.t_state_finish));
                            } else {
                                tvState.setText(getString(R.string.tip180));
                            }
                            int type = dataBean.getType();
                            String memo = dataBean.getRemark();
                            tvFrom.setText(from);
                            tvTo.setText(to);
                            tvRemark.setText(memo);


                            if (to.equals(DaoUtil.selectNowWallet().getAddress())) {
                                quantity = "+" + quantity;
                                tvAmount.setTextColor(getColor(R.color.text_green2));
                            } else {
                                quantity = "-" + quantity;
                                tvAmount.setTextColor(getColor(R.color.color_red2));
                            }
                            tvAmount.setText(quantity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_detail_one);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.t_trans_detail);
        tvTradeHash.setText(hash);
        initLoading(this);
        hud.show();
        tvType.setText(transType);
        iRequest.transDetail(hash);
    }

    @OnClick(R.id.tv_hash_detail)
    public void onViewClicked() {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        UiHelper.startTransDetailTwoAcy(this, hash);
    }

    @OnClick({R.id.tv_hash_detail, R.id.tv_from, R.id.tv_to, R.id.tv_trade_hash, R.id.tv_remark})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hash_detail:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startTransDetailTwoAcy(this, hash);
            case R.id.tv_from:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (!StringUtils.isEmpty(tvFrom.getText().toString())) {
                    Util.copy(this, tvFrom.getText().toString());
                    ToastUtil.show(this, getString(R.string.t_copied));
                }
                break;
            case R.id.tv_to:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (!StringUtils.isEmpty(tvTo.getText().toString())) {
                    Util.copy(this, tvTo.getText().toString());
                    ToastUtil.show(this, getString(R.string.t_copied));
                }
                break;
            case R.id.tv_trade_hash:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (!StringUtils.isEmpty(tvTradeHash.getText().toString())) {
                    Util.copy(this, tvTradeHash.getText().toString());
                    ToastUtil.show(this, getString(R.string.t_copied));
                }
                break;
            case R.id.tv_remark:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (!StringUtils.isEmpty(tvRemark.getText().toString())) {
                    Util.copy(this, tvRemark.getText().toString());
                    ToastUtil.show(this, getString(R.string.t_copied));
                }
                break;
        }
    }
}
