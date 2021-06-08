package com.sz.apollo.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hysd.android.platform_ub.base.ui.BaseFragment;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.ui.basic.BasicFragment;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFragment extends BasicFragment {

    @BindView(R.id.ll_safe_setting)
    LinearLayout llSafeSetting;
    @BindView(R.id.ll_address_book)
    LinearLayout llAddressBook;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_help_center)
    LinearLayout llHelpCenter;
    @BindView(R.id.ll_system_setting)
    LinearLayout llSystemSetting;
    @BindView(R.id.ll_about_us)
    LinearLayout llAboutUs;

    @Override
    protected void handleStateMessage(Message message) {

    }

    @Override
    protected void lazyLoad() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_frg_my, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

    }

    @OnClick({R.id.ll_safe_setting, R.id.ll_address_book, R.id.ll_share, R.id.ll_help_center, R.id.ll_system_setting, R.id.ll_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_safe_setting:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startSafeCenterAcy(getActivity());
                break;
            case R.id.ll_address_book:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startAddressBookAcy(getActivity());
                break;
            case R.id.ll_share:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startShareAcy(getActivity());
                break;
            case R.id.ll_help_center:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startWebAcyAcy(getActivity(), getString(R.string.tip93), RequestHost.WebUrls.helpCenter);
                break;
            case R.id.ll_system_setting:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startSystemSettingAcy(getActivity());
                break;
            case R.id.ll_about_us:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startAboutUsAcy(getActivity());
//                UiHelper.startTestAcy(getActivity(),"测试OncChance","");

                break;
        }
    }

}
