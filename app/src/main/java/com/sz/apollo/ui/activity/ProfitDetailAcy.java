package com.sz.apollo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterProfitAocDetail;
import com.sz.apollo.ui.adapters.AdapterProfitOtherDetail;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.MineRewardBean;
import com.sz.apollo.ui.models.NboProfitDetailBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;
import com.sz.apollo.ui.views.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收益详情
 */
public class ProfitDetailAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_data)
    ListView lvData;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.refreshLayout)
    MyRefreshLayout refreshLayout;
    @BindView(R.id.ll_page)
    LinearLayout llPage;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    private AdapterProfitAocDetail adapterProfitDeatail;
    private List<MineRewardBean.DataBeanX.DataBean> list, listGet;//aoc收益
    private List<NboProfitDetailBean.DataBeanX.DataBean> listOther, listOtherGet;//nbo收益
    private UserWalletBean userWalletBean;
    private MineRewardBean mineRewardBean;
    private NboProfitDetailBean nboProfitDetail;
    private int pageNumber = 1, pageSize = 15;
    private FingerprintCallback fingerprintCallback;
    private int selectedPosition;
    private String tag = "";
    private static String addmore = "ADDMORE";
    private static String refresh = "REFRESH";
    private String symbol;
    private AdapterProfitOtherDetail adapterProfitOtherDetail;

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.WARD_LIST:
                hud.dismiss();
                mineRewardBean = (MineRewardBean) message.obj;
                listGet = mineRewardBean.getData().getData();
                if (tag.equals(addmore)) {
                    if (listGet.size() == 0) {
                        ToastUtil.show(this, getString(R.string.tip174));
                    } else {
                        list.addAll(listGet);
                    }
                } else {
                    if (null != list && list.size() > 0) {
                        list.clear();
                    }
                    list.addAll(listGet);
                }
                refreshLayout.complete();
                adapterProfitDeatail.setList(list);
                if (list.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    lvData.setVisibility(View.GONE);
                } else {
                    llEmpty.setVisibility(View.GONE);
                    lvData.setVisibility(View.VISIBLE);
                }
                break;
            case GlobalMessageType.RequestCode.OREPOOLDETAILS:
                hud.dismiss();
                refreshLayout.complete();
                nboProfitDetail = (NboProfitDetailBean) message.obj;
                if (nboProfitDetail == null) {
                    llEmpty.setVisibility(View.VISIBLE);
                    lvData.setVisibility(View.GONE);
                    return;
                } else {
                    NboProfitDetailBean.DataBeanX dataBeanXX = nboProfitDetail.getData();
                    if (dataBeanXX == null) {
                        llEmpty.setVisibility(View.VISIBLE);
                        lvData.setVisibility(View.GONE);
                        return;
                    } else {
                        listOtherGet = dataBeanXX.getData();
                    }
                }

                if (tag.equals(addmore)) {
                    if (listOtherGet.size() == 0) {
                        ToastUtil.show(this, getString(R.string.tip174));
                    } else {
                        listOther.addAll(listOtherGet);
                    }
                } else {
                    if (null != listOther && listOther.size() > 0) {
                        listOther.clear();
                    }
                    listOther.addAll(listOtherGet);
                }
                refreshLayout.complete();
                adapterProfitOtherDetail.setList(listOther);
                if (listOther.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    lvData.setVisibility(View.GONE);
                } else {
                    llEmpty.setVisibility(View.GONE);
                    lvData.setVisibility(View.VISIBLE);
                }
                break;
            case GlobalMessageType.RequestCode.WITHDRAW:
                JSONObject jsonObject = (JSONObject) message.obj;
                if (null != jsonObject) {
                    ToastUtil.show(this, jsonObject.getString("message"));
                    iRequest.getRewardList(userWalletBean.getAddress(), pageNumber, pageSize, 0);
                }
                break;
            case GlobalMessageType.RequestCode.WITHDRAWPROFIT:
                JSONObject object = (JSONObject) message.obj;
                if (null != object) {
                    ToastUtil.show(this, object.getString("message"));
                    iRequest.orePoolDetails(symbol, DaoUtil.selectNowWallet().getAddress(), pageNumber, pageSize);
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                String error = (String) message.obj;
                if (!StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                break;
        }
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            symbol = bundle.getString("symbol","");
        }
    }

    /**
     * 需要和getIntentForBundle一起使用
     */
    protected void getIntentForSavedInstanceState(Bundle savedInstanceState) {
        super.getIntentForSavedInstanceState(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            symbol = bundle.getString("symbol","");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_detail);
        ButterKnife.bind(this);
        userWalletBean = DaoUtil.selectNowWallet();
        initView();
        hud.show();
        if (symbol.equals(Constant.TYPE_AOT)) {
            llPage.setBackgroundColor(getResources().getColor(R.color.color_page_main));
        } else {
            llPage.setBackgroundColor(getResources().getColor(R.color.page_color3));
        }
        if (symbol.equals(Constant.TYPE_AOT)) {
            iRequest.getRewardList(userWalletBean.getAddress(), pageNumber, pageSize, 0);
        } else {
            iRequest.orePoolDetails(symbol, userWalletBean.getAddress(), pageNumber, pageSize);
        }
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                tag = addmore;
                pageNumber++;
                if (symbol.equals(Constant.TYPE_AOT)) {
                    iRequest.getRewardList(userWalletBean.getAddress(), pageNumber, pageSize, 0);
                    llPage.setBackgroundColor(getResources().getColor(R.color.color_page_main));
                } else {
                    llPage.setBackgroundColor(getResources().getColor(R.color.page_color3));
                    iRequest.orePoolDetails(symbol, DaoUtil.selectNowWallet().getAddress(), pageNumber, pageSize);
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tag = refresh;
                pageNumber = 1;
                if (symbol.equals(Constant.TYPE_AOT)) {
                    iRequest.getRewardList(userWalletBean.getAddress(), pageNumber, pageSize, 0);
                } else {
                    iRequest.orePoolDetails(symbol, DaoUtil.selectNowWallet().getAddress(), pageNumber, pageSize);
                }
            }
        });


        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                hud.show();
                if (symbol.equals(Constant.TYPE_AOT)) {
                    iRequest.withdraw(list.get(selectedPosition).getUserRewardId());
                } else {
                    iRequest.withdrawProfit(listOther.get(selectedPosition).getId(), symbol);
                }
            }

            @Override
            public void onFailed() {
                ToastUtil.show(ProfitDetailAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(ProfitDetailAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(ProfitDetailAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(ProfitDetailAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfitDetailAcy.this);
                builder.setTitle(getString(R.string.biometricprompt_tip))
                        .setMessage(getString(R.string.biometricprompt_finger_add))
                        .setCancelable(false)
                        .setNegativeButton(getString(R.string.biometricprompt_finger_add_confirm), ((DialogInterface dialog, int which) -> {
                            Intent intent = new Intent(Settings.ACTION_FINGERPRINT_ENROLL);
                            startActivity(intent);
                        }
                        ))
                        .setPositiveButton(getString(R.string.biometricprompt_cancel), ((DialogInterface dialog, int which) -> {
                            dialog.dismiss();
                        }
                        ))
                        .create().show();
            }
        };
    }

    private void initView() {
        tvTitle.setText(R.string.tip58);
        initLoading(this);
        adapterProfitDeatail = new AdapterProfitAocDetail(this);
        adapterProfitOtherDetail = new AdapterProfitOtherDetail(this, symbol);
        list = new ArrayList<>();
        listGet = new ArrayList<>();
        listOther = new ArrayList<>();
        listOtherGet = new ArrayList<>();
        if (symbol.equals(Constant.TYPE_AOT)) {
            lvData.setAdapter(adapterProfitDeatail);
            llPage.setBackgroundColor(getResources().getColor(R.color.color_page_main));
            ivEmpty.setImageResource(R.drawable.ic_empty);
            tvEmpty.setTextColor(getResources().getColor(R.color.gray));
        }
        if (symbol.equals(Constant.TYPE_NBO)) {
            lvData.setAdapter(adapterProfitOtherDetail);
            llPage.setBackgroundColor(getResources().getColor(R.color.page_color3));
            ivEmpty.setImageResource(R.drawable.ic_empty_nbo);
            tvEmpty.setTextColor(getResources().getColor(R.color.color_purple5));
        }
        if (symbol.equals(Constant.TYPE_DDAO)) {
            lvData.setAdapter(adapterProfitOtherDetail);
            llPage.setBackgroundColor(getResources().getColor(R.color.page_color3));
            ivEmpty.setImageResource(R.drawable.ic_empty_ddao);
            tvEmpty.setTextColor(getResources().getColor(R.color.color_purple5));
        }
        if (symbol.equals(Constant.TYPE_LON)) {
            lvData.setAdapter(adapterProfitOtherDetail);
            llPage.setBackgroundColor(getResources().getColor(R.color.page_color3));
            ivEmpty.setImageResource(R.drawable.ic_empty_lon);
            tvEmpty.setTextColor(getResources().getColor(R.color.color_purple5));
        }
        adapterProfitDeatail.setList(list);
        adapterProfitDeatail.setiGetProfit(position -> {
            selectedPosition = position;
            if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(ProfitDetailAcy.this);
                builder.callback(fingerprintCallback)
                        .fingerprintColor(ContextCompat.getColor(ProfitDetailAcy.this, R.color.colorPrimary))
                        .build();
            } else {
                DialogUtil.dialogPwd(ProfitDetailAcy.this, new IPassGetInput() {
                    @Override
                    public void getTextString(String pass) {
                        if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                            hud.show();
                            iRequest.withdraw(list.get(selectedPosition).getUserRewardId());
                        } else {
                            ToastUtil.show(ProfitDetailAcy.this, getString(R.string.error_pwd));
                        }
                    }

                    @Override
                    public void cancle() {
                    }
                });

            }
        });
        adapterProfitOtherDetail.setiGetProfit(new AdapterProfitOtherDetail.IGetProfit() {
            @Override
            public void getProfit(int position) {
                selectedPosition = position;
                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(ProfitDetailAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(ProfitDetailAcy.this, R.color.colorPrimary))
                            .build();
                } else {
                    DialogUtil.dialogPwd(ProfitDetailAcy.this, new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {
                            if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                hud.show();
                                iRequest.withdrawProfit(listOther.get(selectedPosition).getId(), symbol);
                            } else {
                                ToastUtil.show(ProfitDetailAcy.this, getString(R.string.error_pwd));
                            }
                        }

                        @Override
                        public void cancle() {
                        }
                    });

                }
            }
        });
    }
}