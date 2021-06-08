package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.qrUtil.ScanUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 私钥备份/keyStore备份
 */
public class BackupPKOrKeyAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_qr)
    ImageView ivQr;
    @BindView(R.id.tip)
    TextView tip;
    private int backupType;
    private UserWalletBean userWalletBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_pk_key);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            backupType = bundle.getInt("backupType");
            userWalletBean = (UserWalletBean) bundle.getSerializable("userWalletBean");
        }
    }

    private void initView() {
        if (backupType == 2) {
            tvTitle.setText(R.string.t_back_up_pk);
            tip.setText(R.string.tip31);

            if (userWalletBean.getType().equals(Constant.TYPE_APOLLO)) {
                tvContent.setText("Address:\n" + userWalletBean.getAddress() + "\n\n\n" + "PrivateKey:\n" + userWalletBean.getPk());
            } else {
                tvContent.setText(userWalletBean.getPk());
            }
            ScanUtils.creatQr(userWalletBean.getPk(), ivQr);
        }
        if (backupType == 3) {
            tip.setText(R.string.tip32);
            tvContent.setText(userWalletBean.getKeystore());
            ScanUtils.creatQr(userWalletBean.getKeystore(), ivQr);
        }
    }


    @OnClick(R.id.ll_copy)
    public void onViewClicked() {
        if (backupType == 2) {

            if (userWalletBean.getType().equals(Constant.TYPE_APOLLO)) {
                Util.copy(this, "Address:\n" + userWalletBean.getAddress() + "\n\n\nPrivateKey:\n" + userWalletBean.getPk());
            } else {
                Util.copy(this, userWalletBean.getPk());
            }
            ToastUtil.show(this, getString(R.string.t_copied));
        }
        if (backupType == 3) {
            Util.copy(this, userWalletBean.getKeystore());
            ToastUtil.show(this, getString(R.string.t_copied));
        }
    }
}
