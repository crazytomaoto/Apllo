package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterFlashExchangeRecord;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.FlashRecordBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 闪兑记录
 */
public class FlashRecordAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_data)
    ListView lvData;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    @BindView(R.id.ll_page)
    LinearLayout llPage;
    private AdapterFlashExchangeRecord adapter;
    private List<FlashRecordBean.DataBeanX.DataBean> list, listGet;
    private UserWalletBean userWalletBean;
    private int pageNumber = 1;
    private int pageSize = 10;
    private String tag = "";
    private static String addmore = "ADDMORE";
    private static String refresh = "REFRESH";
    private String symbol;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_record);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            symbol = bundle.getString("symbol");
        }
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.FLASH_LIST:
                FlashRecordBean flashRecordBean = (FlashRecordBean) message.obj;
                if (null != flashRecordBean) {
                    FlashRecordBean.DataBeanX dataBeanX = flashRecordBean.getData();
                    if (null != dataBeanX) {
                        hud.dismiss();
                        listGet = dataBeanX.getData();
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
                        adapter.setList(list);
                        if (list.size() == 0) {
                            llEmpty.setVisibility(View.VISIBLE);
                            llData.setVisibility(View.GONE);
                        } else {
                            llEmpty.setVisibility(View.GONE);
                            llData.setVisibility(View.VISIBLE);
                        }
                        break;
                    }
                }
        }
    }

    private void initView() {
        tvTitle.setText(R.string.tip88);
        list = new ArrayList<>();
        listGet = new ArrayList<>();
        if (symbol.equals(Constant.TYPE_AOT)) {
            llPage.setBackgroundColor(getResources().getColor(R.color.color_page_main));
        }
        if (symbol.equals(Constant.TYPE_NBO)) {
            llPage.setBackgroundColor(getResources().getColor(R.color.page_color3));
        }
        initLoading(this);
        userWalletBean = DaoUtil.selectNowWallet();
        adapter = new AdapterFlashExchangeRecord(this, symbol);
        lvData.setAdapter(adapter);
        adapter.setList(list);
        if (list.size() <= 0) {
            llEmpty.setVisibility(View.VISIBLE);
            llData.setVisibility(View.GONE);
        } else {
            llEmpty.setVisibility(View.GONE);
            llData.setVisibility(View.VISIBLE);
        }
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                tag = addmore;
                pageNumber++;
                iRequest.flashExchangeList(userWalletBean.getAddress(), pageNumber, pageSize);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tag = refresh;
                pageNumber = 1;
                iRequest.flashExchangeList(userWalletBean.getAddress(), pageNumber, pageSize);
            }
        });
        hud.show();
        iRequest.flashExchangeList(userWalletBean.getAddress(), pageNumber, pageSize);
    }
}
