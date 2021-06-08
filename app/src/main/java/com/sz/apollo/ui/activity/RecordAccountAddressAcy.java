package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.RightScrollAdapter;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.MyJSONFormat;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.views.CustomHorizontalScrollView;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 账号地址
 */
public class RecordAccountAddressAcy extends BasicActivity implements RightScrollAdapter.OnContentScrollListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_balance_aot)
    TextView tvBalanceAot;
    @BindView(R.id.tv_balance_aoc)
    TextView tvBalanceAoc;
    @BindView(R.id.tv_balance_nbo)
    TextView tvBalanceNbo;
    @BindView(R.id.tv_date)
    LinearLayout tvDate;
    @BindView(R.id.rv_tab_right)
    RecyclerView rvTabRight;
    @BindView(R.id.hor_scrollview)
    CustomHorizontalScrollView horScrollview;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    private String account;
    private int pageSize = 20, pageNumber = 1;
    private RightScrollAdapter contentAdapter;
    private List<JSONObject> list, listGet;
    private String tag = "";
    private static String addmore = "ADDMORE";
    private static String refresh = "REFRESH";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_account_address);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.GET_RECORD_BY_ADDRESS:
                String valueString = (String) message.obj;
                hud.dismiss();
                refreshLayout.complete();
                if (!StringUtils.isEmpty(valueString)) {
                    JSONObject jsonObjectA = JSON.parseObject(valueString);
                    if (null != jsonObjectA) {
                        JSONObject jsonObjectB = jsonObjectA.getJSONObject("data");
                        if (null != jsonObjectB) {
                            JSONArray jsonObjectC = jsonObjectB.getJSONArray("balance");
                            if (jsonObjectC.size() == 1) {
                                String aot = (String) jsonObjectC.get(0);
                                tvBalanceAot.setText(aot);
                                tvBalanceAoc.setVisibility(View.GONE);
                            } else if (jsonObjectC.size() == 2) {
                                String aot = (String) jsonObjectC.get(0);
                                tvBalanceAot.setText(aot);
                                String aoc = (String) jsonObjectC.get(1);
                                tvBalanceAoc.setText(aoc);
                            } else if (jsonObjectC.size() == 3) {
                                String aot = (String) jsonObjectC.get(0);
                                tvBalanceAot.setText(aot);
                                String aoc = (String) jsonObjectC.get(1);
                                tvBalanceAoc.setText(aoc);
                                String nbo = (String) jsonObjectC.get(2);
                                tvBalanceNbo.setText(nbo);
                            } else {
                                tvBalanceAot.setText("0AOT");
                                tvBalanceAoc.setText("0AOC");
                                tvBalanceNbo.setText("0NBO");
                            }

                            JSONArray jsonObjectD = jsonObjectB.getJSONArray("list");
                            if (jsonObjectD.size() > 0) {
                                for (int i = 0; i < jsonObjectD.size(); i++) {
                                    listGet.add((JSONObject) jsonObjectD.get(i));
                                }
                            }
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
//                            list =sortData(list);
                            contentAdapter.setDatas(list);
                            if (list.size() == 0) {
                                llEmpty.setVisibility(View.VISIBLE);
                                llData.setVisibility(View.GONE);
                            } else {
                                llEmpty.setVisibility(View.GONE);
                                llData.setVisibility(View.VISIBLE);
                            }

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
                refreshLayout.complete();
                llEmpty.setVisibility(View.VISIBLE);
                llData.setVisibility(View.GONE);
                hud.dismiss();
                break;
        }
    }

    private void initView() {
        list = new ArrayList<>();
        listGet = new ArrayList<>();
        tvTitle.setText(getString(R.string.tip158));
        initLoading(this);
        hud.show();
        tvAddress.setText(account);
        tvEmpty.setText(getString(R.string.tip236));
        iRequest.getTransactionsByAccount2(account, pageNumber, pageSize);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTabRight.setLayoutManager(linearLayoutManager);
        //处理内容部分
        rvTabRight.setLayoutManager(new LinearLayoutManager(this));
        rvTabRight.setHasFixedSize(true);
        contentAdapter = new RightScrollAdapter(this);
        rvTabRight.setAdapter(contentAdapter);
        contentAdapter.setOnContentScrollListener(this);
        contentAdapter.setOnRawClickListener((view, position) -> {
            JSONObject jsonObject = list.get(position);
            if (null != jsonObject) {
                JSONObject jsonRaw = jsonObject.getJSONObject("raw");

                String rawContent = MyJSONFormat.print(jsonRaw.toJSONString());
                DialogUtil.showDialogRaw(RecordAccountAddressAcy.this, rawContent, "RAW", null);

            }
        });
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                tag = addmore;
                pageNumber++;
                iRequest.getTransactionsByAccount2(account, pageNumber, pageSize);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tag = refresh;
                pageNumber = 1;
                iRequest.getTransactionsByAccount2(account, pageNumber, pageSize);
            }
        });

    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            account = bundle.getString("account");
        }
    }

    @Override
    public void onScroll(int offestX) {
        JSONObject jsonObject = list.get(offestX);
        if (null != jsonObject) {
            JSONObject jsonRaw = jsonObject.getJSONObject("raw");
        }
    }

    private List<JSONObject> sortData(List<JSONObject> mList) {
        Collections.sort(mList, new Comparator<JSONObject>() {
            /**
             *
             * @param lhs
             * @param rhs
             * @return an integer < 0 if lhs is less than rhs, 0 if they are
             *         equal, and > 0 if lhs is greater than rhs,比较数据大小时,这里比的是时间
             */
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                Date date1 = stringToDate(lhs.getString("blockTime"));
                Date date2 = stringToDate(rhs.getString("blockTime"));
                // 对日期字段进行升序，如果欲降序可采用after方法
                if (date1.before(date2)) {
                    return 1;
                }
                return -1;
            }
        });
        return mList;
    }

    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }
}
