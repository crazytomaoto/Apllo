package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.gyf.barlibrary.ImmersionBar;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.ImportApolloBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.crypto.EccTool;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 导入 阿波罗钱包
 */
public class ImportApolloAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.ed_pk)
    EditText edPk;
    @BindView(R.id.btn_create)
    Button btnCreate;
    private UserWalletBean userWalletBean;
    private String addressEd, pkEd, publicKey;

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.GETACCOUNTS:
                ImportApolloBean importApolloBean = (ImportApolloBean) message.obj;
                if (null != importApolloBean) {
                    List<String> list = importApolloBean.getData().getAccount_names();
                    handleWallet(list.contains(edAddress.getText().toString().trim()));
                } else {
                    hud.dismiss();
                    ToastUtil.show(this, getString(R.string.tip152));
                }
                break;
            case GlobalMessageType.RequestCode.IMPORT_ACCOUNT:
                JSONObject object = (JSONObject) message.obj;
                if (object != null) {
                    hud.dismiss();
                    if (object.getInteger("code") != 200) {
                        ToastUtil.show(this, object.getString("message"));
                    } else {
                        //更新选中钱包
                        UserWalletBean nowWallet = DaoUtil.selectNowWallet();
                        if (null != nowWallet) {
                            nowWallet.setIsNowWallet("0");
                            DaoUtil.updateWallet(nowWallet);
                        }
                        userWalletBean = new UserWalletBean();
                        userWalletBean.setAddress(addressEd);
                        userWalletBean.setIsBackedUp("1");
                        userWalletBean.setIsNowWallet("1");
                        userWalletBean.setPk(pkEd);
                        userWalletBean.setPublicKey(publicKey);
                        userWalletBean.setType(Constant.TYPE_APOLLO);
                        userWalletBean.setRemark(Constant.TYPE_APOLLO);
                        userWalletBean.setPhrase("");
                        userWalletBean.setKeystore("");
                        userWalletBean.setWalletExistWay("b");
                        userWalletBean.setIsAddUSDT("0");
                        DaoUtil.addWallet(userWalletBean);
                        ApolloApplication.getInstance().finishAllActivity();
                        UiHelper.startHomepageActivity(this);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.ACCOUNT_IS_EXIT:
                boolean isExist = (boolean) message.obj;
                handleWallet(isExist);
                break;
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                String error = (String) message.obj;
                ToastUtil.show(this, error);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_apl);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarColor(R.color.color_page_main)//这里的颜色，你可以自定义。
                .init();
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.t_import_account);
        initLoading(this);
    }

    @OnClick(R.id.btn_create)
    public void onViewClicked() {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        addressEd = edAddress.getText().toString().trim();
        pkEd = edPk.getText().toString().trim();
        if (StringUtils.isEmpty(addressEd)) {
            ToastUtil.show(this, getString(R.string.tip150));
            return;
        }
        if (StringUtils.isEmpty(pkEd)) {
            ToastUtil.show(this, getString(R.string.tip26));
            return;
        }

        try {
            publicKey = EccTool.privateToPublic(pkEd);
            if (!StringUtils.isEmpty(publicKey)) {
                hud.show();
                iRequest.checkPublicKeyIsExist(publicKey, addressEd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.show(this, getString(R.string.tip149));
            edPk.setText("");
        }
    }

    private void handleWallet(boolean isAccountExist) {
        if (isAccountExist) {
            UserWalletBean checkBean = DaoUtil.selectWalletByAddress(addressEd);
            if (null != checkBean) {
                hud.dismiss();
                ToastUtil.show(this, getString(R.string.tip151));
                return;
            }
            iRequest.importAccount(publicKey, pkEd, Util.getDeviceOnlyNum(this), addressEd);
        } else {
            hud.dismiss();
            ToastUtil.show(this, getString(R.string.tip152));
        }
    }
}
