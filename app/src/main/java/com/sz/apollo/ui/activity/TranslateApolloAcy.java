package com.sz.apollo.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.client.android.utils.ScanUtils;
import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.commons.interfaces.GlobalMessageType;
import com.sz.apollo.ui.adapters.AdapterTransPage;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.interfaces.IGetBackResult;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.ContactAddressBean;
import com.sz.apollo.ui.models.TransApolloBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.DialogUtil;
import com.sz.apollo.ui.utils.PermissionUtils;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.utils.UiHelper;
import com.sz.apollo.ui.utils.Util;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintCallback;
import com.sz.apollo.ui.utils.fingerUtil.FingerprintVerifyManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 转账页面
 */
public class TranslateApolloAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_nowAddress)
    TextView tvNowAddress;
    @BindView(R.id.iv_left_arrow)
    ImageView ivLeftArrow;
    @BindView(R.id.tv_nowType)
    TextView tvNowType;
    @BindView(R.id.iv_right_arrow)
    ImageView ivRightArrow;
    @BindView(R.id.edit_receive_address)
    EditText editReceiveAddress;
    @BindView(R.id.iv_contract)
    ImageView ivContract;
    @BindView(R.id.edit_tran_amount)
    EditText editTranAmount;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.tv_available)
    TextView tvAvailable;
    @BindView(R.id.ed_sign)
    EditText edSign;
    private String contactAddress;//地址
    private boolean isFromContactBook;
    private UserWalletBean userWalletBean;
    private String balance, transType;
    private BalanceBean balanceBean, balanceAotBean;
    private IGetBackResult iGetBackResult;
    private int popType;//1 左边  2 右边
    private double gasRate = 0.006;
    private double gas = 0;
    private List<BalanceBean> listBalance;
    private FingerprintCallback fingerprintCallback;
    private String toAddress, quantity, memo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_apollo);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void handleStateMessage(Message message) {
        super.handleStateMessage(message);
        switch (message.what) {
            case GlobalMessageType.RequestCode.ERROR:
                hud.dismiss();
                String error = (String) message.obj;
                if (!StringUtils.isEmpty(error)) {
                    ToastUtil.show(this, error);
                }
                break;
            case GlobalMessageType.RequestCode.SENDEOS:
                TransApolloBean transApolloBean = (TransApolloBean) message.obj;
                if (null != transApolloBean && transApolloBean.getCode() == 200) {
                    hud.dismiss();
                    ToastUtil.show(this, transApolloBean.getMessage());
                    if (!DaoUtil.isContactSaved(toAddress)) {
                        DialogUtil.dialogSaveNewContact(TranslateApolloAcy.this, new IPassGetInput() {
                            @Override
                            public void getTextString(String pass) {
                                if (!StringUtils.isEmpty(pass)) {
                                    ContactAddressBean bean = new ContactAddressBean();
                                    bean.setAddress(toAddress);
                                    if (toAddress.equals(Constant.NEXADDRESS)) {
                                        bean.setRemark(memo);
                                    } else {
                                        bean.setRemark(getString(R.string.tip209) + "--" + toAddress);
                                    }
                                    DaoUtil.addNewContact(bean);
                                    hideInput();
                                    ToastUtil.show(TranslateApolloAcy.this, getString(R.string.tip103));
                                }
                            }

                            @Override
                            public void cancle() {

                            }
                        });
                    }
                    String transHash = transApolloBean.getData();
                    UiHelper.startTransDetailOneAcy(this, transHash, transType);
                    finish();
                }
                break;
            case GlobalMessageType.RequestCode.APOLLO_ONE_KIND_BALANCE:
                JSONObject jsonObject = (JSONObject) message.obj;
                balanceBean = new BalanceBean();
                if (null != jsonObject) {
                    Map<String, String> map = Util.json2map(jsonObject.getString("data"));
                    /**
                     * 当前币
                     */
                    if (!transType.equals(Constant.TYPE_AOC)) {
                        balanceBean.setType(transType);
                        balanceBean.setBalance(map.get(transType));
                        tvAvailable.setText(balanceBean.getBalance() + transType);
                        hud.dismiss();
                    } else {
                        /**
                         * AOC
                         */
                        balanceBean.setType(transType);
                        balanceBean.setBalance(map.get(transType));
                        tvAvailable.setText(balanceBean.getBalance() + transType);
                        /**
                         * AOT
                         */
                        balanceAotBean = new BalanceBean();
                        balanceAotBean.setType(Constant.TYPE_AOT);
                        balanceAotBean.setBalance(map.get(Constant.TYPE_AOT));
                        hud.dismiss();
                    }
                }
                break;
        }

    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            contactAddress = bundle.getString("contactAddress");
            isFromContactBook = bundle.getBoolean("isFromContactBook");
            transType = bundle.getString("transType");
        }
    }

    private void initView() {
        tvTitle.setText(R.string.t_transfer);
        listBalance = new ArrayList<>();
        ivRight.setImageResource(R.drawable.ic_scan);
        if (isFromContactBook) {
            editReceiveAddress.setText(contactAddress);
            transType = Constant.TYPE_AOT;
        }
        userWalletBean = DaoUtil.selectNowWallet();
        getBalanceToken();
        editReceiveAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editReceiveAddress.getText().toString().trim().equals(Constant.NEXADDRESS)) {
                    edSign.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                } else {
                    edSign.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                }
            }
        });
        tvNowAddress.setText(userWalletBean.getAddress());
        tvNowType.setText(transType);
        initLoading(this);
        iGetBackResult = result -> {
            if (popType == 1) {
                userWalletBean = DaoUtil.selectWalletByAddress((String) result);
                tvNowAddress.setText(userWalletBean.getAddress());
            }
            if (popType == 2) {
                transType = (String) result;
                tvNowType.setText(transType);
            }
            getBalanceToken();
        };
        editTranAmount.setFilters(new InputFilter[]{lengthFilter});
        editTranAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String amount = s.toString().trim();
                if (!StringUtils.isEmpty(amount)) {
                    gas = Double.parseDouble(amount) * gasRate;
                } else {
                    gas = 0;
                }
                if (transType.equals(Constant.TYPE_AOC)) {
                    tvFee.setText(Util.getStringFromDecimal(gas, 4) + " " + Constant.TYPE_AOT);
                } else {
                    tvFee.setText(Util.getStringFromDecimal(gas, 4) + " " + transType);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                hud.show();
                iRequest.tranApollo(
                        userWalletBean.getPk(),
                        Util.getDeviceOnlyNum(TranslateApolloAcy.this),
                        userWalletBean.getAddress(),
                        toAddress,
                        Double.parseDouble(quantity),
                        transType,
                        memo);
            }

            @Override
            public void onFailed() {
                ToastUtil.show(TranslateApolloAcy.this, getString(R.string.biometricprompt_verify_failed));
            }

            @Override
            public void onUsepwd() {
                ToastUtil.show(TranslateApolloAcy.this, getString(R.string.fingerprint_usepwd));
            }

            @Override
            public void onCancel() {
                ToastUtil.show(TranslateApolloAcy.this, getString(R.string.fingerprint_cancel));
            }

            @Override
            public void onHwUnavailable() {
                ToastUtil.show(TranslateApolloAcy.this, getString(R.string.biometricprompt_finger_hw_unavailable));
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder builder = new AlertDialog.Builder(TranslateApolloAcy.this);
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

    public void getBalanceToken() {
        if (transType.equals(Constant.TYPE_AOC)) {
            iRequest.getBalance(userWalletBean.getAddress(), Constant.TYPE_AOT + "," + transType);
        } else {
            iRequest.getBalance(userWalletBean.getAddress(), transType);
        }
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
//                if (dotValue.length() == 2) {//输入框小数的位数是2的情况，整个输入框都不允许输入
//                    return "";
//                }
            if (dotValue.length() == 4 && dest.length() - dstart < 5) { //输入框小数的位数是2的情况时小数位不可以输入，整数位可以正常输入
                return "";
            }
        }
        return null;
    };

    @SuppressLint("NewApi")
    @OnClick({R.id.iv_right, R.id.ll_right, R.id.iv_right_arrow, R.id.ll_left, R.id.iv_contract, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                String[] permissions = PermissionUtils.getCamera();
                if (PermissionUtils.checkPermissions(this, permissions)) {
                    requestPermissions(permissions, PermissionUtils.REQUEST_CODE);
                } else {
                    UiHelper.startScanAcy(this);
                }
                break;
            case R.id.ll_left:
                popType = 1;
                showPopupWindow(view, userWalletBean.getAddress(), popType, iGetBackResult);
                break;
            case R.id.ll_right:
                popType = 2;
                showPopupWindow(view, transType, popType, iGetBackResult);
                break;
            case R.id.iv_contract:
                contactAddress = editReceiveAddress.getText().toString().trim();
                DialogUtil.dialogContacts(this, contactAddress, new IGetBackResult() {
                    @Override
                    public void getTextString(Object result) {
                        contactAddress = (String) result;
                        editReceiveAddress.setText(contactAddress);
                    }
                });
                break;
            case R.id.btn_sure:
                toAddress = editReceiveAddress.getText().toString().trim();
                quantity = editTranAmount.getText().toString().trim();
                memo = edSign.getText().toString().trim();
                if (StringUtils.isEmpty(toAddress)) {
                    ToastUtil.show(this, getString(R.string.tip170));
                    return;
                }

                if (toAddress.equals(userWalletBean.getAddress())) {
                    ToastUtil.show(this, getString(R.string.tip176));
                    return;
                }
                if (toAddress.equals(Constant.NEXADDRESS)) {
                    if (StringUtils.isEmpty(memo)) {
                        ToastUtil.show(this, getString(R.string.tip183));
                        return;
                    }
                    if (StringUtils.isDigital(memo) == false) {
                        ToastUtil.show(this, getString(R.string.tip210));
                        return;
                    }
                    if (memo.length() != 5 && memo.length() != 6 && memo.length() != 7) {
                        ToastUtil.show(this, getString(R.string.tip210));
                        return;
                    }

                }

                if (StringUtils.isEmpty(quantity)) {
                    ToastUtil.show(this, getString(R.string.tip33));
                    return;
                }

                double quantityDouble = Double.parseDouble(quantity);
                double gasDouble = Double.parseDouble(Util.getStringFromDecimal(gas, 4));
                double balance = Double.parseDouble(balanceBean.getBalance());
                double balanceAot = 0;
                if (transType.equals(Constant.TYPE_AOC)) {
                    balanceAot = Double.parseDouble(balanceAotBean.getBalance());
                }
                //要转的数量==0
                if (balance <= 0) {
                    ToastUtil.show(this, getString(R.string.tip153));
                    return;
                }
                //转账类型不是AOC时
                if (!transType.equals(Constant.TYPE_AOC)) {
                    //手续费>余额
                    if (gas > balance) {
                        ToastUtil.show(this, getString(R.string.tip153));
                        return;
                    }
                    //转账数量>余额
                    if (quantityDouble >= balance) {
                        ToastUtil.show(this, getString(R.string.tip153));
                        return;
                    }
                    //转账数量+手续费>余额
                    if ((quantityDouble + gasDouble) >= balance) {
                        ToastUtil.show(this, getString(R.string.tip153));
                        return;
                    }

                } else {//转账类型不是AOC时，因为扣除手续费是AOT，所有要判断AOT和AOC
                    //aot不足
                    if (balanceAot <= 0) {
                        ToastUtil.show(this, getString(R.string.tip153));
                        return;
                    }
                    //手续费大于AOT余额
                    if (gas > balanceAot) {
                        ToastUtil.show(this, getString(R.string.tip153));
                        return;
                    }
                    //转账AOC数量大于AOC余额
                    if (quantityDouble > balance) {
                        ToastUtil.show(this, getString(R.string.tip153));
                        return;
                    }
                }

                if (PlatformConfig.getBoolean(Constant.SpConstant.FINGER_PAY)) {
                    FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(TranslateApolloAcy.this);
                    builder.callback(fingerprintCallback)
                            .fingerprintColor(ContextCompat.getColor(TranslateApolloAcy.this, R.color.colorPrimary))
                            .build();
                } else {
                    DialogUtil.dialogPwd(this, new IPassGetInput() {
                        @Override
                        public void getTextString(String pass) {
                            if (pass.equals(PlatformConfig.getString(Constant.SpConstant.WALLET_PASS))) {
                                hud.show();
                                iRequest.tranApollo(
                                        userWalletBean.getPk(),
                                        Util.getDeviceOnlyNum(TranslateApolloAcy.this),
                                        userWalletBean.getAddress(),
                                        toAddress,
                                        Double.parseDouble(quantity),
                                        transType,
                                        memo);
                            } else {
                                ToastUtil.show(TranslateApolloAcy.this, getString(R.string.error_pwd));
                            }
                        }

                        @Override
                        public void cancle() {
                        }
                    });
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == ScanUtils.SCAN_CODE) {
            try {
                Bundle bundle = data.getExtras();
                assert bundle != null;
                String scanResult = bundle.getString(ScanUtils.SCAN_DATA);
                //将扫描出的信息显示出来
                if (TextUtils.isEmpty(scanResult)) {
                    ToastUtil.show(this, getString(R.string.str_qr_error));
                    return;
                }
                onResultScanData(scanResult);
            } catch (Exception e) {
//                ToastUtil.show(this, "扫码识别返回：" + e.getMessage());
            }
        }
    }

    /**
     * 扫码返回的结果
     *
     * @param scanResult 结果
     */
    public void onResultScanData(String scanResult) {
        editReceiveAddress.setText(scanResult);
    }

    /**
     * @param v
     * @param dataString
     * @param type       type =1 左边 type =2 右边
     */
    private void showPopupWindow(View v, String dataString, int type, IGetBackResult iGetBackResult) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_pop_from_address, null);
        final PopupWindow popupWindow = new PopupWindow(view, 992, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        ListView lv_data = view.findViewById(R.id.lv_data);
        AdapterTransPage adapterTransPage = new AdapterTransPage(this);
        lv_data.setAdapter(adapterTransPage);
        adapterTransPage.setData(dataString);
        List<String> stringList = new ArrayList<>();

        if (type == 1) {
            List<UserWalletBean> list = DaoUtil.selectAllWalletByType(Constant.TYPE_APOLLO);
            for (UserWalletBean bean : list) {
                stringList.add(bean.getAddress());
            }
        } else {
            stringList.add(Constant.TYPE_AOT);
            stringList.add(Constant.TYPE_AOC);
            stringList.add(Constant.TYPE_NBO);
            stringList.add(Constant.TYPE_DDAO);
            stringList.add(Constant.TYPE_LON);
        }
        adapterTransPage.setList(stringList);
        if (type == 1) {
            popupWindow.showAsDropDown(v, 0, 15, Gravity.LEFT);
        } else {
            popupWindow.showAsDropDown(v, 0, 15, Gravity.RIGHT);
        }
        lv_data.setOnItemClickListener((parent, view1, position, id) -> {
            iGetBackResult.getTextString(stringList.get(position));
            popupWindow.dismiss();
        });
    }
}
