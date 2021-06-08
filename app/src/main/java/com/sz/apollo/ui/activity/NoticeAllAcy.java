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
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterNotice;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.NoticeBean;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公告页面
 */
public class NoticeAllAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    private AdapterNotice adapterNotice;
    private List<NoticeBean.DataBeanX.DataBean> list, listGet;
    private int pageNumber = 1;
    private int pageSize = 10;
    private NoticeBean noticeBean;
    private String tag = "";
    private static String addmore = "ADDMORE";
    private static String refresh = "REFRESH";
    private int noticeType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_all);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            noticeType = bundle.getInt("type");
        }
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.QUERY_NOTICE:
                hud.dismiss();
                noticeBean = (NoticeBean) message.obj;
                listGet = noticeBean.getData().getData();
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
                adapterNotice.setList(list);
                if (list.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);
                } else {
                    llEmpty.setVisibility(View.GONE);
                    lv.setVisibility(View.VISIBLE);
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                refreshLayout.complete();
                llEmpty.setVisibility(View.VISIBLE);
                lv.setVisibility(View.GONE);
                break;
        }
    }

    private void initView() {
        tvTitle.setText(R.string.tip156);
        list = new ArrayList<>();
        listGet = new ArrayList<>();
        if (noticeType == 1) {
            llMain.setBackgroundColor(getResources().getColor(R.color.color_page_main));
        } else {
            llMain.setBackgroundColor(getResources().getColor(R.color.page_color3));
        }
        adapterNotice = new AdapterNotice(this);
        lv.setAdapter(adapterNotice);
        adapterNotice.setList(list);
        initLoading(this);
        hud.show();
        iRequest.queryNotice(pageNumber, pageSize, "", noticeType);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            UiHelper.startWebAcyAcy(this, list.get(position).getTitle(), list.get(position).getUrl());
        });
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                tag = addmore;
                pageNumber++;
                iRequest.queryNotice(pageNumber, pageSize, "", noticeType);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tag = refresh;
                pageNumber = 1;
                iRequest.queryNotice(pageNumber, pageSize, "", noticeType);
            }
        });
    }
}
