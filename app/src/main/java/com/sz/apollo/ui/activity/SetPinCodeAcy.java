package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.language_util.MultiLanguageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sz.apollo.ui.utils.language_util.MultiLanguageUtil.SAVE_LANGUAGE;

/**
 * 设置PIN码
 */
public class SetPinCodeAcy extends BasicActivity {
    @BindView(R.id.ed_pin)
    EditText edPin;
    @BindView(R.id.ed_re_pin)
    EditText edRePin;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.tv_user_know)
    TextView tvUserKnow;
    @BindView(R.id.btn_sure)
    RoundTextView btnSure;
    @BindView(R.id.iv_see_one)
    ImageView ivSeeOne;
    @BindView(R.id.iv_see_two)
    ImageView ivSeeTwo;
    private boolean canSeeOne, canSeeTwo = true;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_set_pin);
        ButterKnife.bind(this);
        btnSure.getDelegate().setBackgroundColor(getColor(R.color.gray5));
        btnSure.setTextColor(getResources().getColor(R.color.gray4));
        btnSure.setEnabled(false);
        cbAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked == false) {
                btnSure.getDelegate().setBackgroundColor(getResources().getColor(R.color.gray5));
                btnSure.setTextColor(getResources().getColor(R.color.gray4));
                btnSure.setEnabled(false);
            } else {
                btnSure.getDelegate().setBackgroundColor(getResources().getColor(R.color.color_copy));
                btnSure.setTextColor(getResources().getColor(R.color.white));
                btnSure.setEnabled(true);
            }
        });
        MultiLanguageUtil.getInstance().updateLanguage(0);
    }

    @OnClick({R.id.iv_see_one, R.id.iv_see_two, R.id.tv_user_know, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_see_one:
                if (canSeeOne) {
                    canSeeOne = false;
                    // 设置为可见
                    edPin.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    edPin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivSeeOne.setImageResource(R.drawable.ic_can_see_gray);
                } else {
                    canSeeOne = true;
                    // 设置为不可见
                    edPin.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    edPin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivSeeOne.setImageResource(R.drawable.icon_can_see_not);
                }
                edPin.setSelection(edPin.getText().toString().trim().length());
                break;
            case R.id.iv_see_two:
                if (canSeeTwo) {
                    canSeeTwo = false;
                    // 设置为可见
                    edRePin.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    edRePin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivSeeTwo.setImageResource(R.drawable.ic_can_see_gray);
                } else {
                    // 设置为不可见
                    canSeeTwo = true;
                    edRePin.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    edRePin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivSeeTwo.setImageResource(R.drawable.icon_can_see_not);
                }
                edRePin.setSelection(edRePin.getText().toString().trim().length());
                break;
            case R.id.tv_user_know:

                break;
            case R.id.btn_sure:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                String pin = edPin.getText().toString().trim();
                String rePin = edRePin.getText().toString().trim();
                if (StringUtils.isEmpty(pin) || pin.length() < 6) {
                    ToastUtil.show(this, getString(R.string.tip136));
                    return;
                }
                if (StringUtils.isEmpty(pin) || pin.length() < 6) {
                    ToastUtil.show(this, getString(R.string.tip137));
                    return;
                }
                if (!pin.equals(rePin)) {
                    ToastUtil.show(this, getString(R.string.tip120));
                    return;
                }
                PlatformConfig.setValue(Constant.SpConstant.WALLET_PASS, pin);
                UiHelper.startChooseTypeAcy(this);
                break;
        }
    }

    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.show(this, getString(R.string.tips_exit));
                exitTime = System.currentTimeMillis();
            } else {
                ApolloApplication.getInstance().exit();
            }
            return true;
        }
        return true;
    }
}
