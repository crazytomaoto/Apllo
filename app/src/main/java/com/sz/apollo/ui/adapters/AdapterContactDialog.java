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
import com.sz.apollo.ui.models.ContactAddressBean;

import java.util.ArrayList;
import java.util.List;

public class AdapterContactDialog extends BaseAdapter {
    private Context context;
    private List<ContactAddressBean> list;
    private String nowAddress;

    public AdapterContactDialog(Context context, String nowAddress) {
        this.context = context;
        this.nowAddress = nowAddress;
        list = new ArrayList<>();
    }

    public List<ContactAddressBean> getList() {
        return list;
    }

    public void setList(List<ContactAddressBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dialog_contact, parent, false);
            holder.iv_logo = convertView.findViewById(R.id.iv_logo);
            holder.iv_select = convertView.findViewById(R.id.iv_select);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_address = convertView.findViewById(R.id.tv_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContactAddressBean bean = list.get(position);
        if (null != bean) {
            holder.tv_name.setText(bean.getRemark());
            holder.tv_address.setText(bean.getAddress());
            if (!StringUtils.isEmpty(nowAddress) && nowAddress.equals(bean.getAddress())) {
                holder.iv_select.setVisibility(View.VISIBLE);
            } else {
                holder.iv_select.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_name, tv_address;
        ImageView iv_logo, iv_select;
    }
}
