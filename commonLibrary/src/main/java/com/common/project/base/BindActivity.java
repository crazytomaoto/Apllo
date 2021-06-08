package com.common.project.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.config.Constants;
import com.common.project.config.EventBean;
import com.common.project.config.EventConstant;
import com.common.project.utils.AppManager;
import com.common.project.utils.KeyBoardUtils;
import com.common.project.utils.LogUtils;
import com.common.project.utils.files.MyFileUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.common.project.R;
import com.common.project.utils.tool.PhotoUtils;

import java.io.File;

import butterknife.ButterKnife;


/**
 * 基类
 * @author by benny
 * @date on 2019/4/27.
 */
public class BindActivity extends AppCompatActivity implements View.OnClickListener {

    /*** 是否开启沉浸式*/
    public boolean isImmerse = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(bindLayout());
            ButterKnife.bind(this);
            initOnCreate(savedInstanceState);
            initViews();
            AppManager.getAppManager().addActivity(this);
        } catch (Exception e) {
            LogUtils.d("onCreate() 抛出异常：" + e.getMessage());
        }
    }

    public int bindLayout() {
        return 0;
    }


    public void initOnCreate(Bundle savedInstanceState) {
        initEvent();
        //注册事件
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void initViews(){

    }
    /**
     * 监听事件
     */
    public void initEvent() {
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
     * -----------------------C.界面跳转操作代码·开始-------------------------------------
     */
    public void openActivity(Class<?> cls, Bundle bundle) {
        if (cls != null) {
            Intent intent = new Intent(this, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }

    public void openActivity(Class<?> cls, Bundle bundle, int requestCode) {
        if (cls != null) {
            Intent intent = new Intent(this, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        }
    }

    public void openFragment(Class<?> cls, Bundle bundle) {
        if (cls != null) {
            Intent intent = new Intent(this, CommonActivity.class);
            intent.putExtra(Constants.FRAGMENT_CLASS, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }

    public void openFragment(Class<?> cls, Bundle bundle, int requestCode) {
        if (cls != null) {
            Intent intent = new Intent(this, CommonActivity.class);
            intent.putExtra(Constants.FRAGMENT_CLASS, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * -----------------------C.界面跳转操作代码·结束-------------------------------------
     */


    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消事件注册
        EventBus.getDefault().unregister(this);
        AppManager.getAppManager().finishActivity(this);

        KeyBoardUtils.close(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(EventBean event) {
        if (event.getType() == EventConstant.NO_NET) {
        }
    }


    /**
     * ---------------------------标题栏.start--------------------------------
     */
    public void initToolBar(ViewGroup toolBarGroup) {
        View barView = LayoutInflater.from(this).inflate(R.layout.common_tool_bar, toolBarGroup, false);
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
        toolBarRoot = barView.findViewById(R.id.tool_bar_root);

        commonStatusBar = barView.findViewById(R.id.common_status_bar);

        rlTitleRoot = barView.findViewById(R.id.rl_title_root);

        llToolBarLeft = barView.findViewById(R.id.ll_tool_bar_left);
        ivLeftArrow = barView.findViewById(R.id.iv_left_arrow);
        tvLeftTitleBar = barView.findViewById(R.id.tv_left_title_bar);
        llToolBarLeft.setOnClickListener(this);

        tvToolBarTitle = barView.findViewById(R.id.tv_tool_bar_title);

        llToolBarRight = barView.findViewById(R.id.ll_tool_bar_right);
        ivRightArrow = barView.findViewById(R.id.iv_right_arrow);
        tvRightTitleBar = barView.findViewById(R.id.tv_right_title_bar);
        llToolBarRight.setOnClickListener(this);

        ivToolBarLine = barView.findViewById(R.id.iv_tool_bar_line);
        vShadow = barView.findViewById(R.id.v_shadow);
        //initStatusBar(commonStatusBar);
        initTitleBar();
    }

    public void initTitleBar() {

    }

    public void onLeftClick() {
        this.finish();
    }


    public void onRightClick() {

    }

    /**
     * 设置标题栏 标题
     *
     * @param text 标题
     */
    public void setTitle(String text) {
        if (tvToolBarTitle != null) {
            tvToolBarTitle.setText(text);
        }
    }

    /**
     * 标题栏右边文字
     *
     * @param text 右边文字
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
     * @param resource 右边图标
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
     * @param text 左边文字
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


    /**
     * 默认 垂直
     *
     * @param recyclerView view
     */
    public void setLinearLayoutManager(RecyclerView recyclerView) {
        if (recyclerView == null) {
            LogUtils.e("setRecyclerConfig:baseRecycler==null");
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    /**
     * 设置recycler直线
     * @param recyclerView view
     */
    public void setRecyclerDivider(RecyclerView recyclerView) {
        if (recyclerView == null) {
            LogUtils.e("setRecyclerConfig:baseRecycler==null");
            return;
        }
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_divider_inset));
        recyclerView.addItemDecoration(itemDecoration);
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
        if (isVertical) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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
        recyclerView.setLayoutManager(new GridLayoutManager(this, row));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //照片选择回调
        onActivityResultImage(requestCode,resultCode,data);
    }
    /**
     * 拍照选择照片返回
     * @param requestCode 请求码
     * @param resultCode 返回结果码
     * @param data 参数
     */
    public void onActivityResultImage(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoUtils.CHOOSE_PHOTO_REQUEST:
                //相册选择返回
                if(RESULT_OK == resultCode) {
                    try {
                        Uri imageUri = data.getData();
                        photoResult(PhotoUtils.getImagePath(this, imageUri));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PhotoUtils.TAKE_PHOTO_REQUEST:
                //拍照返回
                if (resultCode == RESULT_CANCELED) {
                    PhotoUtils.delteImageUri(this, PhotoUtils.imageUri);
                } else {
                    photoResult(PhotoUtils.getImagePath(this, PhotoUtils.imageUri));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 选择相片回调
     */
    public void photoResult(String path) {
        //异常记录 2019年6月5日17:23:39
        //Caused by: java.lang.NullPointerException: println needs a message
        LogUtils.d("选择照片或拍照回调地址------ "+path);

    }

    public void onCompressResult(File file) {

    }

    /**
     * 设置鲁班压缩目录
     *
     * @return 压缩目录
     */
    public String getDir() {
        String dir = MyFileUtil.getExternalStorageDirectory() + "/bitbt/";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}

