package com.common.project.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.common.project.R;


/**
 * 公共弹出框
 *
 * @author benny on 2016/3/24.
 */
public class CommonDialog {
    private Dialog dialog;
    /**
     * 弹出宽的位置，默认居中
     */
    private int gravity;
    /**
     * 弹出框的宽度,默认0.8，例如：1表示全屏，0.8表示为屏幕宽度的0.8倍
     */
    private float width;
    /**
     * 默认布局自动加载高度
     */
    private float height;
    /**
     * 弹出框形状，默认方角
     */
    private int shape;
    /**
     * 布局id
     */
    private int resId;
    /**
     * 动画id
     */
    private int animResId;
    /**
     * 点击屏幕是否可消失
     */
    private boolean isTouchOutside;
    private View view;
    /**
     * 圆角
     */
    public static final int CIRCLE = R.drawable.dialog_circle_shape;
    /**
     * 方角
     */
    public static final int SQUARE = R.drawable.dialog_square_shape;
    /**
     * 透明
     */
    public static final int TRA = R.drawable.dialog_tra_shape;
    /**
     * 默认弹出
     */
    public static final int DIALOG_OnLine_IN_OUT = R.style.dark_animation;
    /**
     * 向上弹起向下滑落
     */
    public static final int DIALOG_IN_OUT = R.style.dialog_in_out;
    /**
     * 从左弹出从右关闭
     */
    public static final int DIALOG_LEFT_RIGHT = R.style.dialog_left_right;

    private Activity activity;

    private CommonDialog(Builder builder) {
        this.activity = builder.activity;
        this.gravity = builder.gravity;
        this.width = builder.width;
        this.height = builder.height;
        this.shape = builder.shape;
        this.resId = builder.resId;
        this.animResId = builder.animResId;
        this.view = builder.view;
        this.dialog = builder.dialog;
        this.isTouchOutside = builder.isTouchOutside;
        this.dialog.setOnKeyListener(listener);
    }

    public static class Builder {
        private WindowManager windowManager;
        private Display display;
        private Activity activity;
        private Dialog dialog;
        private int gravity = Gravity.CENTER;
        private float width = 0.9f;
        private float height = 0.0f;
        private int shape = SQUARE;
        private int resId;
        private int animResId = DIALOG_IN_OUT;
        private boolean isTouchOutside = true;
        public View view;
        public onCancelCallBack cancelCallBack;

        public Builder(Context context) {
            this.activity = (Activity) context;
            windowManager = activity.getWindowManager();
            display = windowManager.getDefaultDisplay();
        }

        public Builder setGravity(int GRAVITY) {
            this.gravity = GRAVITY;
            return this;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public Builder setShape(int shape) {
            this.shape = shape;
            return this;
        }

        public Builder setResId(int resId) {
            this.resId = resId;
            return this;
        }

        public Builder setAnimResId(int animResId) {
            this.animResId = animResId;
            return this;
        }

        public Builder setTouchOutside(boolean touchOutside) {
            isTouchOutside = touchOutside;
            return this;
        }

        public Builder setCancelListener(onCancelCallBack cancelListener){
            this.cancelCallBack = cancelListener;
            return this;
        }

        public CommonDialog build() {
            view = LayoutInflater.from(activity).inflate(resId, null, false);
            dialog = new Dialog(activity);
            //取消标题
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //点击屏幕消失
            dialog.setCanceledOnTouchOutside(isTouchOutside);
            dialog.setContentView(view);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    if(cancelCallBack!=null){
                        cancelCallBack.onCancel();
                    }
                }
            });
            Window dialogwWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogwWindow.getAttributes();
            //设置Dialog宽度
            lp.width = (int) (display.getWidth() * width);
            if (height != 0) {
                //设置Dialog高度
                lp.height = (int) (display.getHeight() * height);
            }
            dialogwWindow.setAttributes(lp);
            //设置dialog显示位置
            dialogwWindow.setGravity(gravity);
            //设置dialog背景风格
            dialogwWindow.setBackgroundDrawableResource(shape);
            if (animResId != 0) {
                //设置动画效果
                dialogwWindow.setWindowAnimations(animResId);
            }
            return new CommonDialog(this);
        }

    }

    public interface onCancelCallBack{
        void onCancel();
    }

    /**
     * 关闭dialog
     */
    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 显示dialog
     */
    public void show() {
        if (dialog != null && !dialog.isShowing()&&!activity.isFinishing()) {
            dialog.show();
        } else {
            if(dialog!=null) {
                dialog.dismiss();
            }
        }
    }

    /**
     * 是否show
     *
     * @return
     */
    public boolean isShowing() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public View getView() {
        return view;
    }

    private DialogInterface.OnKeyListener listener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == KeyEvent.KEYCODE_BACK) {
                return !isTouchOutside;
            }
            return false;
        }
    };
}
