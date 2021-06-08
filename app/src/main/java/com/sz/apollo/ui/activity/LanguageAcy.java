package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.adapters.AdapterChooseLanguage;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.LanguageBean;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.language_util.LanguageType;
import com.sz.apollo.ui.utils.language_util.MultiLanguageUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sz.apollo.ui.utils.language_util.MultiLanguageUtil.SAVE_LANGUAGE;

/**
 * 语言
 */
public class LanguageAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.lv_language)
    ListView lvLanguage;
    private AdapterChooseLanguage adapter;
    private List<LanguageBean> list;
    private int positionNow = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);
        tvTitle.setText(R.string.tip135);
        initView();
    }

    private void initView() {
        adapter = new AdapterChooseLanguage(this);
        lvLanguage.setAdapter(adapter);
        list = new ArrayList<>();
        LanguageBean bean1 = new LanguageBean();
        bean1.setLanguage(getString(R.string.lag_ch));
//        bean1.setL_code("zh_CN");
        LanguageBean bean2 = new LanguageBean();
        bean2.setLanguage(getString(R.string.lag_en));
//        bean2.setL_code("en_US");
        LanguageBean bean3 = new LanguageBean();
        bean3.setLanguage(getString(R.string.lag_jp));
//        bean3.setL_code("ko_south");
        LanguageBean bean4 = new LanguageBean();
        bean4.setLanguage(getString(R.string.lag_korea));

        LanguageBean bean5= new LanguageBean();
        bean5.setLanguage(getString(R.string.lag_ch_traditional));



//        bean3.setL_code("ko_south");
//        LanguageBean bean5 = new LanguageBean();
//        bean5.setLanguage("ViệtName");
//        bean3.setL_code("ko_south");
//        LanguageBean bean6 = new LanguageBean();
//        bean6.setLanguage("عربي ،");
//        bean3.setL_code("ko_south");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
//        list.add(bean6);
        adapter.setList(list);
        positionNow = PlatformConfig.getInt(SAVE_LANGUAGE, 0);
        adapter.setSelectedposition(positionNow);
        lvLanguage.setOnItemClickListener((parent, view, position, id) -> {
            positionNow = position;
            adapter.setSelectedposition(position);
            MultiLanguageUtil.getInstance().updateLanguage(position);
            changeTitle();
        });
    }

    private void changeTitle() {
        int languageType = PlatformConfig.getInt(SAVE_LANGUAGE, 0);
        if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            tvTitle.setText(getString(R.string.lag_ch));
        } else if (languageType == LanguageType.LANGUAGE_TRADITIONAL_CHINESE) {
            tvTitle.setText(getString(R.string.lag_ch_traditional));
        } else if (languageType == LanguageType.LANGUAGE_EN) {
            tvTitle.setText(getString(R.string.lag_en));
        } else if (languageType == LanguageType.LANGUAGE_KOREA) {
            tvTitle.setText(getString(R.string.lag_korea));
        } else if (languageType == LanguageType.LANGUAGE_JAPAN) {
            tvTitle.setText(getString(R.string.lag_jp));
        } else {
            tvTitle.setText(getString(R.string.lag_ch));
        }
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        MultiLanguageUtil.getInstance().updateLanguage(positionNow);
        PlatformConfig.setValue(Constant.SpConstant.USER_LANGUANGE, list.get(positionNow).getL_code());
        ApolloApplication.getInstance().finishAllActivity();
        UiHelper.startHomepageActivity(this);
    }
}
