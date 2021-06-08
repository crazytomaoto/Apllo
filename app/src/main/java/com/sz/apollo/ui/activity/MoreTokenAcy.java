package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.adapters.AdapterMoreToken;
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
public class MoreTokenAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_tokens)
    ListView lvTokens;
    private String kind;
    private ArrayList<TokenKindBean> list;
    private AdapterMoreToken adapterMoreToken;
    private UserWalletBean userWalletBean;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            kind = bundle.getString("kind");
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
        switch (kind) {
            case Constant.TYPE_ETH:
                tvTitle.setText(R.string.t_assest_manage);
                TokenKindBean ethBean = new TokenKindBean();
                ethBean.setName("ETH");
                ethBean.setContractAddress("eth_address");
                ethBean.setCanBeSet(false);

                TokenKindBean usdtBean = new TokenKindBean();
                usdtBean.setName("USDT");
                usdtBean.setContractAddress("usdt_address");
                if (userWalletBean.getIsAddUSDT().equals("1")) {
                    usdtBean.setSelected(true);
                } else {
                    usdtBean.setSelected(false);
                }
                usdtBean.setCanBeSet(true);
                list.add(ethBean);
                list.add(usdtBean);
                break;
            case Constant.TYPE_APOLLO:
                tvTitle.setText(R.string.t_more_token);
                TokenKindBean aotBean = new TokenKindBean();
                aotBean.setName(Constant.TYPE_AOT);
                aotBean.setContractAddress("aot_address");
                aotBean.setCanBeSet(false);

                TokenKindBean aocBean = new TokenKindBean();
                aocBean.setName(Constant.TYPE_AOC);
                aocBean.setContractAddress("aot_address");
                aocBean.setCanBeSet(false);

                TokenKindBean nboBean = new TokenKindBean();
                nboBean.setName(Constant.TYPE_NBO);
                nboBean.setContractAddress("nbo_address");
                nboBean.setCanBeSet(false);

                TokenKindBean ddaoBean = new TokenKindBean();
                ddaoBean.setName(Constant.TYPE_DDAO);
                ddaoBean.setContractAddress("ddao_address");
                ddaoBean.setCanBeSet(false);

                TokenKindBean lonBean = new TokenKindBean();
                lonBean.setName(Constant.TYPE_LON);
                lonBean.setContractAddress("lon_address");
                lonBean.setCanBeSet(false);


                list.add(aotBean);
                list.add(aocBean);
                list.add(nboBean);
                list.add(ddaoBean);
                list.add(lonBean);
                break;
        }
        adapterMoreToken = new AdapterMoreToken(this);
        lvTokens.setAdapter(adapterMoreToken);
        adapterMoreToken.setList(list);

        adapterMoreToken.setiOnItemClickListner(new AdapterMoreToken.ISelectChangeListner() {
            @Override
            public void onChange(int position, boolean isChecked) {
                userWalletBean.setIsAddUSDT(isChecked == true ? "1" : "0");
                DaoUtil.updateWallet(userWalletBean);
                list.get(position).setSelected(isChecked);
                adapterMoreToken.notifyDataSetChanged();
            }
        });


    }
}
