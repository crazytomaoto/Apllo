package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IGetBackResult;
import com.sz.apollo.ui.models.ContactAddressBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址详情
 */
public class ContactDetailAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ll_transfer)
    LinearLayout llTransfer;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    private long addressId;
    private ContactAddressBean bean;

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            addressId = bundle.getLong("addressId");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.tip106);
        ivRight.setImageResource(R.drawable.ic_edite_ok);
        bean = DaoUtil.selectContactById(addressId);
        if (null != bean) {
            tvAddress.setText(bean.getAddress());
            edName.setHint(bean.getRemark());
        }
    }

    @OnClick({R.id.iv_right, R.id.ll_transfer, R.id.ll_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                String remark = edName.getText().toString().trim();
                if (StringUtils.isEmpty(remark)) {
                    ToastUtil.show(this, getString(R.string.tip101));
                    return;
                } else {
                    bean.setRemark(remark);
                }
                DaoUtil.updateContacts(bean);
                ToastUtil.show(this, getString(R.string.tip102));
                hideInput();
                finish();
                break;
            case R.id.ll_transfer:
                UiHelper.translateApolloAcy(this, true, bean.getAddress(), Constant.TYPE_AOT);
                break;
            case R.id.ll_delete:
                DialogUtil.dialogDeleteContact(this, addressId, new IGetBackResult() {
                    @Override
                    public void getTextString(Object result) {
                        boolean hasDelete = (boolean) result;
                        if (hasDelete) {
                            ToastUtil.show(ContactDetailAcy.this, getString(R.string.tip108));
                            finish();
                        }
                    }
                });
                break;
        }
    }
}
