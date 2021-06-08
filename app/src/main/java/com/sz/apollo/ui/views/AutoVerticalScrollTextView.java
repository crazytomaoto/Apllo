package com.sz.apollo.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.ui.models.NoticeBean;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 自动滚动的textview
 *
 * @author wencheng
 * @date 2019/5/6
 */
public class AutoVerticalScrollTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //更新Id值
                id = next();
                //更新TextSwitcherd显示内容;
                try {
                    updateText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ;
    };

    int id = 0; //数组id
    private Context context;
    private List<NoticeBean.DataBeanX.DataBean> list;
    Timer timer;

    public AutoVerticalScrollTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AutoVerticalScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    public void setData(List<NoticeBean.DataBeanX.DataBean> data) {
        this.list = data;
    }


    private void init() {
        this.setFactory(this);
        this.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom));
        this.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_top));
    }


    public void start() {
        if (timer == null) {
            timer = new Timer();
        }
        timer.scheduleAtFixedRate(new MyTask(), 1, 10000);//每3秒更新
    }

    public void stop() {
        if (mHandler != null) {
            mHandler.removeMessages(1);
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void updateText() {
        if (list != null && list.size() > 0) {
            this.setText(list.get(id).getTitle());
        }
    }

    private int next() {
        if (list != null && list.size() > 0) {
            int flag = id + 1;
            if (flag > list.size() - 1) {
                flag = flag - list.size();
            }
            return flag;
        }
        return 0;
    }

    @Override
    public View makeView() {
        TextView tv = new TextView(context);
        if (list != null && list.size() > 0) {
            if (!StringUtils.isEmpty(list.get(id).getTitle())) {
                tv.setText(list.get(id).getTitle() + "");
            }
        }

        /*
         android:maxEms="18"
         android:singleLine="true"
         android:ellipsize="end"
         */
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setSingleLine(true);
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewOnClick != null) {
                    textViewOnClick.onTextViewOnClick(view, id);
                }
            }
        });

        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
        return tv;
    }

    TextViewOnClick textViewOnClick;

    public void setTextViewOnClick(TextViewOnClick textViewOnClick) {
        this.textViewOnClick = textViewOnClick;
    }

    public interface TextViewOnClick {
        void onTextViewOnClick(View view, int position);
    }

    /**
     * 设置颜色
     *
     * @param color 颜色值
     */
    public void setTextColor(int color) {
        TextView view = (TextView) makeView();
        if (view != null) {
            view.setTextColor(color);
        }
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
        }
    }
}
