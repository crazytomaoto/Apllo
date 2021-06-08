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
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterPaymentAddressDialog extends BaseAdapter {
    private Context context;
    private List<BalanceBean> list;
    private String address;

    public AdapterPaymentAddressDialog(Context context, String address) {
        this.context = context;
        this.address = address;
        list = new ArrayList<>();
    }

    public List<BalanceBean> getList() {
        return list;
    }

    public void setList(List<BalanceBean> list) {
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dialog_payment_address, parent, false);
            holder.tv_balance = convertView.findViewById(R.id.tv_balance);
            holder.iv_select = convertView.findViewById(R.id.iv_select);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_address = convertView.findViewById(R.id.tv_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BalanceBean balanceBean = list.get(position);
        if (null != balanceBean) {
            holder.tv_balance.setText(Util.getStringFromDecimal(balanceBean.getBalance(), 4) + " USDT");
            holder.tv_address.setText(StringUtils.hideMiddleString(balanceBean.getAddress(), 15, 6));
            if (address.equals(balanceBean.getAddress())) {
                holder.iv_select.setVisibility(View.VISIBLE);
            } else {
                holder.iv_select.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_name, tv_address, tv_balance;
        ImageView iv_select;
    }
}
