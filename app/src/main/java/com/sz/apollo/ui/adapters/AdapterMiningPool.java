package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.ResonanceBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterMiningPool extends BaseAdapter {
    private Context context;
    private List<ResonanceBean.DataBeanX.DataBean> list;

    public AdapterMiningPool(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<ResonanceBean.DataBeanX.DataBean> getList() {
        return list;
    }

    public void setList(List<ResonanceBean.DataBeanX.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mining_pool, parent, false);
            holder.tv_cost = convertView.findViewById(R.id.tv_cost);
            holder.tv_percentage = convertView.findViewById(R.id.tv_percentage);
            holder.tv_all = convertView.findViewById(R.id.tv_all);
            holder.progressBar = convertView.findViewById(R.id.progress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResonanceBean.DataBeanX.DataBean bean = list.get(position);
        if (bean != null) {
            double percentage = bean.getSurplusPercent() * 100;
            holder.tv_percentage.setText(Util.getStringFromDecimal(percentage, 2) + "%");
            holder.progressBar.setProgress((int) percentage);

            holder.tv_cost.setText(Util.getStringFromDecimal(bean.getPrice(), 4)+"USDT");
            holder.tv_all.setText(Util.getStringFromDecimal(bean.getTotalAmount(), 0));

        }

        return convertView;
    }

    class ViewHolder {
        ProgressBar progressBar;
        TextView tv_cost, tv_percentage, tv_all;
    }
}
