package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<BalanceBean> list;
    private String noType;

    public void setNoType(String noType) {
        this.noType = noType;
    }

    public HomeAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<BalanceBean> getList() {
        return list;
    }

    public void setList(List<BalanceBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_wallet, parent, false);
            holder.iv_logo = convertView.findViewById(R.id.iv_logo);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_balance = convertView.findViewById(R.id.tv_balance);
            holder.tv_equivalent = convertView.findViewById(R.id.tv_equivalent);
            holder.line = convertView.findViewById(R.id.line);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BalanceBean balanceBean = list.get(position);
        if (null != balanceBean) {
            holder.tv_balance.setText(Util.getStringFromDecimal(balanceBean.getBalance(), 4));
            holder.tv_name.setText(balanceBean.getType());
            if (!noType.equals(Constant.TYPE_APOLLO)) {
                holder.tv_equivalent.setVisibility(View.GONE);
            } else {
                holder.tv_equivalent.setVisibility(View.VISIBLE);
                holder.tv_equivalent.setText("$ " + Util.getStringFromDecimal(balanceBean.getBalanceToU(), 4));
            }
        }
        if (position == list.size() - 1) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }
        Util.setImageRsForMoreToken(holder.iv_logo, balanceBean.getType());
        return convertView;
    }

    class ViewHolder {
        TextView tv_name, tv_balance, tv_equivalent;
        ImageView iv_logo;
        View line;
    }
}
