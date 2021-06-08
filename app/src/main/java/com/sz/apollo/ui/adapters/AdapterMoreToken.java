package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.TokenKindBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterMoreToken extends BaseAdapter {
    private Context context;
    private ArrayList<TokenKindBean> list;
    private ISelectChangeListner iSelectChangeListner;

    public ISelectChangeListner getiOnItemClickListner() {
        return iSelectChangeListner;
    }

    public interface ISelectChangeListner {
        void onChange(int position, boolean isChecked);
    }

    public void setiOnItemClickListner(ISelectChangeListner iSelectChangeListner) {
        this.iSelectChangeListner = iSelectChangeListner;
    }

    public AdapterMoreToken(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<TokenKindBean> getList() {
        return list;
    }

    public void setList(ArrayList<TokenKindBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_more_token, parent, false);
            holder.iv_logo = convertView.findViewById(R.id.iv_logo);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.sw = convertView.findViewById(R.id.sw);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TokenKindBean kindBean = list.get(position);
        if (null != kindBean) {
            if (kindBean.isCanBeSet()) {
                holder.sw.setVisibility(View.VISIBLE);
                if (kindBean.isSelected()) {
                    holder.sw.setChecked(true);
                } else {
                    holder.sw.setChecked(false);
                }
            } else {
                holder.sw.setVisibility(View.GONE);
            }

            holder.tv_name.setText(kindBean.getName());
            Util.setImageRsForMoreToken(holder.iv_logo, kindBean.getName());
        }

        holder.sw.setOnCheckedChangeListener((buttonView, isChecked) -> {

            iSelectChangeListner.onChange(position, isChecked);
        });


        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        ImageView iv_logo;
        Switch sw;
    }
}
