package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.TimePowerBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterDatePowerDetail extends BaseAdapter {
    private Context context;
    private List<TimePowerBean.DataBean.TimeRulesBean> list;

    public AdapterDatePowerDetail(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<TimePowerBean.DataBean.TimeRulesBean> getList() {
        return list;
    }

    public void setList(List<TimePowerBean.DataBean.TimeRulesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TimePowerBean.DataBean.TimeRulesBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_date_power_detail, parent, false);
            holder.tv_time = convertView.findViewById(R.id.tv_time);
            holder.tv_value = convertView.findViewById(R.id.tv_value);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TimePowerBean.DataBean.TimeRulesBean timeRulesBean = list.get(position);
        if (null != timeRulesBean) {
            if (position == list.size() - 1) {
                holder.tv_time.setText((position * 6) + "≤" + context.getString(R.string.tip201) + "<" + "n");
            } else {
                holder.tv_time.setText((position * 6) + "≤" + context.getString(R.string.tip201) + "<" + (position + 1) * 6);
            }
            holder.tv_value.setText(Util.getStringFromDecimal(Double.parseDouble(timeRulesBean.getCodeValue2()) * 100, 0) + "%");
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_time, tv_value;
    }
}
