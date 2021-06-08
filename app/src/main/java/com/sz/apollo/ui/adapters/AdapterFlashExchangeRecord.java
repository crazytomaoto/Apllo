package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.ui.models.FlashRecordBean;
import com.sz.apollo.ui.utils.TimeUtil;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterFlashExchangeRecord extends BaseAdapter {
    private Context context;
    private List<FlashRecordBean.DataBeanX.DataBean> list;
    private String symbol;

    public AdapterFlashExchangeRecord(Context context, String symbol) {
        this.context = context;
        this.symbol = symbol;
        list = new ArrayList<>();
    }

    public List<FlashRecordBean.DataBeanX.DataBean> getList() {
        return list;
    }

    public void setList(List<FlashRecordBean.DataBeanX.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_flash_exchange_record, parent, false);
            holder.tv_address = convertView.findViewById(R.id.tv_address);
            holder.tv_count = convertView.findViewById(R.id.tv_count);
            holder.tv_change_number = convertView.findViewById(R.id.tv_change_number);
            holder.tv_date = convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FlashRecordBean.DataBeanX.DataBean dataBean = list.get(position);

        if (null != dataBean) {
            holder.tv_address.setText(StringUtils.hideMiddleString(dataBean.getFromAddress(), 11, 4));
            holder.tv_count.setText(Util.getStringFromDecimal(dataBean.getAmount(), 2) + " USDT");
            holder.tv_change_number.setText(Util.getStringFromDecimal(dataBean.getLastAmount(), 2) + " " + symbol);
            holder.tv_date.setText(TimeUtil.getTime(dataBean.getCreatedAt()));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_address, tv_count, tv_change_number, tv_date;
    }
}
