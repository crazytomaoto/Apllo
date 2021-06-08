package com.sz.apollo.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.MineRewardBean;
import com.sz.apollo.ui.utils.ClickUtil;
import com.sz.apollo.ui.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterProfitAocDetail extends BaseAdapter {
    private Context context;
    private List<MineRewardBean.DataBeanX.DataBean> list;
    private IGetProfit iGetProfit;


    public interface IGetProfit {
        void getProfit(int position);
    }

    public IGetProfit getiGetProfit() {
        return iGetProfit;
    }

    public void setiGetProfit(IGetProfit iGetProfit) {
        this.iGetProfit = iGetProfit;
    }

    public AdapterProfitAocDetail(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<MineRewardBean.DataBeanX.DataBean> getList() {
        return list;
    }

    public void setList(List<MineRewardBean.DataBeanX.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_profit_detail, parent, false);
            holder.tv_type = convertView.findViewById(R.id.tv_type);
            holder.tv_count = convertView.findViewById(R.id.tv_count);
            holder.tv_date = convertView.findViewById(R.id.tv_date);
            holder.tv_state = convertView.findViewById(R.id.tv_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MineRewardBean.DataBeanX.DataBean dataBean = list.get(position);
        if (null != dataBean) {
            holder.tv_type.setText(dataBean.getRewardSymbol());
            holder.tv_count.setText("+ " + dataBean.getRewardAmount() + " " + dataBean.getRewardSymbol());
            holder.tv_date.setText(TimeUtil.getTime2(dataBean.getCreatedAt()));
            int state = dataBean.getState();
            if (state == 0) {
                holder.tv_state.setText(context.getString(R.string.tip198));
                holder.tv_state.setEnabled(true);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setTextColor(context.getColor(R.color.white));
            }
            if (state == 1) {
                holder.tv_state.setText(context.getString(R.string.tip199));
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setEnabled(false);
                holder.tv_state.setTextColor(context.getColor(R.color.gray2));
            }
            if (state == 2) {
                holder.tv_state.setText(context.getString(R.string.tip200));
                holder.tv_state.setEnabled(false);
                holder.tv_state.setVisibility(View.GONE);
            }
            holder.tv_state.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isNotFirstClick()) {
                        return;
                    }
                    if (dataBean.getState() == 0) {
                        iGetProfit.getProfit(position);
                    }
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_type, tv_count, tv_date, tv_state;
    }
}
