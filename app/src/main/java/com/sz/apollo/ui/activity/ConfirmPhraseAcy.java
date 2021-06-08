package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.ui.adapters.AdapterConfirmPhraseAbove;
import com.sz.apollo.ui.adapters.AdapterConfirmPhraseBelow;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.PhraseBackupBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认助记词
 */
public class ConfirmPhraseAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.gv_phrase_above)
    GridView gvPhraseAbove;
    @BindView(R.id.gv_phrase_bellow)
    GridView gvPhraseBellow;
    @BindView(R.id.tv_next)
    RoundTextView tvNext;
    private List<String> listOld, listSelected;
    private AdapterConfirmPhraseAbove adapterAbove;
    private AdapterConfirmPhraseBelow adapterBelow;
    private List<PhraseBackupBean> listOldPhrase;
    private UserWalletBean userWalletBean;
    private boolean isFromChargeBackup;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            userWalletBean = (UserWalletBean) bundle.getSerializable("userWalletBean");
            isFromChargeBackup = bundle.getBoolean("isFromChargeBackup");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_phrase);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.t_confirm_phrase);
        adapterBelow = new AdapterConfirmPhraseBelow(this);
        listOld = new ArrayList<>();
        listOldPhrase = new ArrayList<>();

        if (null != userWalletBean) {
            String myString[] = userWalletBean.getPhrase().split(" ");
            for (int i = 0; i < myString.length; i++) {
                PhraseBackupBean bean = new PhraseBackupBean();
                bean.setCharacter(myString[i]);
                bean.setSelected(false);
                listOldPhrase.add(bean);
            }
        }
        Collections.shuffle(listOldPhrase);

        listSelected = new ArrayList<>();
        adapterAbove = new AdapterConfirmPhraseAbove(this);
        gvPhraseAbove.setAdapter(adapterAbove);
        adapterAbove.setList(listSelected);

        gvPhraseBellow.setAdapter(adapterBelow);
        adapterBelow.setList(listOldPhrase);
        gvPhraseBellow.setOnItemClickListener((parent, view, position, id) -> {
            if (!listOldPhrase.get(position).isSelected()) {
                PhraseBackupBean nowBean = listOldPhrase.get(position);
                nowBean.setSelected(true);
                listOldPhrase.set(position, nowBean);
                adapterBelow.notifyDataSetChanged();
                listSelected.add(nowBean.getCharacter());
                adapterAbove.notifyDataSetChanged();
            }
        });

        gvPhraseAbove.setOnItemClickListener((parent, view, position, id) -> {
            String cha = listSelected.get(position);
            listSelected.remove(position);
            adapterAbove.notifyDataSetChanged();
            for (PhraseBackupBean bean : listOldPhrase) {
                if (bean.getCharacter().equals(cha)) {
                    int index = listOldPhrase.indexOf(bean);
                    bean.setSelected(false);
                    listOldPhrase.set(index, bean);
                    adapterBelow.notifyDataSetChanged();
                    break;
                }
            }
        });

    }

    private String getHYString() {

        String nowString = "";
        for (int i = 0; i < listSelected.size(); i++) {
            if (StringUtils.isNotEmpty(nowString)) {
                nowString = nowString + " " + listSelected.get(i);
            } else {
                nowString = listSelected.get(i);
            }
        }
        return nowString;
    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        if (userWalletBean.getPhrase().equals(getHYString())) {
            if (isFromChargeBackup) {
                ApolloApplication.getInstance().finishAllActivity();
                iRequest.addAccountEth(userWalletBean.getPk(), Util.getDeviceOnlyNum(this), userWalletBean.getAddress());
                UiHelper.startHomepageActivity(this);
            } else {
                UiHelper.startCreateEthSuccessAcy(this, userWalletBean);
            }

        } else {
            ToastUtil.show(this, getString(R.string.tip141));
        }
    }
}
