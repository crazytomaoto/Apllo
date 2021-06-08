package com.sz.apollo.ui.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.ui.basic.BasicFragment;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 矿场页面
 */
public class TwoFragment extends BasicFragment {


    @Override
    protected void lazyLoad() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_frg_mining_pool, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.iv_apollo, R.id.iv_nbo, R.id.iv_ddao, R.id.iv_lon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_apollo:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startMiningPoolApolloAcyAcy(getActivity());
                break;
            case R.id.iv_nbo:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startMiningPoolNBOAcy(getActivity());
            case R.id.iv_ddao:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startMiningPoolDDaoAcy(getActivity());
            case R.id.iv_lon:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startMiningPoolLonAcy(getActivity());
                break;
        }
    }
}
