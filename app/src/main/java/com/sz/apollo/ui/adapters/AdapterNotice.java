package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.NoticeBean;
import com.sz.apollo.ui.models.TokenKindBean;
import com.sz.apollo.ui.utils.TimeUtil;
import com.sz.apollo.ui.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AdapterNotice extends BaseAdapter {
    private Context context;
    private List<NoticeBean.DataBeanX.DataBean> list;


    public AdapterNotice(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<NoticeBean.DataBeanX.DataBean> getList() {
        return list;
    }

    public void setList(List<NoticeBean.DataBeanX.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false);
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_date = convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NoticeBean.DataBeanX.DataBean bean = list.get(position);
        if (null != bean) {
            holder.tv_title.setText(bean.getTitle());
            holder.tv_date.setText(TimeUtil.getTime(bean.getCreatedAt()));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_title, tv_date;
    }
}
