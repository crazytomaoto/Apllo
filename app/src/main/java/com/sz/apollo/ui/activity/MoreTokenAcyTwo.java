package com.sz.apollo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.adapters.AdapterMoreToken;
import com.sz.apollo.ui.adapters.AdapterMoreToken2;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.TokenKindBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 更多币种
 */
public class MoreTokenAcyTwo extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_tokens)
    ListView lvTokens;
    private String tokenType;
    private ArrayList<TokenKindBean> list;
    private AdapterMoreToken2 adapterMoreToken;
    private UserWalletBean userWalletBean;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            tokenType = bundle.getString("tokenType");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_token);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        userWalletBean = DaoUtil.selectNowWallet();
        tvTitle.setText(R.string.t_assest_manage);
        TokenKindBean ethBean = new TokenKindBean();
        ethBean.setName("ETH");
        ethBean.setContractAddress("eth_address");
        ethBean.setCanBeSet(true);

        TokenKindBean usdtBean = new TokenKindBean();
        usdtBean.setName("USDT");
        usdtBean.setContractAddress("usdt_address");
        usdtBean.setCanBeSet(true);

        if (tokenType.equals(Constant.TYPE_ETH)) {
            ethBean.setSelected(true);
        } else {
            usdtBean.setSelected(true);
        }

        list.add(ethBean);
        list.add(usdtBean);

        adapterMoreToken = new AdapterMoreToken2(this, tokenType);
        lvTokens.setAdapter(adapterMoreToken);
        adapterMoreToken.setList(list);

        lvTokens.setOnItemClickListener((parent, view, position, id) -> {
            if (!tokenType.equals(list.get(position).getName())) {
                Intent intent = new Intent();
                intent.putExtra("nowType", list.get(position).getName());
                setResult(Constant.RESULT_CODE, intent);
                finish();
            }
        });

    }
}
