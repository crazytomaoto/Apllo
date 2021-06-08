package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sz.apollo.R;
import com.sz.apollo.ui.models.PhraseBackupBean;

import java.util.ArrayList;
import java.util.List;

public class AdapterConfirmPhraseBelow extends BaseAdapter {

    private List<PhraseBackupBean> list;
    private Context context;

    public AdapterConfirmPhraseBelow(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<PhraseBackupBean> getList() {
        return list;
    }

    public void setList(List<PhraseBackupBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_confirm_phrase_below, parent, false);
            holder.tv_phrase = convertView.findViewById(R.id.tv_phrase);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PhraseBackupBean bean = list.get(position);
        if (null != bean) {
            holder.tv_phrase.setText(bean.getCharacter());
            if (bean.isSelected() == true) {
                holder.tv_phrase.setBackgroundResource(R.drawable.radius_14_bottom);
                holder.tv_phrase.setTextColor(context.getResources().getColor(R.color.gray3));
            } else {
                holder.tv_phrase.setBackgroundResource(R.drawable.radius_14_black1);
                holder.tv_phrase.setTextColor(context.getResources().getColor(R.color.white));
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_phrase;
    }

}