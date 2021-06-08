package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterTransPage extends BaseAdapter {
    private Context context;
    private List<String> list;
    private String data;

    public void setData(String data) {
        this.data = data;
    }

    public AdapterTransPage(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_trans_page, parent, false);
            holder.tv_left = convertView.findViewById(R.id.tv_left);
            holder.iv_right = convertView.findViewById(R.id.iv_right);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_left.setText(list.get(position));
        if (list.get(position).equals(data)) {
            holder.tv_left.setTextColor(context.getResources().getColor(R.color.gray3));
            holder.iv_right.setVisibility(View.VISIBLE);
        } else {
            holder.tv_left.setTextColor(context.getResources().getColor(R.color.white));
            holder.iv_right.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_left;
        ImageView iv_right;
    }
}
