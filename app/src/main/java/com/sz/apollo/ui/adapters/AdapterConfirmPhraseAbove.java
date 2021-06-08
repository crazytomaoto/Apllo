package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.sz.apollo.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterConfirmPhraseAbove extends BaseAdapter {

    private List<String> list;
    private Context context;

    public AdapterConfirmPhraseAbove(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_confirm_phrase_above, parent, false);
            holder.tv_phrase = convertView.findViewById(R.id.tv_phrase);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_phrase.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView tv_phrase;
    }

}