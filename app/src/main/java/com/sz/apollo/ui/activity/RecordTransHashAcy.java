package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.RecordTxHashBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.MyJSONFormat;
import com.sz.apollo.ui.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交易hash
 */
public class RecordTransHashAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_txHash)
    TextView tvTxHash;
    @BindView(R.id.tv_trans_date)
    TextView tvTransDate;
    @BindView(R.id.tv_arrive_date)
    TextView tvArriveDate;
    @BindView(R.id.tv_block_height)
    TextView tvBlockHeight;
    @BindView(R.id.tv_block_hash)
    TextView tvBlockHash;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_trans_name)
    TextView tvTransName;
    @BindView(R.id.tv_hex_data)
    TextView tvHexData;
    @BindView(R.id.tv_authorization)
    TextView tvAuthorization;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.arrow_one)
    ImageView arrowOne;
    @BindView(R.id.arrow_two)
    ImageView arrowTwo;
    @BindView(R.id.arrow_three)
    ImageView arrowThree;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.tv_raw)
    TextView tvRaw;
    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.tv_raw_content)
    TextView tvRawContent;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.sc_data)
    ScrollView scData;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private String txHash;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_trans_hash);
        ButterKnife.bind(this);
        initView();
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
                        tvArriveDate.setText(exTime);
                        tvTransDate.setText(blockTime);
                        tvBlockHash.setText(StringUtils.hideMiddleString(blockHash, 18, 8));
                        tvBlockHeight.setText(height + "");

                        JSONObject rawJson = jsonObject2.getJSONObject("raw");
                        String raw = MyJSONFormat.print(JSONObject.toJSONString(rawJson));
                        tvRawContent.setText(raw);
                        JSONObject trxJson1 = rawJson.getJSONObject("trx");
                        if (null != trxJson1) {
                            RecordTxHashBean recordTxHashBean = JSON.parseObject(trxJson1.toJSONString(), RecordTxHashBean.class);
                            if (null != recordTxHashBean) {
                                RecordTxHashBean.TrxBean trxBean = recordTxHashBean.getTrx();
                                if (null != trxBean) {
                                    List<RecordTxHashBean.TrxBean.ActionsBean> list = trxBean.getActions();
                                    if (null != list && list.size() > 0) {
                                        RecordTxHashBean.TrxBean.ActionsBean actionsBean = list.get(0);
                                        tvAccount.setText("Actions:" + actionsBean.getAccount());
                                        tvTransName.setText(actionsBean.getName());
                                        tvHexData.setText(actionsBean.getHex_data());
                                        RecordTxHashBean.TrxBean.ActionsBean.DataBean dataBean = actionsBean.getData();
                                        List<RecordTxHashBean.TrxBean.ActionsBean.AuthorizationBean> authorizationBeanList = actionsBean.getAuthorization();
                                        tvAuthorization.setText("[\n" + MyJSONFormat.print(JSONObject.toJSONString(authorizationBeanList.get(0))) + "\n]");
                                        tvData.setText(MyJSONFormat.print(JSONObject.toJSONString(actionsBean.getData())));
                                    }
                                } else {
                                    llEmpty.setVisibility(View.VISIBLE);
                                    scData.setVisibility(View.GONE);
                                }
                            } else {
                                llEmpty.setVisibility(View.VISIBLE);
                                scData.setVisibility(View.GONE);
                            }
                        } else {
                            llEmpty.setVisibility(View.VISIBLE);
                            scData.setVisibility(View.GONE);
                        }

                    } else {
                        llEmpty.setVisibility(View.VISIBLE);
                        scData.setVisibility(View.GONE);
                    }
                } else {
                    llEmpty.setVisibility(View.VISIBLE);
                    scData.setVisibility(View.GONE);
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                llEmpty.setVisibility(View.VISIBLE);
                scData.setVisibility(View.GONE);
                break;
        }
    }

    private void initView() {
        tvTitle.setText(getString(R.string.tip166));
        initLoading(this);
        hud.show();
        tvAction.setSelected(true);
        tvRaw.setSelected(false);
        tvTxHash.setText(txHash);
        tvEmpty.setText(R.string.tip238);
        llLeft.setVisibility(View.VISIBLE);
        llRight.setVisibility(View.GONE);
        llEmpty.setVisibility(View.GONE);
        scData.setVisibility(View.VISIBLE);
        iRequest.getTransactionByHash(txHash);
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            txHash = bundle.getString("txHash");
        }
    }

    @OnClick({R.id.arrow_one, R.id.arrow_two, R.id.arrow_three, R.id.tv_action, R.id.tv_raw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.arrow_one:
                if (tvHexData.getVisibility() == View.VISIBLE) {
                    tvHexData.setVisibility(View.GONE);
                    arrowOne.setImageResource(R.drawable.ic_arrow_up);
                } else {
                    tvHexData.setVisibility(View.VISIBLE);
                    arrowOne.setImageResource(R.drawable.ic_arrow_dowm);
                }
                break;
            case R.id.arrow_two:
                if (tvAuthorization.getVisibility() == View.VISIBLE) {
                    tvAuthorization.setVisibility(View.GONE);
                    arrowTwo.setImageResource(R.drawable.ic_arrow_up);
                } else {
                    tvAuthorization.setVisibility(View.VISIBLE);
                    arrowTwo.setImageResource(R.drawable.ic_arrow_dowm);
                }
                break;
            case R.id.arrow_three:
                if (tvData.getVisibility() == View.VISIBLE) {
                    tvData.setVisibility(View.GONE);
                    arrowThree.setImageResource(R.drawable.ic_arrow_up);
                } else {
                    tvData.setVisibility(View.VISIBLE);
                    arrowThree.setImageResource(R.drawable.ic_arrow_dowm);
                }
                break;
            case R.id.tv_action:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (tvAction.isSelected() == false) {
                    tvAction.setSelected(true);
                    tvRaw.setSelected(false);
                    llLeft.setVisibility(View.VISIBLE);
                    llRight.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_raw:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (tvRaw.isSelected() == false) {
                    tvAction.setSelected(false);
                    tvRaw.setSelected(true);
                    llLeft.setVisibility(View.GONE);
                    llRight.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
