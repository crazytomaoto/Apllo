package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.WalletTypeBean;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterChooseWalletType extends BaseAdapter {
    private Context context;
    private List<WalletTypeBean> list;
    private int selectedposition = 0;

    public void setSelectedposition(int selectedposition) {
        this.selectedposition = selectedposition;
        notifyDataSetChanged();
    }

    public AdapterChooseWalletType(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<WalletTypeBean> getList() {
        return list;
    }

    public void setList(List<WalletTypeBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choose_wallet_type, parent, false);
            holder.iv_logo = convertView.findViewById(R.id.iv_logo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WalletTypeBean bean = list.get(position);
        if (null != bean) {
            Util.setImageRs(holder.iv_logo, bean.getName(), bean.isSelected());
            if (selectedposition == position) {
                Util.setImageRs(holder.iv_logo, bean.getName(), true);
            } else {
                Util.setImageRs(holder.iv_logo, bean.getName(), false);
            }
        }
        return convertView;
    }

    class ViewHolder {
        ImageView iv_logo;
    }
}
