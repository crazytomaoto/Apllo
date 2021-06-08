package com.sz.apollo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.widget.VideoView;

import com.alibaba.fastjson.JSONObject;
import com.gyf.barlibrary.ImmersionBar;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 启动页
 */
public class GuideActivity extends BasicActivity {
    @BindView(R.id.video_view)
    VideoView videoView;
    private JSONObject jsonObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ImmersionBar.with(this).fullScreen(true).navigationBarColor(R.color.trans).init();
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent
                    .ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        setupVideo();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.ACCOUNT_STATE:
                jsonObject = (JSONObject) message.obj;
                if (null == jsonObject || jsonObject.getBoolean("success") == false) {
                    ToastUtil.showLong(this, getString(R.string.error_data));
                    return;
                }
                if (StringUtils.isEmpty(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                    UiHelper.startSetPinCodeAcy(GuideActivity.this);
                } else {
                    List<UserWalletBean> list = DaoUtil.selectAllWallet();
                    if (null == list || list.size() == 0) {
                        UiHelper.startChooseTypeAcy(GuideActivity.this);
                    } else {
                        UiHelper.startHomepageActivity(GuideActivity.this);
                    }
                }
                finish();
                break;
            case GlobalMessageType.RequestCode.ERROR:
                ToastUtil.showLong(this, getString(R.string.error_data));
                break;
        }
    }

    private void setupVideo() {
        videoView.setOnPreparedListener(mp -> videoView.start());
        videoView.setOnCompletionListener(mp -> {
            stopPlaybackVideo();
            iRequest.accountState(Util.getDeviceOnlyNum(this));
        });
        videoView.setOnErrorListener((mp, what, extra) -> {
            stopPlaybackVideo();
            return true;
        });

        try {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.guide);
            videoView.setVideoURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!videoView.isPlaying()) {
            videoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (videoView.canPause()) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlaybackVideo();
    }

    private void stopPlaybackVideo() {
        try {
            videoView.stopPlayback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
