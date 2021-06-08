package com.common.project.adapter;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Recycler控件
 * Created by benny on 2019/4/26.
 */

public class RecyclerHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    public View mRootView;

    public RecyclerHolder(View itemView) {
        super(itemView);
        this.mRootView = itemView;
        mViews = new SparseArray<View>();
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, String text) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        TextView textView = (TextView) view;

        if (TextUtils.isEmpty(text)) {
            textView.setText("");
        } else {
            textView.setText(text);
        }
    }

    public void setImageRes(int viewId, int resImageId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        ImageView iv = (ImageView) view;
        iv.setImageResource(resImageId);
    }
}
