package com.common.project.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.common.project.R;
import com.common.project.utils.LogUtils;
import com.common.project.utils.statusbar.StatusBarUtil;


/**
 * @author by.benny
 * @date 2019/2/12
 */

public class CommonActivity extends BindActivity {

    public static final String FRAGMENT_CLASS = "fragment_class";
    public boolean isImmerse = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try {
           /* //设置状态栏颜色
            if (isImmerse) {
                //设置状态栏颜色
                //StatusBar.showStatusBar(this, R.color.colorStatusBar);
                //设置状态栏透明
                StatusBarUtil.setTranslucentStatus(this);
            }*/
            StatusBarUtil.setStatusColor(this, R.color.ff_to_131);
            setContentView(R.layout.common_base_activity);
        } catch (Exception e) {
            LogUtils.d("onCreate() 抛出异常：" + e.getMessage());

        }

        try {
            Class fragmentClass = (Class) getIntent().getSerializableExtra(FRAGMENT_CLASS);
            if (fragmentClass == null) {
                return;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            fragment = (Fragment) fragmentClass.newInstance();
            if (fragment == null) {
                return;
            }
            transaction.replace(R.id.frame_common, fragment, fragmentClass.getName());
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //qq微信新浪授权防杀死

    }

    protected boolean enableSliding() {
        return true;
    }
}
