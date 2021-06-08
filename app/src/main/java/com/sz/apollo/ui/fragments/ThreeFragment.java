package com.sz.apollo.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.LineChartB;
import com.github.mikephil.charting.data.Entry;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicFragment;
import com.sz.apollo.ui.interfaces.IGetBackResult;
import com.sz.apollo.ui.models.WebInfoBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.LineChartManager;
import com.sz.apollo.ui.utils.TimeUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 账本
 */
public class ThreeFragment extends BasicFragment {

    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.tv_account_count)
    TextView tvAccountCount;
    @BindView(R.id.tv_trans_count)
    TextView tvTransCount;
    @BindView(R.id.tv_block_height)
    TextView tvBlockHeight;
    @BindView(R.id.my_webView)
    WebView myWebView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.myRefresh)
    MyRefreshLayout myRefresh;
    @BindView(R.id.chart)
    LineChartB chart;
    private int searchType = 1;
    private ProgressBar mProgressBar;
    //标志位，标志已经初始化完成
    protected boolean isInit = false;
    //是否已被加载过一次，第二次不再去请求数据
    private ArrayList<Entry> entriesXYValuse;
    private LineChartManager lineChartManager1;
    protected String[] XTetValues;

    @Override
    protected void handleStateMessage(Message message) {
        switch (message.what) {
            case GlobalMessageType.RequestCode.QUERY_POWER:

                break;
            case GlobalMessageType.RequestCode.QUERY_NOTICE:

                break;
            case GlobalMessageType.RequestCode.ERROR:

                break;
            case GlobalMessageType.RequestCode.GET_WEB_INFO:
                WebInfoBean webInfoBean = (WebInfoBean) message.obj;
                hud.dismiss();
                myRefresh.complete();
                if (null != webInfoBean) {
                    WebInfoBean.DataBean dataBean = webInfoBean.getData();
                    if (null != dataBean) {
                        tvBlockHeight.setText(dataBean.getHeight() + "");
                        tvAccountCount.setText((dataBean.getAccountNum()) + "");
                        tvTransCount.setText(dataBean.getTransNum() + "");
                        List<WebInfoBean.DataBean.RecentBean> listBeans = dataBean.getRecent();
                        if (null != listBeans && listBeans.size() > 0) {
                            if (listBeans.size() > 1) {
                                Collections.reverse(listBeans);
                                double YValueF;
                                entriesXYValuse = new ArrayList<>();
                                XTetValues = new String[listBeans.size() + 1];
                                for (int i = 0; i < listBeans.size(); i++) {
                                    YValueF = listBeans.get(i).getCount();
                                    entriesXYValuse.add(new Entry(i, (float) YValueF));
                                    XTetValues[i] = listBeans.get(i).getDate();
                                }
                                //展示图表
                                lineChartManager1 = new LineChartManager(chart, getActivity(), getResources().getDrawable(R.drawable.radius_14_gray9));
                                lineChartManager1.showLineChart2(getActivity(), listBeans, "", getResources().getColor(R.color.green3));
                                //设置曲线填充色 以及 MarkerView
                                Drawable drawable = getResources().getDrawable(R.drawable.fade_test);
                                lineChartManager1.setChartFillDrawable(drawable);
                                lineChartManager1.setMarkerView(getActivity());
                            } else {
                                chart.setVisibility(View.GONE);
                            }
                        } else {
                            chart.setVisibility(View.GONE);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 禁止预加载
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
//        if (getUserVisibleHint()) {
//            loadData();
//        }
    }

    protected void loadData() {
        //数据加载成功后
        hud.show();
        iRequest.getWebInfo();
    }

    @Override
    protected void lazyLoad() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_frg_three, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        tvTitle.setText(getText(R.string.t_ledger));
        rlBack.setVisibility(View.GONE);

        isInit = true;
//        isCanLoadData();
//        myRefresh.setEnableLoadmore(false);
//        myRefresh.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                iRequest.getWebInfo();
//            }
//        });
        initWebView();
    }

    private void initWebView() {
//        final Dialog prDialog = new Dialog(WebAcitivity.this, R.style.ActionTopDialogStyle);
//        prDialog.setContentView(R.layout.layout_dialog_loading);

        WebSettings webSettings = myWebView.getSettings();
        // 让WebView能够执行javaScript

        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);//开启DOM
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);
        webSettings.setAppCacheEnabled(false);
        myWebView.setBackgroundColor(0); // 设置背景色
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;// 返回false
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                prDialog.dismiss();
                super.onPageFinished(view, url);
            }
        });
        addProgressBar();
        myWebView.loadUrl(RequestHost.browserUrl);
//        myWebView.addJavascriptInterface(new JavascriptCallBrows(), "androidBrows");//h5吊Android跳转浏览器
//        myWebView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
//                    if (myWebView.canGoBack()) {
//                        myWebView.goBack();
//                        return true;
//                    } else {
//                        return true;
//                    }
//                }
//                return true;
//            }
//        });
    }

    @OnClick({R.id.rl_search, R.id.tv_type, R.id.iv_arrow, R.id.ll_content, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                String content = edContent.getText().toString().trim();
                if (!StringUtils.isEmpty(content)) {
                    switch (searchType) {
                        case 1:
                            if (content.length() < 2 || content.length() > 32) {
                                ToastUtil.show(getActivity(), getString(R.string.tip159));
                                break;
                            } else {
                                UiHelper.startAccountAddressAcy(getActivity(), content);
                            }
                            break;
                        case 2:
                            if (StringUtils.isDigital(content)) {
                                UiHelper.startRecordBlockHeightAcyAcy(getActivity(), content);
                            } else {
                                ToastUtil.show(getActivity(), getString(R.string.tip237));
                            }
                            break;
                        case 3:
                            UiHelper.startRecordTransHashAcy(getActivity(), content);
                            break;
                    }
                } else {
                    ToastUtil.show(getActivity(), getString(R.string.tip192));
                }

                break;
            case R.id.ll_content:
                showPopupWindow(view, result -> {
                    searchType = (int) result;
                    switch (searchType) {
                        case 1:
                            tvType.setText(getString(R.string.tip158));
                            edContent.setHint(getString(R.string.tip159));
                            break;
                        case 2:
                            tvType.setText(getString(R.string.tip162));
                            edContent.setHint(getString(R.string.tip163));
                            break;
                        case 3:
                            tvType.setText(getString(R.string.tip166));
                            edContent.setHint(getString(R.string.tip164));
                            break;
                    }
                });
                break;
            case R.id.rl_back:
                if (myWebView.canGoBack()) {
                    myWebView.goBack();
                }


//                myWebView.clearHistory();
//                myWebView.clearCache(true);
//                myWebView.loadUrl(RequestHost.browserUrl);
//                myWebView.clearCache(true);
//                myWebView.clearHistory();
//                rlBack.setVisibility(View.GONE);
//                myWebView.canGoBack();
                break;
        }
    }

    private void showPopupWindow(View v, IGetBackResult iGetBackResult) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_pop_list, null);
        final PopupWindow popupWindow = new PopupWindow(view, 292, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        TextView tv_address = view.findViewById(R.id.tv_address);
        TextView tv_block_height = view.findViewById(R.id.tv_block_height);
        TextView tv_txHash = view.findViewById(R.id.tv_txHash);
        tv_address.setOnClickListener(v13 -> {
            iGetBackResult.getTextString(1);
            popupWindow.dismiss();
        });
        tv_block_height.setOnClickListener(v1 -> {
            iGetBackResult.getTextString(2);
            popupWindow.dismiss();
        });
        tv_txHash.setOnClickListener(v12 -> {
            iGetBackResult.getTextString(3);
            popupWindow.dismiss();
        });
        popupWindow.showAsDropDown(v, 0, 15, Gravity.BOTTOM);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myWebView.clearCache(true);
        myWebView.clearHistory();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK) {
//                    ToastUtil.show(getActivity(), "按了返回键aaaa");
//                    return true;
//                }
//                return true;
//            }
//        });
//    }

    /**
     * 添加进度条
     */
    public void addProgressBar() {
        mProgressBar = new ProgressBar(getContext(), null,
                android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new AbsoluteLayout.LayoutParams(
                AbsoluteLayout.LayoutParams.MATCH_PARENT, 5, 0, 0));
        mProgressBar.setProgressDrawable(getContext().getResources()
                .getDrawable(R.drawable.bg_pb_web_loading));
        myWebView.addView(mProgressBar);//添加进度条至LoadingWebView中
        myWebView.setWebChromeClient(new WebChromeClient());//设置setWebChromeClient对象
    }


    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
                if (myWebView.canGoBack()) {
                    rlBack.setVisibility(View.VISIBLE);
                } else {
                    rlBack.setVisibility(View.GONE);
                }
            } else {
                if (mProgressBar.getVisibility() == View.GONE)
                    mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

}
