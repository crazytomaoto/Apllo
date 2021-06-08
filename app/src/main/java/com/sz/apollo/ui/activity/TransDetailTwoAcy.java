package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flyco.roundview.RoundTextView;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交易详情2
 */
public class TransDetailTwoAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_trade_hash)
    TextView tvTradeHash;
    @BindView(R.id.tv_tran_date)
    TextView tvTranDate;
    @BindView(R.id.tv_expiration)
    TextView tvExpiration;
    @BindView(R.id.tv_stateOne)
    RoundTextView tvStateOne;
    @BindView(R.id.tv_stateTwo)
    RoundTextView tvStateTwo;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_block_hash)
    TextView tvBlockHash;
    String hash;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            hash = bundle.getString("hash");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_detail_two);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.t_trans_detail);
        initLoading(this);
        hud.show();
        iRequest.getTransactionByHash(hash);
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.GET_TRANS_BY_HAH:
                hud.dismiss();
                String dataString = (String) message.obj;
                JSONObject jsonObject1 = JSON.parseObject(dataString);
                if (null != jsonObject1) {
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                    if (null != jsonObject2) {
                        String blockHash = jsonObject2.getString("blockHash");
                        String exTime = jsonObject2.getString("expireTime");
                        String blockTime = jsonObject2.getString("blockTime");
                        int height = jsonObject2.getInteger("height");
                        tvTradeHash.setText(hash);
                        tvBlockHash.setText(blockHash);
                        tvTranDate.setText(blockTime);
                        tvExpiration.setText(exTime);
                        tvRemark.setText(height + "");
                    }
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                String error = (String) message.obj;
                hud.dismiss();
                if (!StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                break;

        }
    }

    @OnClick({R.id.tv_trade_hash, R.id.tv_remark, R.id.tv_block_hash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.tv_block_hash:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (!StringUtils.isEmpty(tvBlockHash.getText().toString())) {
                    Util.copy(this, tvBlockHash.getText().toString());
                    ToastUtil.show(this, getString(R.string.t_copied));
                }
                break;
        }
    }
}
