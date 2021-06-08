package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.ContactAddressBean;

import java.util.ArrayList;
import java.util.List;

public class AdapterAddressBook extends BaseAdapter {
    private Context context;
    private List<ContactAddressBean> list;

    public AdapterAddressBook(Context context) {
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_address, parent, false);
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
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_name, tv_address;
    }
}
