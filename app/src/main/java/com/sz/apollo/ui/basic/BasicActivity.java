package com.sz.apollo.ui.basic;

import android.app.Dialog;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.hysd.android.platform_ub.base.manager.LogicFactory;
import com.hysd.android.platform_ub.base.manager.MessageCenter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.base.BaseActivity;
import com.sz.apollo.ui.logics.IRequest;
import com.sz.apollo.ui.receivers.NetBroadcastReceiver;
import com.sz.apollo.ui.utils.ActivityManager;
import com.sz.apollo.ui.utils.language_util.MultiLanguageUtil;

import java.util.Set;

/**
 * Created by admin on 2018/4/2.
 */
public abstract class BasicActivity extends BaseActivity implements View.OnClickListener, NetBroadcastReceiver.NetChangeListener {

    private Handler mHandler;
    public static NetBroadcastReceiver.NetChangeListener listener;
    private NetBroadcastReceiver netBroadcastReceiver;
    private Dialog noNet;
    private static Dialog dialog;
    private static BasicActivity context;
    protected static IRequest iRequest;
    protected KProgressHUD hud;

    /**
     * 网络类型
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ImmersionBar.with(this)
                .statusBarDarkFont(false).init();
        super.onCreate(savedInstanceState);
        try {
            inits(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(context));
    }

    public void inits(Bundle savedInstanceState) {
        context = this;
        ApolloApplication.getInstance().addActivity(this);//应用退出
        if (null != savedInstanceState) {
            getIntentForSavedInstanceState(savedInstanceState);
        } else {
            getIntentForBundle();
        }
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        initLogics();
        MessageCenter.getInstance().addHandler(getHandler());
        listener = this;
        //Android 7.0以上需要动态注册
        //实例化IntentFilter对象
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        netBroadcastReceiver = new NetBroadcastReceiver();
        //注册广播接收
        registerReceiver(netBroadcastReceiver, filter);

        initNetDialog();


    }

    private void initNetDialog() {
        dialog = new Dialog(this, R.style.dialog_tips);
        Window dialogWindow = dialog.getWindow();
        dialog.setContentView(R.layout.layout_net_error);
        //改变蒙版的透明度
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.dimAmount = 0.2f;
//        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(false);//是否可以取消dialog
        int mWidth, height;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
            height = dm.heightPixels;
        } else {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
            height = metrics.heightPixels;
        }
        dialogWindow.setLayout(mWidth, height);
    }


    public void initLoading(Context context) {
        hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setBackgroundColor(getResources().getColor(R.color.dialog_color))
                .setAnimationSpeed(2)
                .setSize(60, 60);
    }

    public void onChangeListener(int status) {
        if (status == -1) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    public void dismissDialog() {
        if (null != noNet && noNet.isShowing()) {
            noNet.dismiss();
        }
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
//        ImmersionBar.with(this).statusBarDarkFont(false).init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 消息处理
     */
    protected void handleStateMessage(Message message) {
        switch (message.what) {
//            case GlobalMessageType.RequestCode.LOGIN_AGAIN:
////                UiHelper.startLoginActivity(this);
//                break;
        }
    }

    /**
     * logic
     */
    protected void initLogics() {
        iRequest = LogicFactory.getLogicByClass(IRequest.class);
    }

    /**
     * handler
     */
    protected Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    handleStateMessage(msg);
//                    handleKickOutUnline(msg);
                }
            };
        }
        return mHandler;
    }


    /**
     * 被IM踢出 的方法
     *
     * @param message
     */
    private void handleKickOutUnline(Message message) {
        switch (message.what) {

        }

        if (message.what == GlobalMessageType.CommonMessageType.USER_TOKEN_EXPIRE) {
//            myDialog = new DialogLogoutView(this, itemsOnClick);
//            myDialog.setContent("您的账号已在别的设备上登录，若不是本人操作，请及时检查账号密码是否泄露！");
//            myDialog.setDialogCallback(dialogcallback);
//            myDialog.show()
        }
    }

    protected void sendMessage(Message message) {
        getHandler().sendMessage(message);
    }

    protected void sendMessage(int what, Object obj) {
        Message message = new Message();
        message.what = what;
        message.obj = obj;
        getHandler().sendMessage(message);
    }

    protected void sendEmptyMessage(int what) {
        getHandler().sendEmptyMessage(what);
    }

    protected void sendEmptyMessageDelayed(int what, long delayMillis) {
        getHandler().sendEmptyMessageDelayed(what, delayMillis);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        // 移除回收的handler
        MessageCenter.getInstance().removeHandler(getHandler());
        if (isImmersionBarEnabled()) {
//            ImmersionBar.with(this).destroy();
        }
        super.onDestroy();
        unregisterReceiver(netBroadcastReceiver);
    }

    /**
     * 需要和getIntentForBundle一起使用
     */
    protected void getIntentForSavedInstanceState(Bundle savedInstanceState) {

    }

    /**
     * 需要和getIntentForSavedInstanceState一起使用
     */
    protected void getIntentForBundle() {

    }

    protected void onSaveIntent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                Set<String> keySet = bundle.keySet();
                for (String key : keySet) {
                    Object value = bundle.get(key);
                    if (value != null) {
                        if (value instanceof String) {
                            savedInstanceState.putString(key, (String) value);
                        } else if (value instanceof Integer) {
                            savedInstanceState.putInt(key, (Integer) value);
                        } else if (value instanceof Boolean) {
                            savedInstanceState.putBoolean(key, (Boolean) value);
                        } else if (value instanceof Float) {
                            savedInstanceState.putFloat(key, (Float) value);
                        } else if (value instanceof Long) {
                            savedInstanceState.putLong(key, (Long) value);
                        } else {
                            savedInstanceState.putString(key, value.toString());
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        onSaveIntent(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
            super.onRestoreInstanceState(savedInstanceState);
        } catch (Exception e) {

        }
        getIntentForSavedInstanceState(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        ActivityManager.getInstance().setCurrentActivity(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
