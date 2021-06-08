package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.ShareSystemBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterShareSystem extends BaseAdapter {
    private Context context;
    private List<ShareSystemBean.DataBean.ChildListBean> list;

    public AdapterShareSystem(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<ShareSystemBean.DataBean.ChildListBean> getList() {
        return list;
    }

    public void setList(List<ShareSystemBean.DataBean.ChildListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_share_system, parent, false);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_level = convertView.findViewById(R.id.tv_level);
            holder.tv_count = convertView.findViewById(R.id.tv_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ShareSystemBean.DataBean.ChildListBean dataBean = list.get(position);
        if (null != dataBean) {
            holder.tv_name.setText(dataBean.getAddress());
            holder.tv_count.setText(Util.getStringFromDecimal(dataBean.getBalance(), 4));
            holder.tv_level.setText("V" + dataBean.getLevel());
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_name, tv_level, tv_count;
    }
}
