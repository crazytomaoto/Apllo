package com.common.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Listview基本文字适配器基类
 * @author benny
 * @date 2017/11/3
 * description
 */

public abstract class AppBaseAdapter<T> extends BaseAdapter {
    public List<T> list = new ArrayList<>();
    public Context context;
    private ListView listView;

    public AppBaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        BaseHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(bindItemLayout(), parent, false);
            holder = new BaseHolder(view);
            convertView = view;
            convertView.setTag(holder);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        onItemView(holder, list.get(position), position);
        return convertView;
    }

    public int getListViewHeight() {
        return listView.getBottom() - listView.getTop();
    }

    protected abstract int bindItemLayout();

    protected abstract void onItemView(BaseHolder holder, T t, int position);

    public void onRefresh(List<T> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public void setData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void onLoadMore(List<T> list) {
        if (list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        list = new ArrayList<>();
        if (list.size() >= 0) {
            list.clear();
        }
        notifyDataSetChanged();
    }

    public void setColorText(TextView views, int textColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            views.setTextColor(context.getColor(textColor));
        } else {
            views.setTextColor(context.getResources().getColor(textColor));
        }
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public class BaseHolder {

        private SparseArray<View> mViews;
        public View mRootView;

        public BaseHolder(View itemView) {

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
}
