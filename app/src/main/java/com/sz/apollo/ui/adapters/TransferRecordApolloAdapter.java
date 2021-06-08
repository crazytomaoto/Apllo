package com.sz.apollo.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.EthTransRecordBean;
import com.sz.apollo.ui.models.TransRecordBean;
import com.sz.apollo.ui.utils.TimeUtil;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class TransferRecordApolloAdapter extends BaseAdapter {
    private Context context;
    private List<TransRecordBean.DataBeanX.DataBean> list;

    public TransferRecordApolloAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<TransRecordBean.DataBeanX.DataBean> getList() {
        return list;
    }

    public void setListApollo(List<TransRecordBean.DataBeanX.DataBean> list) {
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

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_transfer_record, parent, false);
            holder.tv_address = convertView.findViewById(R.id.tv_address);
            holder.tv_date = convertView.findViewById(R.id.tv_date);
            holder.tv_amount = convertView.findViewById(R.id.tv_amount);
            holder.tv_state = convertView.findViewById(R.id.tv_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TransRecordBean.DataBeanX.DataBean dataBean = list.get(position);
        if (null != dataBean) {
            holder.tv_date.setText(TimeUtil.getTime(dataBean.getCreatedAt()));
            String otherAddress = dataBean.getOtherAddress();
            holder.tv_address.setText(otherAddress);
            int type = dataBean.getType();//转账类型（1：转入 2：转出）
            if (type == 1) {
                holder.tv_amount.setTextColor(context.getColor(R.color.text_green));
                holder.tv_amount.setText("+" + Util.getStringFromDecimal(dataBean.getAmount(), 4));
            }
            if (type == 2) {
                holder.tv_amount.setTextColor(context.getColor(R.color.color_red2));
                holder.tv_amount.setText("-" + Util.getStringFromDecimal(dataBean.getAmount(), 4));
            }

            if (dataBean.getState() == 1) {
                holder.tv_state.setText(R.string.tip147);
            }
            if (dataBean.getState() == 0) {
                holder.tv_state.setText(R.string.tip148);
            }
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_address, tv_date, tv_amount, tv_state;
    }
}
