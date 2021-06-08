package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.sz.apollo.R;
import com.sz.apollo.ui.adapters.AdapterMyPhrase;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.views.AutoGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 助记词备份
 */
public class BackupPhraseAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.gv_phrase)
    AutoGridView gvPhrase;
    @BindView(R.id.tv_next)
    TextView tvNext;
    private AdapterMyPhrase adapterMyPhrase;
    private List<String> list;
    private UserWalletBean userWalletBean;
    private boolean isFromChargeBackup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_up_phrase);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            userWalletBean = (UserWalletBean) bundle.getSerializable("userWalletBean");
            isFromChargeBackup = bundle.getBoolean("isFromChargeBackup");
        }
    }

    private void initView() {
        tvTitle.setText(R.string.t_back_up_phrase);
        adapterMyPhrase = new AdapterMyPhrase(this);
        list = new ArrayList<>();
        if (null != userWalletBean) {
            String myString[] = userWalletBean.getPhrase().split(" ");
            for (int i = 0; i < myString.length; i++) {
                list.add(myString[i]);
            }
        }
        gvPhrase.setAdapter(adapterMyPhrase);
        adapterMyPhrase.setList(list);
    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        UiHelper.startConfirmPhraseAcy(this, userWalletBean, isFromChargeBackup);
    }
}
