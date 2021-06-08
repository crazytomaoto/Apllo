package com.sz.apollo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.ActivityUtil;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.VersionBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们
 */
public class AboutUsAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.ll_check_version)
    LinearLayout llCheckVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.LATEST_VERSION:
                VersionBean versionBean = (VersionBean) message.obj;
                hud.dismiss();
                if (null != versionBean) {
                    VersionBean.DataBean dataBean = versionBean.getData();
                    if (null != dataBean) {
                        compareVersion(dataBean);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                String error = (String) message.obj;
                if (!StringUtils.stringToBoolean(error)) {
                    ToastUtil.show(this, error);
                }
                break;


        }
    }

    private void compareVersion(VersionBean.DataBean dataBean) {
        int versionCode = ActivityUtil.getVersionCode(this);
        if (dataBean.getAppVersionCode() != versionCode) {
            try {
                if (!StringUtils.isEmpty(dataBean.getAppPath())) {
                    DialogUtil.showVersionCheckDialog(this, dataBean.getAppVersionName(), dataBean.getAppDesc(), (v, d) -> {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        Uri targetUrl = Uri.parse(dataBean.getAppPath());
                        intent.setData(targetUrl);
                        startActivity(intent);
                    });
                } else {
                    ToastUtil.show(this, getString(R.string.tip189));
                }
            } catch (Exception e) {
                ToastUtil.show(this, getString(R.string.tip189));
            }

        } else {
            ToastUtil.show(this, getString(R.string.tip188));
        }
    }

    private void initView() {
        tvTitle.setText(R.string.tip95);
        tvVersion.setText(ActivityUtil.getVersionName(this));
        initLoading(this);
    }

    @OnClick({R.id.ll_check_version, R.id.ll_contact_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_check_version:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                hud.show();
                iRequest.latestVersion();
                break;
            case R.id.ll_contact_us:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startWebAcyAcy(this, getString(R.string.tip240), RequestHost.WebUrls.contactUs);
                break;
        }
    }
}
