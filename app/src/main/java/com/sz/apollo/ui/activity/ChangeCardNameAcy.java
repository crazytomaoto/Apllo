package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeCardNameAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ed_name)
    EditText edName;
    String name;
    private UserWalletBean nowWallet;

    /**
     * 卡片名称
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_card_name);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.card_name);
        tvRight.setText(R.string.t_finish);
        nowWallet = DaoUtil.selectNowWallet();
        edName.setText(nowWallet.getRemark());
        tvRight.setTextColor(getResources().getColor(R.color.text_green));
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            name = bundle.getString("name");
        }
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        String name = edName.getText().toString().trim();
        if (!StringUtils.isEmpty(name)) {
            nowWallet.setRemark(name);
            DaoUtil.updateWallet(nowWallet);
            hideInput();
            ToastUtil.show(this, getString(R.string.tip121));
            finish();
        }
    }
}
