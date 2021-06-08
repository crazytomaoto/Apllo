package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.AddressStateBean;
import com.sz.apollo.ui.models.FlashRateBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 闪兑
 */
public class FlashExchangeAotAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.tv_now_rate)
    TextView tvNowRate;
    @BindView(R.id.tv_max_exchange)
    TextView tvMaxExchange;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.btn_exchange)
    LinearLayout btn_exchange;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    boolean isActive = false;
    private double rate = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_exchange_aot);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.ADDRESS_STATE:
                AddressStateBean addressStateBean = (AddressStateBean) message.obj;
                if (null != addressStateBean) {
                    isActive = addressStateBean.getData().isEnable();
                    if (isActive) {
                        tvTip.setVisibility(View.GONE);
                        btn_exchange.setEnabled(true);
                    } else {
                        tvTip.setVisibility(View.VISIBLE);
                        btn_exchange.setEnabled(false);
                    }
                }
                break;
            case GlobalMessageType.RequestCode.FLASH_RATE:
                FlashRateBean flashRateBean = (FlashRateBean) message.obj;
                if (null != flashRateBean) {
                    rate = flashRateBean.getData().getRate();
                    tvNowRate.setText("1USDT = " + rate + " "+Constant.TYPE_AOT);
                }
                break;
            case GlobalMessageType.RequestCode.ERROR:
                String error = (String) message.obj;
                if (!StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                break;
        }
    }

    private void initView() {
        tvTitle.setText(R.string.tip77);
        ivRight.setImageResource(R.drawable.ic_exchchang_record);
        if (isActive) {
            tvTip.setVisibility(View.GONE);
            btn_exchange.setEnabled(true);
        } else {
            tvTip.setVisibility(View.VISIBLE);
            btn_exchange.setEnabled(false);
        }
        iRequest.addressState(DaoUtil.selectNowWallet().getAddress());
        iRequest.queryFlashRate(Constant.TYPE_U,Constant.TYPE_AOT);
        edContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isEmpty(s.toString()) || s.toString().equals(".") || Double.parseDouble(s.toString()) < 1) {
                    tvMaxExchange.setText("0 AOT");
                } else {
                    double content = Double.parseDouble(s.toString());
                    tvMaxExchange.setText(Util.getStringFromDecimal(content * rate, 4) + " AOT");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edContent.setFilters(new InputFilter[]{lengthFilter});
    }

    private InputFilter lengthFilter = (source, start, end, dest, dstart, dend) -> {
        // source:当前输入的字符
        // start:输入字符的开始位置
        // end:输入字符的结束位置
        // dest：当前已显示的内容
        // dstart:当前光标开始位置
        // dent:当前光标结束位置
        //Log.e("", "source=" + source + ",start=" + start + ",end=" + end + ",dest=" + dest.toString() + ",dstart=" + dstart + ",dend=" + dend);
        if (dest.length() == 0 && source.equals(".")) {
            return "0.";
        }
        String dValue = dest.toString();
        String[] splitArray = dValue.split("\\.");
        if (splitArray.length > 1) {
            String dotValue = splitArray[1];
            if (dotValue.length() == 4 && dest.length() - dstart < 5) { //输入框小数的位数是2的情况时小数位不可以输入，整数位可以正常输入
                return "";
            }
        }
        return null;
    };

    @OnClick({R.id.btn_exchange, R.id.iv_right})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_exchange:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                String amount = edContent.getText().toString();
                if (StringUtils.isEmpty(amount)) {
                    ToastUtil.show(this, getString(R.string.tip177));
                    return;
                }
                if (Double.parseDouble(amount) < 1) {
                    ToastUtil.show(this, getString(R.string.tip72));
                    return;
                }
                UiHelper.startPaymentAcy(this, amount);
                break;
            case R.id.iv_right:
                if (ClickUtil.isNotFirstClick()) {
                    return;
                }
                UiHelper.startFlashRecordAcy(this,Constant.TYPE_AOT);
                break;
        }

    }

}
