package com.common.project.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 绑定Activity,Frgment Layout
 *
 * @author 陈俊山 on 2016/8/30.
 */

public interface BindListener {

    int bindLayout();

    int bindItemLayout();

    /**
     * 初始化操作
     */
    void initOnCreate(@Nullable Bundle savedInstanceState);

    /**
     * 初始化TitleBar
     */
    void initTitleBar();

    /**
     * TitleBar左边按钮的点击事件
     */
    void onLeftClick();

    /**
     * TitleBar右边按钮的点击事件
     */
    void onRightClick();

    /**
     * 没网络点击事件
     */
    void onEmptyRefresh();
}
