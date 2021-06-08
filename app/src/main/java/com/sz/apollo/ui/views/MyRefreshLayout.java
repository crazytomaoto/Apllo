package com.sz.apollo.ui.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.sz.apollo.R;

/**
 * Date:2019/12/7
 * Time:10:16
 * author:andmin
 * describe:
 */
public class MyRefreshLayout extends SmartRefreshLayout {
    public static void init() {
        // 指定全局的下拉Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> new PullDownHeader(context));

        // 指定全局的上拉Footer
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> new PullUpFooter(context));
    }

    public MyRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setReboundDuration(100); // 设置回弹动画时长
        setPrimaryColorsId(R.color.color_page_main);  // 主题色
        setEnableAutoLoadmore(false); // 设置是否监听列表在滚动到底部时触发加载事件

    }

    // 下拉/上拉完成
    public void complete() {
        if (mState == RefreshState.Loading) {
            finishLoadmore();
        } else {
            finishRefresh();
        }
    }
}