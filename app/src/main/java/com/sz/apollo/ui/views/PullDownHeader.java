package com.sz.apollo.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.internal.pathview.PathsView;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sz.apollo.R;
import com.sz.apollo.ui.utils.TimeUtil;

import java.text.DateFormat;
import java.util.Date;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


/**
 * Date:2019/2/19
 * auther:andmin
 * describe:
 */
public class PullDownHeader extends RelativeLayout implements RefreshHeader {

    public static String REFRESH_HEADER_PULLDOWN = "Dropdown refreshes";
    public static String REFRESH_HEADER_REFRESHING = "Refreshing";
    public static String REFRESH_HEADER_RELEASE = "Release and refresh immediately";

    private Date mLastTime;
    private TextView mHeaderText;
    private TextView mLastUpdateText;
    private PathsView mArrowView;
    private ImageView mProgressView;
    private ProgressDrawable mProgressDrawable;
    private DateFormat mFormat;
    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    private Context context;

    //<editor-fold desc="RelativeLayout">
    public PullDownHeader(Context context) {
        super(context);
        this.context = context;
        this.initView(context, null, 0);
    }

    public PullDownHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs, 0);
    }

    public PullDownHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        DensityUtil density = new DensityUtil();

        setMinimumHeight(density.dip2px(80));

        mProgressDrawable = new ProgressDrawable();
        mProgressDrawable.setColor(context.getResources().getColor(R.color.gray4));
        mProgressView = new ImageView(context);
        mProgressView.setImageDrawable(mProgressDrawable);
        LayoutParams lpProgress = new LayoutParams(density.dip2px(20), density.dip2px(20));
        lpProgress.leftMargin = density.dip2px(80);
        lpProgress.addRule(CENTER_VERTICAL);
        lpProgress.addRule(ALIGN_PARENT_LEFT);
        addView(mProgressView, lpProgress);

        mArrowView = new PathsView(context);
        mArrowView.parserColors(context.getResources().getColor(R.color.gray4));
        mArrowView.parserPaths("M20,12l-1.41,-1.41L13,16.17V4h-2v12.17l-5.58,-5.59L4,12l8,8 8,-8z");
        addView(mArrowView, lpProgress);

        LinearLayout layout = new LinearLayout(context, attrs, defStyleAttr);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setOrientation(LinearLayout.VERTICAL);
        mHeaderText = new TextView(context);
        mHeaderText.setText(context.getString(R.string.pull_fresh));
        mHeaderText.setTextColor(context.getResources().getColor(R.color.gray4));
        mHeaderText.setTextSize(16);

        mLastUpdateText = new TextView(context);
        mLastUpdateText.setText(context.getString(R.string.assestlog_refresh_date) + " " + TimeUtil.getDatePull());
        mLastUpdateText.setTextColor(0xff7c7c7c);
        mLastUpdateText.setTextSize(12);
        LinearLayout.LayoutParams lpHeaderText = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpHeaderText.leftMargin = density.dip2px(20);
        lpHeaderText.rightMargin = density.dip2px(20);
        layout.addView(mHeaderText, lpHeaderText);
        LinearLayout.LayoutParams lpUpdateText = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layout.addView(mLastUpdateText, lpUpdateText);

        LayoutParams lpHeaderLayout = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpHeaderLayout.addRule(CENTER_IN_PARENT);
        addView(layout, lpHeaderLayout);

        if (isInEditMode()) {
            mArrowView.setVisibility(GONE);
            mHeaderText.setText(context.getString(R.string.data_freshing));
        } else {
            mProgressView.setVisibility(GONE);
        }


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClassicsHeader);

        mSpinnerStyle = SpinnerStyle.values()[ta.getInt(R.styleable.ClassicsHeader_srlClassicsSpinnerStyle, mSpinnerStyle.ordinal())];

        int primaryColor = ta.getColor(R.styleable.ClassicsHeader_srlPrimaryColor, 0);
        int accentColor = ta.getColor(R.styleable.ClassicsHeader_srlAccentColor, 0);
        if (primaryColor != 0) {
            if (accentColor != 0) {
                setPrimaryColors(primaryColor, accentColor);
            } else {
                setPrimaryColors(primaryColor);
            }
        } else if (accentColor != 0) {
            setPrimaryColors(0, accentColor);
        }

        ta.recycle();
    }

    //</editor-fold>

    //<editor-fold desc="RefreshHeader">
    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
    }

    @Override
    public void onPullingDown(float percent, int offset, int headHeight, int extendHeight) {
    }

    @Override
    public void onReleasing(float percent, int offset, int headHeight, int extendHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
        mProgressDrawable.start();
    }

    @Override
    public void onFinish(RefreshLayout layout) {
        mProgressDrawable.stop();
    }

    @Override
    public void setPrimaryColors(int... colors) {

        setBackgroundColor(context.getResources().getColor(R.color.trans));
        mArrowView.parserColors(context.getResources().getColor(R.color.gray4));
        mHeaderText.setTextColor(context.getResources().getColor(R.color.gray4));
        mProgressDrawable.setColor(context.getResources().getColor(R.color.gray4));
        mLastUpdateText.setTextColor(context.getResources().getColor(R.color.gray4));
//
//
//        if (colors.length > 1) {
//            setBackgroundColor(colors[0]);
//            mArrowView.parserColors(colors[1]);
//            mHeaderText.setTextColor(colors[1]);
//            mProgressDrawable.setColor(colors[1]);
//            mLastUpdateText.setTextColor(context.getResources().getColor(R.color.light_blue));
//        } else if (colors.length > 0) {
//            setBackgroundColor(context.getResources().getColor(R.color.pageBackground));
//            mArrowView.parserColors(context.getResources().getColor(R.color.light_blue));
//            mHeaderText.setTextColor(context.getResources().getColor(R.color.light_blue));
//            mProgressDrawable.setColor(context.getResources().getColor(R.color.light_blue));
//            mLastUpdateText.setTextColor(context.getResources().getColor(R.color.light_blue));
////            if (colors[0] == 0xffffffff) {
////                mArrowView.parserColors(context.getResources().getColor(R.color.light_blue));
////                mHeaderText.setTextColor(context.getResources().getColor(R.color.light_blue));
////                mProgressDrawable.setColor(context.getResources().getColor(R.color.light_blue));
////                mLastUpdateText.setTextColor(context.getResources().getColor(R.color.light_blue));
////            } else {
////                mArrowView.parserColors(context.getResources().getColor(R.color.light_blue));
////                mHeaderText.setTextColor(context.getResources().getColor(R.color.light_blue));
////                mProgressDrawable.setColor(context.getResources().getColor(R.color.light_blue));
////                mLastUpdateText.setTextColor(context.getResources().getColor(R.color.light_blue));
////            }
//        }
    }

    @NonNull
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return mSpinnerStyle;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
                restoreRefreshLayoutBackground();
            case PullDownToRefresh:
                mHeaderText.setText(context.getString(R.string.pull_fresh));
                mArrowView.setVisibility(VISIBLE);
                mProgressView.setVisibility(GONE);
                mArrowView.animate().rotation(0);
                break;
            case Refreshing:
                mHeaderText.setText(context.getString(R.string.data_freshing));
                mProgressView.setVisibility(VISIBLE);
                mArrowView.setVisibility(GONE);
                break;
            case ReleaseToRefresh:
                mHeaderText.setText(context.getString(R.string.assestlog_release_fresh));
                mArrowView.animate().rotation(180);
                replaceRefreshLayoutBackground(refreshLayout);
                break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="background">
    private Runnable restoreRunable;

    private void restoreRefreshLayoutBackground() {
        if (restoreRunable != null) {
            restoreRunable.run();
            restoreRunable = null;
        }
    }

    private void replaceRefreshLayoutBackground(RefreshLayout refreshLayout) {
        if (restoreRunable == null && mSpinnerStyle == SpinnerStyle.FixedBehind) {
            restoreRunable = new Runnable() {
                Drawable drawable = refreshLayout.getLayout().getBackground();

                @Override
                public void run() {
                    refreshLayout.getLayout().setBackgroundDrawable(drawable);
                }
            };
            refreshLayout.getLayout().setBackgroundDrawable(getBackground());
        }
    }
    //</editor-fold>

    //<editor-fold desc="API">
    public PullDownHeader setLastUpdateTime(Date time) {
        mLastTime = time;
        mLastUpdateText.setText(mFormat.format(time));
        return this;
    }

    public PullDownHeader setTimeFormat(DateFormat format) {
        mFormat = format;
        mLastUpdateText.setText(mFormat.format(mLastTime));
        return this;
    }

    public PullDownHeader setSpinnerStyle(SpinnerStyle style) {
        this.mSpinnerStyle = style;
        return this;
    }
    //</editor-fold>

}
