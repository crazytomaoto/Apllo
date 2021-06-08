package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.NboMiningPowerBean;
import com.sz.apollo.ui.models.NboPollBean;
import com.sz.apollo.ui.models.ResonanceBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterMinersNBO extends BaseAdapter {
    private Context context;
    private List<NboMiningPowerBean.DataBeanX.DataBean> list;

    public AdapterMinersNBO(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<NboMiningPowerBean.DataBeanX.DataBean> getList() {
        return list;
    }

    public void setList(List<NboMiningPowerBean.DataBeanX.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_miner_nbo, parent, false);
            holder.tv_account = convertView.findViewById(R.id.tv_account);
            holder.tv_own = convertView.findViewById(R.id.tv_own);
            holder.tv_number = convertView.findViewById(R.id.tv_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NboMiningPowerBean.DataBeanX.DataBean teamBean = list.get(position);
        if (null != teamBean) {
            holder.tv_account.setText(teamBean.getChildAddress());
            holder.tv_own.setText(Util.getStringFromDecimal(teamBean.getTotalAoc(), 4));
            holder.tv_number.setText(teamBean.getMiningCount() + "");
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_account, tv_own, tv_number;
    }
}
