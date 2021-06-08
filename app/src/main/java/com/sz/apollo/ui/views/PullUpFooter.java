package com.sz.apollo.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sz.apollo.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Date:2019/2/19
 * auther:andmin
 * describe:经典上拉底部组件
 */
public class PullUpFooter extends LinearLayout implements RefreshFooter {

    private static final String REFRESH_BOTTOM_PULLUP = "Pull-up Load More";
    private static final String REFRESH_BOTTOM_RELEASE = "Release immediate loading";
    private static final String REFRESH_BOTTOM_LOADING = "Loading...";
    private static final String NOMODATA = "no more data...";
    private TextView mBottomText;
    private ImageView mProgressView;
    private ProgressDrawable mProgressDrawable;
    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    private Context context;

    //<editor-fold desc="LinearLayout">
    public PullUpFooter(Context context) {
        super(context);
        this.context = context;
        this.initView(context, null, 0);
    }

    public PullUpFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs, 0);
    }

    public PullUpFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        DensityUtil density = new DensityUtil();

        setGravity(Gravity.CENTER);
        setMinimumHeight(density.dip2px(60));

        mProgressDrawable = new ProgressDrawable(context);
        mProgressDrawable.setColor(context.getResources().getColor(R.color.gray3));
        mProgressView = new ImageView(context);
        mProgressView.setImageDrawable(mProgressDrawable);
        LayoutParams lpPathView = new LayoutParams(density.dip2px(16), density.dip2px(16));
        lpPathView.rightMargin = density.dip2px(10);
        addView(mProgressView, lpPathView);

        mBottomText = new AppCompatTextView(context, attrs, defStyleAttr);
        mBottomText.setTextColor(context.getResources().getColor(R.color.gray3));
        mBottomText.setTextSize(16);
        mBottomText.setText(context.getString(R.string.pull_load_more));

        addView(mBottomText, WRAP_CONTENT, WRAP_CONTENT);

        if (!isInEditMode()) {
            mProgressView.setVisibility(GONE);
        }

        TypedArray ta = context.obtainStyledAttributes(attrs, com.scwang.smartrefresh.layout.R.styleable.ClassicsFooter);

        mSpinnerStyle = SpinnerStyle.values()[ta.getInt(com.scwang.smartrefresh.layout.R.styleable.ClassicsFooter_srlClassicsSpinnerStyle, mSpinnerStyle.ordinal())];

        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsFooter_srlAccentColor)) {
            int accentColor = ta.getColor(com.scwang.smartrefresh.layout.R.styleable.ClassicsFooter_srlAccentColor, 0);
            setAccentColor(accentColor);
        }

        ta.recycle();
    }

    //</editor-fold>

    //<editor-fold desc="RefreshFooter">

    @Override
    public void onInitialized(RefreshKernel layout, int height, int extendHeight) {

    }

    @Override
    public void onPullingUp(float percent, int offset, int bottomHeight, int extendHeight) {

    }

    @Override
    public void onPullReleasing(float percent, int offset, int headHeight, int extendHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
        mProgressView.setVisibility(VISIBLE);
        mProgressDrawable.start();
    }

    @Override
    public void onFinish(RefreshLayout layout) {
        mProgressDrawable.stop();
        mProgressView.setVisibility(GONE);
    }

    /**
     * PullUpFooter 没有主题色
     * PullUpFooter has no primary colors
     */
    @Override
    public void setPrimaryColors(int... colors) {
        if (mSpinnerStyle == SpinnerStyle.FixedBehind) {
            if (colors.length > 1) {
                setBackgroundColor(colors[0]);
                mBottomText.setTextColor(colors[1]);
                mProgressDrawable.setColor(colors[1]);
            } else if (colors.length > 0) {
                setBackgroundColor(colors[0]);
                if (colors[0] == 0xffffffff) {
                    mBottomText.setTextColor(0xff666666);
                    mProgressDrawable.setColor(0xff666666);
                } else {
                    mBottomText.setTextColor(0xffffffff);
                    mProgressDrawable.setColor(0xffffffff);
                }
            }
        }

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
                mBottomText.setText(NOMODATA);
            case PullToUpLoad:
                mBottomText.setText(context.getString(R.string.pull_load_more));
                break;
            case Loading:
                mBottomText.setText(context.getString(R.string.data_loading));
                break;
            case ReleaseToLoad:
                mBottomText.setText(context.getString(R.string.release_load_now));
                replaceRefreshLayoutBackground(refreshLayout);
                break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="private">
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
    public PullUpFooter setSpinnerStyle(SpinnerStyle style) {
        this.mSpinnerStyle = style;
        return this;
    }

    public PullUpFooter setAccentColor(int accentColor) {
        mBottomText.setTextColor(accentColor);
        mProgressDrawable.setColor(accentColor);
        return this;
    }
    //</editor-fold>

}
