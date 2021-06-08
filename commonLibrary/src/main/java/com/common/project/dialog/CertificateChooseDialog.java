package com.common.project.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.common.project.R;


/**
 * 选择证件选择弹出框
 *
 * @author benny
 */

public class CertificateChooseDialog {
    private static CommonDialog dialog;


    /**
     * 打开证件选择
     *
     * @param context 上下文
     */
    public static void selector(final Context context, final CallBackListener listener) {
        dialog = new CommonDialog.Builder(context)
                .setShape(R.drawable.dialog_tra_shape)
                .setGravity(Gravity.BOTTOM)
                .setWidth(1.0f)
                .setResId(R.layout.selelctor_photo_dialog)
                .setCancelListener(new CommonDialog.onCancelCallBack() {
                    @Override
                    public void onCancel() {
                        if (listener != null) {
                            listener.onDismiss();
                        }
                    }
                })
                .build();
        View view = dialog.getView();
        RadioButton rbTaking =  view.findViewById(R.id.rb_taking);
        RadioButton rbAlbum = view.findViewById(R.id.rb_album);
        TextView tvCancel =  view.findViewById(R.id.tv_cancel);
        rbTaking.setText(context.getString(R.string.str_sfz));
        rbAlbum.setText(context.getString(R.string.str_huzhao));
        rbTaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCertificateType(1, context.getString(R.string.str_sfz));
                }
                if (listener != null) {
                    listener.onDismiss();
                }
                dissmiss();
            }
        });
        rbAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCertificateType(2, context.getString(R.string.str_huzhao));
                }
                if (listener != null) {
                    listener.onDismiss();
                }
                dissmiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDismiss();
                }
                dissmiss();
            }
        });
        dialog.show();
    }




    public static void dissmiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void clear() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public interface CallBackListener {
        void onCertificateType(int type, String title);

        void onDismiss();
    }

}
