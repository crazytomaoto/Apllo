package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.ContactAddressBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.IndicSyllabicCategory.NUMBER;

/**
 * 新建地址簿
 */
public class AddAddressBookAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.ed_remark)
    EditText edRemark;
    @BindView(R.id.btn_sure)
    RoundTextView btnSure;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
        tvTitle.setText(R.string.tip99);
        edAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edAddress.getText().toString().trim().equals(Constant.NEXADDRESS)) {
                    edRemark.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                } else {
                    edRemark.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    @OnClick(R.id.btn_sure)
    public void onViewClicked() {
        if (ClickUtil.isNotFirstClick()) {
            return;
        }
        String address = edAddress.getText().toString().trim();
        String remark = edRemark.getText().toString().trim();

        if (StringUtils.isEmpty(address)) {
            ToastUtil.show(this, getString(R.string.tip100));
            return;
        }
        if (StringUtils.isEmpty(remark)) {
            ToastUtil.show(this, getString(R.string.tip101));
            return;
        }
        if (DaoUtil.isContactSaved(address)) {
            ToastUtil.show(this, getString(R.string.tup102));
            return;
        }

        if (address.equals(Constant.NEXADDRESS)) {
            if (StringUtils.isEmpty(remark)) {
                ToastUtil.show(this, getString(R.string.tip183));
                return;
            }
            if (StringUtils.isDigital(remark) == false) {
                ToastUtil.show(this, getString(R.string.tip210));
                return;
            }
            if (remark.length() != 5 && remark.length() != 6 && remark.length() != 7) {
                ToastUtil.show(this, getString(R.string.tip210));
                return;
            }
        }

        ContactAddressBean bean = new ContactAddressBean();
        bean.setAddress(address);
        bean.setRemark(remark);
        DaoUtil.addNewContact(bean);
        hideInput();
        ToastUtil.show(this, getString(R.string.tip103));
        finish();
    }
}
