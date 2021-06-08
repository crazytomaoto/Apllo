package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.models.UserWalletBean;
import com.sz.apollo.ui.utils.DaoUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterWalletList extends BaseAdapter {
    private Context context;
    private List<UserWalletBean> list;

    public AdapterWalletList(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<UserWalletBean> getList() {
        return list;
    }

    public void setList(List<UserWalletBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choose_wallet, parent, false);
            holder.rl_father = convertView.findViewById(R.id.rl_father);
            holder.tv_address = convertView.findViewById(R.id.tv_address);
            holder.tv_type = convertView.findViewById(R.id.tv_type);
            holder.iv = convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UserWalletBean bean = list.get(position);
        if (null != bean) {
            holder.tv_type.setText(bean.getRemark());
            holder.tv_address.setText(bean.getAddress());
            if (bean.getType().equals(Constant.TYPE_APOLLO)) {
                holder.rl_father.setBackgroundResource(R.drawable.bg_apl);
                holder.tv_address.setText(bean.getAddress());
            } else {
                holder.rl_father.setBackgroundResource(R.drawable.bg_eth);
                holder.tv_address.setText(StringUtils.hideMiddleString(bean.getAddress(), 25, 10));
            }
            if (DaoUtil.selectNowWallet().getAddress().equals(bean.getAddress())) {
                holder.iv.setVisibility(View.VISIBLE);
            } else {
                holder.iv.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_address, tv_type;
        RelativeLayout rl_father;
        ImageView iv;
    }
}
