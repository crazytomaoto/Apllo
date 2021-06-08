package com.sz.apollo.ui.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hysd.android.platform_ub.base.manager.MessageCenter;
import com.sz.apollo.ui.interfaces.DialogControl;
import com.sz.apollo.ui.utils.SharedPreferencesUtils;
import com.zhy.autolayout.AutoLayoutActivity;


/**
 * BaseActivity
 * Created by wty on 2018/4/2.
 */
public class BaseActivity extends AutoLayoutActivity implements DialogControl {
    public static final int TIME_OUT_INT = 15 * 1000;
    public static SharedPreferencesUtils spUtils;
    private AnimationDrawable animationDrawable;
    protected static View mTipView;

    /**
     * 返回键
     *
     * @param v view
     */
    public void back(View v) {
        this.finish();
        hideInput();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = new SharedPreferencesUtils(this, "user_info.spf");
        _isVisible = true;
    }

    /**
     * 检查当前网络是否可用
     *
     * @param activity
     * @return
     */

    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            @SuppressLint("MissingPermission") NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * show loading progress dialog
     */
    public void showDialog() {
//        mProgressDialog = new CustomProgressDialog(this, R.layout.progress_dialog, R.style.Theme_dialog);
////        ImageView iv_voice_animation = (ImageView) mProgressDialog.findViewById(R.id.iv_voice_animation);
////        iv_voice_animation.setImageResource(R.drawable.load_animation);
////        animationDrawable = (AnimationDrawable) iv_voice_animation.getDrawable();
////        animationDrawable.start();
//        mProgressDialog.setCancelable(false);
//        mProgressDialog.show();
//        mProgressDialog.setOnKeyListener(onKeyListener);

        //5秒后自动关闭
        MessageCenter messageCenter = MessageCenter.getInstance();
        messageCenter.sendEmptyMesageDelay(100, 5000);
    }

    /**
     * add z_bg_home3 key listener for progress dialog
     */
    private DialogInterface.OnKeyListener onKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                dismissDialog();
            }
            return false;
        }
    };

    /**
     * 键盘弹出
     *
     * @param editText
     */
    public void showSoftInputFromWindow(EditText editText) {
        editText.setFocusable(true);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
    }

    /**
     * 隐藏软键盘
     */
    public void hideInput() {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null != BaseActivity.this.getCurrentFocus())
                inputMethodManager.hideSoftInputFromWindow(
                        BaseActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
    }

    private boolean _isVisible;


    /**
     * dismiss dialog
     */
    public void dismissDialog() {
    }

    @Override
    public void hideWaitDialog() {

    }
}