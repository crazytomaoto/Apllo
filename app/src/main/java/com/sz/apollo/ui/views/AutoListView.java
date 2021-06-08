package com.sz.apollo.ui.views;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Date:2019/10/14
 * Time:20:17
 * author:andmin
 * describe:
 */
public class AutoListView extends ListView {

    public AutoListView(Context context) {

        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public AutoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
