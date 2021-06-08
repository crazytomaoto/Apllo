package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.BasePowerBean;
import com.sz.apollo.ui.utils.TimeUtil;
import com.sz.apollo.ui.utils.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdapterBasisPowerDetail extends BaseAdapter {
    private Context context;
    private List<BasePowerBean.DataBeanX.DataBean> list;

    public AdapterBasisPowerDetail(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<BasePowerBean.DataBeanX.DataBean> getList() {
        return list;
    }

    public void setList(List<BasePowerBean.DataBeanX.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BasePowerBean.DataBeanX.DataBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_basis_power_detail, parent, false);
            holder.tv_a = convertView.findViewById(R.id.tv_name_a);
            holder.tv_b = convertView.findViewById(R.id.tv_name_b);
            holder.tv_date = convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BasePowerBean.DataBeanX.DataBean dataBean = list.get(position);
        if (null != dataBean) {
            holder.tv_a.setText(Util.getStringFromDecimal(dataBean.getValidBalance(), 2));
            holder.tv_b.setText(Util.getStringFromDecimal(dataBean.getCommonPower(), 2));
            holder.tv_date.setText(TimeUtil.getTime(dataBean.getCreatedAt()));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_a, tv_b, tv_date;
    }
}
