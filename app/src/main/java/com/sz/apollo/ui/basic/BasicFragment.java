package com.sz.apollo.ui.basic;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hysd.android.platform_ub.base.manager.LogicFactory;
import com.hysd.android.platform_ub.base.ui.BaseFragment;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.logics.IRequest;

public abstract class BasicFragment extends BaseFragment implements View.OnClickListener {
    private int positions;
    protected static KProgressHUD hud;
    protected static IRequest iRequest;
    protected boolean isVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLogics();
        initLoading(getActivity());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 消息处理
     */
    protected void handleStateMessage(Message message) {
        switch (message.what) {
            case GlobalMessageType.RequestCode.GETETHBALANCE:

//                UiHelper.startLoginActivity(getActivity());
                break;
//            case GlobalMessageType.RequestCode.ERROR:
////                DialogUtil.showToastError(getActivity(), (String) (message.obj));
//                break;
        }

    }

    //     * logic
//     */
//    protected abstract void initLogics();
    public void initLoading(Context context) {
        hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setBackgroundColor(getResources().getColor(R.color.loading_color))
                .setAnimationSpeed(2)
                .setSize(60, 60);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * logic
     */
    protected void initLogics() {
        iRequest = LogicFactory.getLogicByClass(IRequest.class);
    }

    @Override
    public void onResume() {
        super.onResume();
//        EventBus.getDefault().register(this);
//        MultiLanguageUtil.getInstance().setConfiguration();
    }

    @Override
    public void onPause() {
        super.onPause();
//        EventBus.getDefault().unregister(this);
    }

    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

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
}
