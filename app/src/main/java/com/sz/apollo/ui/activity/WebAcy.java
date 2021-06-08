package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.sz.apollo.R;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.views.LoadingWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebAcy extends BasicActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wv_loading)
    LoadingWebView wvLoading;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private String title, url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarDarkFont(true)
                .init();
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {
        tvTitle.setText(title);
        ivBack.setImageDrawable(getDrawable(R.drawable.ic_back_arrow_blue));
        tvTitle.setTextColor(getColor(R.color.color_page_main));
        wvLoading.addProgressBar();
        wvLoading.setOnClickListener(this);
        wvLoading.loadMessageUrl(url);
    }


    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            title = bundle.getString("title");
            url = bundle.getString("url");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wvLoading.destroyWebView();
    }

    /**
     * 按返回键时， 不退出程序而是返回WebView的上一页面
     */
    @Override
    public void onBackPressed() {
        if (wvLoading.canGoBack())
            wvLoading.goBack();
        else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.rl_head)
    public void onViewClicked() {
        if (wvLoading.canGoBack())
            wvLoading.goBack();
        else {
            finish();
        }
    }
}