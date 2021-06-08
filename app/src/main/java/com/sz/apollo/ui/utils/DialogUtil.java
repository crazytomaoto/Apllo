package com.sz.apollo.ui.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.adapters.AdapterChooseWalletType;
import com.sz.apollo.ui.adapters.AdapterContactDialog;
import com.sz.apollo.ui.adapters.AdapterPaymentAddressDialog;
import com.sz.apollo.ui.adapters.AdapterWalletList;
import com.sz.apollo.ui.interfaces.IDialogOneButton;
import com.sz.apollo.ui.interfaces.IGetBackResult;
import com.sz.apollo.ui.interfaces.IPassGetInput;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.ContactAddressBean;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.models.WalletTypeBean;
import com.sz.apollo.ui.utils.qrUtil.ScanUtils;
import com.sz.apollo.ui.views.AutoListView;

import java.util.ArrayList;
import java.util.List;

public class DialogUtil {
    /**
     * 收款的dialog弹窗
     *
     * @param activity
     * @param type
     * @param address
     * @return
     */
    public static Dialog dialogReceive(Activity activity, String type, String address) {
        final Dialog dialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        dialog.setContentView(R.layout.layout_receive_dialog);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.BOTTOM;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(true);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog
        LinearLayout llClose = dialog.findViewById(R.id.ll_close);
        llClose.setOnClickListener(v -> dialog.dismiss());
        TextView tv_address = dialog.findViewById(R.id.tv_address);
        LinearLayout ll_copy = dialog.findViewById(R.id.ll_copy);
        ImageView iv_qr = dialog.findViewById(R.id.iv_qr);
        TextView tip = dialog.findViewById(R.id.tip);
        if (!StringUtils.isEmpty(address)) {
            tv_address.setText(address);
            ScanUtils.creatQr(address, iv_qr);
        }
        if (!StringUtils.isEmpty(type)) {
            tip.setText(activity.getResources().getString(R.string.tip_scan) + type.toUpperCase());
        }
        ll_copy.setOnClickListener(v -> {
            if (!StringUtils.isEmpty(address)) {
                Util.copy(activity, address);
                ToastUtil.show(activity, activity.getString(R.string.t_copied));
            }
        });
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 转账选择联系人的弹窗
     *
     * @param activity
     * @param address
     * @return
     */
    public static Dialog dialogContacts(Activity activity, String address, IGetBackResult iGetBackResult) {
        final Dialog dialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        dialog.setContentView(R.layout.layout_dialog_contact);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.BOTTOM;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(true);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(false);//是否可以取消dialog
        LinearLayout ll_close = dialog.findViewById(R.id.ll_close);
        ll_close.setOnClickListener(v -> dialog.dismiss());
        EditText ed_search = dialog.findViewById(R.id.ed_search);
        ImageView iv_search = dialog.findViewById(R.id.iv_search);
        ListView lv_contacts = dialog.findViewById(R.id.lv_contacts);
        AdapterContactDialog adapterContactDialog = new AdapterContactDialog(activity, address);
        List<ContactAddressBean> list = DaoUtil.selectAllContact();
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isEmpty(s.toString())) {
                    adapterContactDialog.setList(list);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iv_search.setOnClickListener(v -> {
            String edAddress = ed_search.getText().toString().trim();
            if (!StringUtils.isEmpty(edAddress)) {
                List<ContactAddressBean> newList = DaoUtil.selectContactFromAddress(edAddress);
                if (newList != null && newList.size() > 0) {
                    adapterContactDialog.setList(newList);
                }
            }
        });
        lv_contacts.setAdapter(adapterContactDialog);
        adapterContactDialog.setList(list);
        lv_contacts.setOnItemClickListener((parent, view, position, id) -> {
            iGetBackResult.getTextString(list.get(position).getAddress());
            dialog.dismiss();
        });
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 选择身份的弹窗
     *
     * @param activity
     * @return
     */
    public static Dialog dialogChooseIdentity(Activity activity, IGetBackResult iGetBackResult) {
        final int[] nowSelection = {0};
        final Dialog dialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        dialog.setContentView(R.layout.layout_dialog_choose_id);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.BOTTOM;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(true);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog

        LinearLayout ll_close = dialog.findViewById(R.id.ll_close);
        ll_close.setOnClickListener(v -> dialog.dismiss());
        ListView lv_logo = dialog.findViewById(R.id.lv_logo);
        AutoListView lv_wallets = dialog.findViewById(R.id.lv_wallets);
        TextView tv_add_wallet = dialog.findViewById(R.id.tv_add_wallet);
        TextView tv_wallet_type = dialog.findViewById(R.id.tv_wallet_type);


        //钱包
        AdapterWalletList adapterWalletList = new AdapterWalletList(activity);
        List<UserWalletBean> list = null;
        list = DaoUtil.selectAllWallet();
        lv_wallets.setAdapter(adapterWalletList);
        adapterWalletList.setList(list);
        final List<UserWalletBean>[] finalList1 = new List[]{list};
        lv_wallets.setOnItemClickListener((parent, view, position, id) -> {

            //更新选中钱包
            UserWalletBean nowWallet = DaoUtil.selectNowWallet();
            if (null != nowWallet) {
                nowWallet.setIsNowWallet("0");
                DaoUtil.updateWallet(nowWallet);
            }
            finalList1[0].get(position).setIsNowWallet("1");
            DaoUtil.updateWallet(finalList1[0].get(position));
            iGetBackResult.getTextString(position + "");
            dialog.dismiss();
        });


        //钱包种类
        WalletTypeBean walletTypeBean1 = new WalletTypeBean();
        walletTypeBean1.setName("ALL");
        walletTypeBean1.setSelected(true);
        WalletTypeBean walletTypeBean2 = new WalletTypeBean();
        walletTypeBean2.setName("Apollo");
        walletTypeBean2.setSelected(false);
        WalletTypeBean walletTypeBean3 = new WalletTypeBean();
        walletTypeBean3.setName("ETH");
        walletTypeBean3.setSelected(false);
        List<WalletTypeBean> typeBeanList = new ArrayList<>();
        typeBeanList.add(walletTypeBean1);
        typeBeanList.add(walletTypeBean2);
        typeBeanList.add(walletTypeBean3);
        AdapterChooseWalletType adapterChooseWalletType = new AdapterChooseWalletType(activity);
        adapterChooseWalletType.setList(typeBeanList);

        adapterChooseWalletType.setSelectedposition(nowSelection[0]);
        lv_logo.setAdapter(adapterChooseWalletType);
        lv_logo.setOnItemClickListener((parent, view, position, id) -> {
            nowSelection[0] = position;
            adapterChooseWalletType.setSelectedposition(nowSelection[0]);
            if (typeBeanList.get(position).getName().equals("ALL")) {
                finalList1[0].clear();
                finalList1[0] = DaoUtil.selectAllWallet();
                adapterWalletList.setList(finalList1[0]);
                tv_wallet_type.setText(activity.getString(R.string.t_id));
            } else {
                finalList1[0].clear();
                finalList1[0] = DaoUtil.selectAllWalletByType(typeBeanList.get(position).getName());
                adapterWalletList.setList(finalList1[0]);
                tv_wallet_type.setText(typeBeanList.get(position).getName());
            }
        });

        tv_add_wallet.setOnClickListener(v -> {
            if (ClickUtil.isNotFirstClick()) {
                return;
            }
            UiHelper.startChooseTypeAcy(activity, typeBeanList.get(nowSelection[0]).getName());
            dialog.dismiss();
        });

        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * @param activity
     * @param iPassGetInput
     * @return
     */
    public static Dialog dialogPwd(Activity activity, IPassGetInput iPassGetInput) {
        final Dialog dialog = new Dialog(activity, R.style.dialog_tips);
        dialog.setContentView(R.layout.layout_dialog_password);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.CENTER;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(v -> {
            iPassGetInput.cancle();
            dialog.dismiss();
        });

        EditText ed_pass = dialog.findViewById(R.id.ed_pass);
        TextView tv_sure = dialog.findViewById(R.id.tv_sure);
        ImageView iv_canSee = dialog.findViewById(R.id.iv_canSee);
        final boolean[] canSee = {false};
        iv_canSee.setOnClickListener(v -> {
            if (canSee[0]) {
                canSee[0] = !canSee[0];
                // 设置为可见
                ed_pass.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                ed_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                iv_canSee.setImageResource(R.drawable.icon_can_see);
                ed_pass.setSelection(ed_pass.getText().toString().trim().length());
            } else {
                canSee[0] = !canSee[0];
                // 设置为不可见
                ed_pass.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ed_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                iv_canSee.setImageResource(R.drawable.icon_can_see_not);
                ed_pass.setSelection(ed_pass.getText().toString().trim().length());
            }
        });
        tv_sure.setOnClickListener(v -> {
            if (!StringUtils.isEmpty(ed_pass.getText().toString().trim())) {
                iPassGetInput.getTextString(ed_pass.getText().toString());
                dialog.dismiss();
            }
        });

        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 选择导入方式Diaolog
     *
     * @param activity
     * @param way      1 助记词 2 私钥 3 keystore
     * @return
     */
    public static Dialog dialogChooseWayToImportEth(Activity activity) {
        final Dialog dialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        dialog.setContentView(R.layout.layout_way_import_eth);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.BOTTOM;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(true);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(false);//是否可以取消dialog
        LinearLayout ll_close = dialog.findViewById(R.id.ll_close);
        ll_close.setOnClickListener(v -> dialog.dismiss());
        RelativeLayout rl_phrase = dialog.findViewById(R.id.rl_phrase);
        RelativeLayout rl_pk = dialog.findViewById(R.id.rl_pk);
        RelativeLayout rl_key = dialog.findViewById(R.id.rl_key);
        rl_phrase.setOnClickListener(v -> {
                    UiHelper.startImportEthAcy(activity, 1);
                    dialog.dismiss();
                }
        );
        rl_pk.setOnClickListener(v -> {
                    UiHelper.startImportEthAcy(activity, 2);
                    dialog.dismiss();
                }
        );
        rl_key.setOnClickListener(v -> {
                    UiHelper.startImportEthAcy(activity, 3);
                    dialog.dismiss();
                }
        );
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 闪兑--选择支付账户的弹窗
     *
     * @param activity
     * @param address
     * @return
     */
    public static Dialog dialogPayForFlashExchange(Activity activity, String address, List<BalanceBean> list, IGetBackResult iGetBackResult) {
        final Dialog dialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        dialog.setContentView(R.layout.layout_dialog_pay_for_flash_exchange);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.BOTTOM;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(true);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(false);//是否可以取消dialog
        LinearLayout ll_close = dialog.findViewById(R.id.ll_close);
        ll_close.setOnClickListener(v -> dialog.dismiss());
        ListView lv_address = dialog.findViewById(R.id.lv_address);
        AdapterPaymentAddressDialog adapterPaymentAddressDialog = new AdapterPaymentAddressDialog(activity, address);
        adapterPaymentAddressDialog.setList(list);
        lv_address.setAdapter(adapterPaymentAddressDialog);
        lv_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iGetBackResult.getTextString(list.get(position));
                dialog.dismiss();
            }
        });
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 删除联系人
     *
     * @param activity
     * @param addressId      联系人地址id
     * @param iGetBackResult
     * @return
     */
    public static Dialog dialogDeleteContact(Activity activity, long addressId, IGetBackResult iGetBackResult) {
        final Dialog dialog = new Dialog(activity, R.style.dialog_tips);
        dialog.setContentView(R.layout.layout_dialog_delete_contact);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.CENTER;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        TextView tv_sure = dialog.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(v -> {
            DaoUtil.deleteContactById(addressId);
            iGetBackResult.getTextString(true);
            dialog.dismiss();
        });
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    public static void showVersionCheckDialog(Activity activity, String v, String des, IDialogOneButton dialogOneButton) {
        final Dialog dialog = new Dialog(activity, R.style.dialog_tips);
        dialog.setContentView(R.layout.layout_dialog_app_version);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(false);//是否可以取消dialog
        TextView tv_vName = dialog.findViewById(R.id.tv_vName);
        TextView tv_appDesc = dialog.findViewById(R.id.tv_appDesc);
        tv_vName.setText("version：" + v);
        tv_appDesc.setText(des);
        TextView tv_button = dialog.findViewById(R.id.tv_button);
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOneButton.sure(v, dialog);
            }
        });
        if (!activity.isFinishing()) {
            dialog.show();
        }
    }

    /**
     * raw的弹窗
     *
     * @param activity
     * @param content
     * @return
     */
    public static Dialog showDialogRaw(Activity activity, String content, String topic, IGetBackResult iGetBackResult) {
        final Dialog dialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        dialog.setContentView(R.layout.layout_dialog_raw);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.BOTTOM;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(true);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog
        LinearLayout ll_close = dialog.findViewById(R.id.ll_close);
        TextView tv_topic = dialog.findViewById(R.id.tv_topic);
        ll_close.setOnClickListener(v -> {
                    if (null != iGetBackResult) {
                        iGetBackResult.getTextString("true");
                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                    }
                }
        );
        TextView tv_content = dialog.findViewById(R.id.tv_content);
        tv_content.setText(content);
        tv_topic.setText(topic);
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * @param activity
     * @param iPassGetInput
     * @return
     */
    public static Dialog dialogSaveNewContact(Activity activity, IPassGetInput iPassGetInput) {
        final Dialog dialog = new Dialog(activity, R.style.dialog_tips);
        dialog.setContentView(R.layout.layout_dialog_save_new_address);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.CENTER;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(v -> {
            iPassGetInput.cancle();
            dialog.dismiss();
        });

        TextView tv_sure = dialog.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(v -> {
            iPassGetInput.getTextString("true");
            dialog.dismiss();
        });

        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * @param activity
     * @param iPassGetInput
     * @return
     */
    public static Dialog dialogTips(Activity activity, String content, String button, IPassGetInput iPassGetInput) {
        final Dialog dialog = new Dialog(activity, R.style.dialog_tips);
        dialog.setContentView(R.layout.layout_dialog_tips);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.CENTER;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog
        TextView tv_content = dialog.findViewById(R.id.tv_content);
        TextView tv_button = dialog.findViewById(R.id.tv_button);
        tv_content.setText(content);
        tv_button.setText(button);
        tv_button.setOnClickListener(v -> {
            iPassGetInput.getTextString("true");
            dialog.dismiss();
        });

        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * @param activity
     * @param iPassGetInput
     * @return
     */
    public static Dialog dialogActiveNbo(Activity activity, String type, IPassGetInput iPassGetInput) {
        final Dialog dialog = new Dialog(activity, R.style.dialog_tips);
        dialog.setContentView(R.layout.layout_dialog_active_nbo);
        //改变蒙版的透明度
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        lp.gravity = Gravity.CENTER;
        lp.x = 0;
        lp.y = 0;
        int mWidth;
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
        } else {
            DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
        }
        dialogWindow.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.dimAmount = 0.8f;
        lp.width = dialog.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);//点击dialog区域以外的地方dialog是否可以取消
        dialog.setCancelable(true);//是否可以取消dialog
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);
        TextView tv_sure = dialog.findViewById(R.id.tv_sure);
        TextView tv_content = dialog.findViewById(R.id.tv_content);
        EditText ed_pass = dialog.findViewById(R.id.ed_pass);
        switch (type) {
            case Constant.TYPE_NBO:
                tv_content.setText(activity.getResources().getString(R.string.tip227) + Constant.TYPE_NBO);
                break;
            case Constant.TYPE_LON:
            case Constant.TYPE_DDAO:
                tv_content.setText(activity.getResources().getString(R.string.tip227) + Constant.TYPE_AOC);
                break;
        }
        tv_sure.setOnClickListener(v -> {
            if (!StringUtils.isEmpty(ed_pass.getText().toString().trim())) {
                iPassGetInput.getTextString(ed_pass.getText().toString());
                dialog.dismiss();
            }
        });
        tv_cancel.setOnClickListener(v -> dialog.dismiss());

        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
}
