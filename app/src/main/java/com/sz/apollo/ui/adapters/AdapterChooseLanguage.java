package com.sz.apollo.ui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.LanguageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择语言适配器
 */
public class AdapterChooseLanguage extends BaseAdapter {
    private Activity context;
    private List<LanguageBean> list;
    private int selectedposition = 0;

    public AdapterChooseLanguage(Activity context) {
        this.context = context;
        list = new ArrayList<>();
    }


    public void setList(List<LanguageBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setSelectedposition(int selectedposition) {
        this.selectedposition = selectedposition;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choose_language, parent, false);
            holder.tv_language = convertView.findViewById(R.id.tv_language);
            holder.ll_item = convertView.findViewById(R.id.ll_item);
            holder.iv_right = convertView.findViewById(R.id.iv_right);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_language.setText(list.get(position).getLanguage());
        if (selectedposition == position) {
            holder.tv_language.setTextColor(context.getResources().getColor(R.color.text_green2));
            holder.iv_right.setVisibility(View.VISIBLE);
        } else {
            holder.tv_language.setTextColor(context.getResources().getColor(R.color.white2));
            holder.iv_right.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_language;
        RelativeLayout ll_item;
        ImageView iv_right;
    }

}