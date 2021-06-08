package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterShareSystem;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.ShareSystemBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分享体系
 */
public class ShareSystemAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_share)
    ListView lvShare;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.tv_all)
    TextView tvAll;
    private AdapterShareSystem adapter;
    private List<ShareSystemBean.DataBean.ChildListBean> list;

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.QUERY_CHILD:
                hud.dismiss();
                ShareSystemBean shareSystemBean = (ShareSystemBean) message.obj;
                if (null != shareSystemBean) {
                    list = shareSystemBean.getData().getChildList();
                    adapter.setList(list);
                    if (list.size() > 0) {
                        tvAll.setText(shareSystemBean.getData().getTotalChild()+"");
                        lvShare.setVisibility(View.VISIBLE);
                        llEmpty.setVisibility(View.GONE);
                    } else {
                        tvAll.setText("0");
                        lvShare.setVisibility(View.GONE);
                        llEmpty.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                String error = (String) message.obj;
                if (!StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frg_share_sys);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.tip56);
        tvEmpty.setText(R.string.tip57);
        list = new ArrayList<>();
        adapter = new AdapterShareSystem(this);
        lvShare.setAdapter(adapter);
        adapter.setList(list);
        initLoading(this);
        hud.show();
        iRequest.getShareList(DaoUtil.selectNowWallet().getAddress());
    }
}
