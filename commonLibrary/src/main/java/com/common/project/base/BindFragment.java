package com.common.project.base;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.project.R;
import com.common.project.config.Constants;
import com.common.project.config.EventBean;
import com.common.project.config.EventConstant;
import com.common.project.utils.AppUtils;
import com.common.project.utils.LogUtils;
import com.common.project.utils.SPUtils;
import com.common.project.utils.statusbar.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author by benny
 * @date on 2019/4/27.
 */
public class BindFragment extends Fragment implements View.OnClickListener {

    /**
     * 是否开启沉浸式
     */
    public boolean isImmerse = true;
    /**
     * 状态栏是否亮色 黑色
     */
    public boolean isDark = false;
    private View statusBar;
    Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View bindView = LayoutInflater.from(getActivity()).inflate(bindLayout(), null, false);
        unbinder = ButterKnife.bind(this, bindView);
        try {

            initOnCreate(savedInstanceState);
            initEvent();
            //注册事件
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        } catch (Exception e) {
            LogUtils.d("onCreate() 抛出异常：" + e.getMessage());

        }
        ViewGroup parent = (ViewGroup) bindView.getParent();
        if (parent != null) {
            parent.removeView(bindView);
        }
        return bindView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public int bindLayout() {
        return 0;
    }

    public void initOnCreate(Bundle savedInstanceState) {
        isDark = (boolean) SPUtils.get(Constants.THEME_MODE, false);
    }


    /**
     * 监听事件
     */
    public void initEvent() {
    }


    public void onEmptyRefresh() {

    }


    public void onClickListener(View view) {
        view.setOnClickListener(this);
    }


    /**
     * 刷新数据
     */
    public void onRefreshData() {

    }

    /**
     * 加载更多数据
     */
    public void onLoadMoreData() {

    }


    /**
     * 要改便颜色的控件 获取高度 初始化颜色值
     *
     * @param view view
     */
    public int mSliding = 0;

    public void initConfigScroll(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        mSliding = view.getMeasuredHeight();
        view.getBackground().mutate().setAlpha(0);
    }

    /**
     * 滑动改变空间颜色
     *
     * @param view     view
     * @param scrollY  滑动值
     * @param mSliding 滑动距离
     */
    public void startScrollChanged(View view, int scrollY, int mSliding) {
        //顶部栏透明度控制
        if (scrollY <= 0) {
            //设置标题的背景颜色
            view.setBackgroundColor(Color.argb((int) 0, 67, 50, 102));
        } else if (scrollY > 0 && scrollY <= mSliding) {
            //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) scrollY / mSliding;
            float alpha = (255 * scale);
            view.setBackgroundColor(Color.argb((int) alpha, 67, 50, 102));
        } else {
            //滑动到banner下面设置普通颜色
            view.setBackgroundColor(Color.argb((int) 255, 67, 50, 102));
        }
    }

    /**
     * -----------------------C.界面跳转操作代码·开始-------------------------------------
     */
    public void openActivity(Class<?> cls, Bundle bundle) {
        if (cls != null) {
            Intent intent = new Intent(getActivity(), cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }

    public void openActivity(Class<?> cls, Bundle bundle, int requestCode) {
        if (cls != null) {
            Intent intent = new Intent(getActivity(), cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        }
    }

    public void openFragment(Class<?> cls, Bundle bundle) {
        if (cls != null) {
            Intent intent = new Intent(getActivity(), CommonActivity.class);
            intent.putExtra(Constants.FRAGMENT_CLASS, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }

    public void openFragment(Class<?> cls, Bundle bundle, int requestCode) {
        if (cls != null) {
            Intent intent = new Intent(getActivity(), CommonActivity.class);
            intent.putExtra(Constants.FRAGMENT_CLASS, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        }
    }

    /*
     * -----------------------C.界面跳转操作代码·结束-------------------------------------
     */


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        //取消事件注册
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(EventBean event) {
        if (event.getType() == EventConstant.NO_NET) {
        }
    }


    /**
     * ---------------------------标题栏.start--------------------------------
     */
    public void initToolBar(FrameLayout toolBarGroup) {
        View barView = LayoutInflater.from(getActivity()).inflate(R.layout.common_tool_bar, toolBarGroup, false);
        assignViews(barView);
        if (toolBarGroup != null) {
            toolBarGroup.addView(barView);
        }
    }


    public LinearLayout toolBarRoot;
    public View commonStatusBar;
    public RelativeLayout rlTitleRoot;
    public LinearLayout llToolBarLeft;
    public ImageView ivLeftArrow;
    public TextView tvLeftTitleBar;
    public TextView tvToolBarTitle;
    public LinearLayout llToolBarRight;
    public ImageView ivRightArrow;
    public ImageView ivToolBarLine;
    public TextView tvRightTitleBar;
    public View vShadow;

    private void assignViews(View barView) {
        toolBarRoot = (LinearLayout) barView.findViewById(R.id.tool_bar_root);

        commonStatusBar = barView.findViewById(R.id.common_status_bar);

        rlTitleRoot = (RelativeLayout) barView.findViewById(R.id.rl_title_root);

        llToolBarLeft = (LinearLayout) barView.findViewById(R.id.ll_tool_bar_left);
        ivLeftArrow = (ImageView) barView.findViewById(R.id.iv_left_arrow);
        tvLeftTitleBar = (TextView) barView.findViewById(R.id.tv_left_title_bar);
        llToolBarLeft.setOnClickListener(this);

        tvToolBarTitle = (TextView) barView.findViewById(R.id.tv_tool_bar_title);

        llToolBarRight = (LinearLayout) barView.findViewById(R.id.ll_tool_bar_right);
        ivRightArrow = (ImageView) barView.findViewById(R.id.iv_right_arrow);
        tvRightTitleBar = (TextView) barView.findViewById(R.id.tv_right_title_bar);
        llToolBarRight.setOnClickListener(this);

        ivToolBarLine = (ImageView) barView.findViewById(R.id.iv_tool_bar_line);

        vShadow = barView.findViewById(R.id.v_shadow);
        // initStatusBar(commonStatusBar);
        initTitleBar();
    }

    public void initTitleBar() {

    }

    public void onLeftClick() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }


    public void onRightClick() {

    }

    /**
     * 初始化状态栏
     */
    public void initStatusBar(View commonStatusBar) {
        if (commonStatusBar == null) {
            return;
        }
        statusBar = commonStatusBar;
        if (!isImmerse) {
            //不开启沉浸
            commonStatusBar.setVisibility(View.GONE);
        } else {
            //开启沉浸适配
            if (AppUtils.getSystemVersion() < 19) {
                commonStatusBar.setVisibility(View.GONE);
            } else {
                //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
                //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
                if (isDark) {
//                    if (!StatusBarUtil.setStatusBarDarkTheme(getActivity(), true)) {
//                        //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//                        //这样半透明+白=灰, 状态栏的文字能看得清
//                        StatusBarUtil.setStatusBarColor(getActivity(), 0x55FFFFFF);
//                    }
                } else {
                    StatusBarUtil.setTranslucentStatus(getActivity());
//                    StatusBarUtil.setStatusColor(getActivity(),R.color.ff_to_131);
                }
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) commonStatusBar.getLayoutParams();
                linearParams.height = AppUtils.getStatusBarHeight();
                commonStatusBar.setLayoutParams(linearParams);
                commonStatusBar.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * 设置标题栏 标题
     *
     * @param text
     */
    public void setTitle(String text) {
        if (tvToolBarTitle != null) {
            tvToolBarTitle.setText(text);
        }
    }

    /**
     * 标题栏右边文字
     *
     * @param text
     */
    public void setRight(String text) {
        if (llToolBarRight != null) {
            tvRightTitleBar.setText(text);
            tvRightTitleBar.setVisibility(View.VISIBLE);
            ivRightArrow.setVisibility(View.GONE);
        }
    }

    /**
     * 标题栏右边图标
     *
     * @param resource
     */
    public void setRightImage(int resource) {
        if (llToolBarRight != null) {
            ivRightArrow.setImageResource(resource);
            ivRightArrow.setVisibility(View.VISIBLE);
            tvRightTitleBar.setVisibility(View.GONE);
        }
    }

    /**
     * 标题栏左边文字
     *
     * @param text
     */
    public void setLeft(String text) {
        if (llToolBarLeft != null) {
            tvLeftTitleBar.setText(text);
            tvLeftTitleBar.setVisibility(View.VISIBLE);
            ivLeftArrow.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.iv_left_arrow) {
            onLeftClick();

        } else if (ids == R.id.tv_left_title_bar) {
            onLeftClick();

        } else if (ids == R.id.ll_tool_bar_left) {
            onLeftClick();

        } else if (ids == R.id.tv_tool_bar_title) {
        } else if (ids == R.id.iv_right_arrow) {
            onRightClick();

        } else if (ids == R.id.tv_right_title_bar) {
            onRightClick();

        } else if (ids == R.id.ll_tool_bar_right) {
            onRightClick();

        }
    }

    /**
     * ---------------------------标题栏.end--------------------------------
     */
    public RecyclerView baseRecycler;

    /**
     * 默认 垂直
     *
     * @param recyclerView
     */
    public void setLinearLayoutManager(RecyclerView recyclerView) {
        if (recyclerView == null) {
            LogUtils.e("setRecyclerConfig:baseRecycler==null");
            return;
        }
        baseRecycler = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * @param recyclerView view
     * @param isVertical   是否垂直
     */
    public void setLinearLayoutManager(RecyclerView recyclerView, boolean isVertical) {
        if (recyclerView == null) {
            LogUtils.e("setRecyclerConfig:baseRecycler==null");
            return;
        }
        baseRecycler = recyclerView;
        if (isVertical) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

    /**
     * @param recyclerView view
     * @param row          列数
     */
    public void setGridLayoutManager(RecyclerView recyclerView, int row) {
        if (recyclerView == null) {
            LogUtils.e("setRecyclerConfig:baseRecycler==null");
            return;
        }
        baseRecycler = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), row));
    }


    /************************************************Fragment懒加载*****************************************************/

    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    public boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    public boolean isPrepared;

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    public void onFirstUserVisible() {
    }

    /**
     * fragment可见（切换回来或者onResume）
     */
    public void onUserVisible() {
    }

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    public void onFirstUserInvisible() {
    }

    /**
     * fragment不可见（切换掉或者onPause）
     */
    public void onUserInvisible() {
    }
}

