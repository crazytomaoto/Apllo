package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.flyco.roundview.RoundTextView;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.RightScrollBlockHeightAdapter;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IGetBackResult;
import com.sz.apollo.ui.models.RecordBlockHashBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.MyJSONFormat;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.views.CustomHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 区块高度
 */
public class RecordBlockHeightAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_block)
    TextView tvBlock;
    @BindView(R.id.tv_blockHash)
    TextView tvBlockHash;
    @BindView(R.id.tv_block_forward)
    TextView tvBlockForward;
    @BindView(R.id.tv_block_behind)
    TextView tvBlockBehind;
    @BindView(R.id.tv_stateTwo)
    RoundTextView tvStateTwo;
    @BindView(R.id.rv_tab_right)
    RecyclerView rvTabRight;
    @BindView(R.id.hor_scrollview)
    CustomHorizontalScrollView horScrollview;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.tv_block_time)
    TextView tvBlockTime;
    @BindView(R.id.tv_select_block)
    TextView tvSelectBlock;
    @BindView(R.id.tv_select_raw)
    TextView tvSelectRaw;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    private String block;
    private RightScrollBlockHeightAdapter contentAdapter;
    private List<RecordBlockHashBean.DataBeanX.ListBean> list;
    private RecordBlockHashBean.DataBeanX dataBeanX;
    private RecordBlockHashBean.DataBeanX.RawBean.TransactionsBean.TrxBean.TransactionBean.ActionsBean.AuthorizationBean authorizationBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_block_height);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.GET_BLOCK:
                hud.dismiss();
                RecordBlockHashBean recordBlockHashBean = (RecordBlockHashBean) message.obj;
                if (null != recordBlockHashBean) {
                    dataBeanX = recordBlockHashBean.getData();
                    if (null != dataBeanX) {
                        String next = dataBeanX.getNext() + "";
                        String blockHash = dataBeanX.getBlockhash();
                        String prev = dataBeanX.getPrev() + "";
                        String blockTime = dataBeanX.getBlockTime();
                        tvBlockForward.setText(prev);
                        tvBlockBehind.setText(next);
                        tvBlockTime.setText(blockTime);
                        tvBlockHash.setText(blockHash);
                        list = dataBeanX.getList();
                        if (null != list && list.size() > 0) {
                            llEmpty.setVisibility(View.GONE);
                            llData.setVisibility(View.VISIBLE);
                            contentAdapter.setDatas(list);
                        } else {
                            llEmpty.setVisibility(View.VISIBLE);
                            llData.setVisibility(View.GONE);
                        }
                    } else {
                        llEmpty.setVisibility(View.VISIBLE);
                        llData.setVisibility(View.GONE);
                    }
                } else {
                    llEmpty.setVisibility(View.VISIBLE);
                    llData.setVisibility(View.GONE);
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                llEmpty.setVisibility(View.VISIBLE);
                llData.setVisibility(View.GONE);
                hud.dismiss();
                String error = (String) message.obj;
                if (StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                break;
        }
    }

    private void initView() {
        list = new ArrayList<>();
        tvTitle.setText(getString(R.string.tip165));
        initLoading(this);
        hud.show();
        tvBlock.setText(block);
        iRequest.getBlock(block);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTabRight.setLayoutManager(linearLayoutManager);
        tvSelectBlock.setSelected(true);
        tvSelectRaw.setSelected(false);
        //处理内容部分
        rvTabRight.setLayoutManager(new LinearLayoutManager(this));
        rvTabRight.setHasFixedSize(true);
        contentAdapter = new RightScrollBlockHeightAdapter(this);
        rvTabRight.setAdapter(contentAdapter);
        contentAdapter.setDatas(list);
        contentAdapter.setOnRawClickListener((view, position) -> {
            RecordBlockHashBean.DataBeanX.ListBean listBean = list.get(position);
            if (null != listBean) {
                String hash = listBean.getHash();
                List<RecordBlockHashBean.DataBeanX.RawBean.TransactionsBean> transactionsBeanList = dataBeanX.getRaw().getTransactions();
                for (RecordBlockHashBean.DataBeanX.RawBean.TransactionsBean transactionsBean : transactionsBeanList) {
                    if (transactionsBean.getTrx().getId().equals(hash)) {
                        authorizationBean = transactionsBean.getTrx().getTransaction().getActions().get(0).getAuthorization().get(0);
                        break;
                    }
                }
                if (null != authorizationBean) {
                    String rawContent = MyJSONFormat.print(JSONObject.toJSONString(authorizationBean));
                    DialogUtil.showDialogRaw(RecordBlockHeightAcy.this, rawContent, "ACTiONS", null);
                }
            }
        });

    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            block = bundle.getString("block");
        }
    }

    @OnClick({R.id.tv_select_block, R.id.tv_select_raw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_block:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (!tvSelectBlock.isSelected()) {
                    tvSelectBlock.setSelected(true);
                    tvSelectRaw.setSelected(false);
                }
                break;
            case R.id.tv_select_raw:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                if (!tvSelectRaw.isSelected()) {
                    tvSelectRaw.setSelected(true);
                    tvSelectBlock.setSelected(false);
                    String rawContent = MyJSONFormat.print(JSONObject.toJSONString(dataBeanX.getRaw()));
                    DialogUtil.showDialogRaw(RecordBlockHeightAcy.this, rawContent, "RAW", new IGetBackResult() {
                        @Override
                        public void getTextString(Object result) {
                            String re = (String) result;
                            if (re.equals("true")) {
                                tvSelectBlock.setSelected(true);
                                tvSelectRaw.setSelected(false);
                            }
                        }
                    });
                }
                break;
        }
    }
}
