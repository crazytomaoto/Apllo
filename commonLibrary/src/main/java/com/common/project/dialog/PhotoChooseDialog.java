package com.common.project.dialog;

import android.Manifest;
import android.app.Activity;
import androidx.core.app.ActivityCompat;

import android.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.common.project.R;
import com.common.project.utils.permission.PermissionUtils;
import com.common.project.utils.tool.PhotoUtils;


/**
 * 选择照片弹出框
 *
 * @author benny
 */

public class PhotoChooseDialog {
    private CommonDialog dialog;
    /**
     * 打开图片选择器
     *
     * @param fragment 片段
     */

    public static void open(final Fragment fragment) {
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (PermissionUtils.checkPermissions(fragment.getActivity(), permissions)) {
            fragment.requestPermissions(permissions, PermissionUtils.REQUEST_CODE);
        } else {

           final CommonDialog dialog = new CommonDialog.Builder(fragment.getActivity())
                    .setShape(R.drawable.dialog_tra_shape)
                    .setGravity(Gravity.BOTTOM)
                    .setWidth(1.0f)
                    .setResId(R.layout.selelctor_photo_dialog)
                    .build();

            View view = dialog.getView();
            RadioButton rbTaking = view.findViewById(R.id.rb_taking);
            RadioButton rbAlbum = view.findViewById(R.id.rb_album);
            TextView tvCancel = view.findViewById(R.id.tv_cancel);
            rbTaking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoUtils.toCamera(fragment);
                   // disMiss();
                    dialog.dismiss();
                }
            });
            rbAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoUtils.toHhotoAlbum(fragment);
//                    disMiss();
                    dialog.dismiss();
                }
            });
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    disMiss();
                }
            });
            //每次打开弹框都要设置为空，防止错误删除照片
            PhotoUtils.imageUri = null;
            dialog.show();
        }
    }

    /**
     * 打开图片选择器
     *
     * @param activity 界面
     */
    public static void open(final Activity activity) {
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (PermissionUtils.checkPermissions(activity, permissions)) {
            ActivityCompat.requestPermissions(activity, permissions, PermissionUtils.REQUEST_CODE);
        } else {
            final CommonDialog dialog = new CommonDialog.Builder(activity)
                    .setShape(R.drawable.dialog_tra_shape)
                    .setGravity(Gravity.BOTTOM)
                    .setWidth(1.0f)
                    .setResId(R.layout.selelctor_photo_dialog)
                    .build();
            View view = dialog.getView();
            RadioButton rbTaking = view.findViewById(R.id.rb_taking);
            RadioButton rbAlbum = view.findViewById(R.id.rb_album);
            TextView tvCancel = view.findViewById(R.id.tv_cancel);
            rbTaking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoUtils.toCamera(activity);
//                    disMiss();
                    dialog.dismiss();
                }
            });
            rbAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoUtils.toHhotoAlbum(activity);
                  //  disMiss();
                    dialog.dismiss();
                }
            });
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            //每次打开弹框都要设置为空，防止错误删除照片
            PhotoUtils.imageUri = null;
            dialog.show();
        }
    }



    /*** 销毁*/
    public static void disMiss() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//            dialog = null;
//        }
    }
}
