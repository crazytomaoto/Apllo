package com.common.project.adapter.databinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.common.project.base.CommonActivity;

import java.util.List;

/**
 * RecyclerView公共Adapter
 *
 * @author chenjunshan 17-3-9.
 */

public abstract class CommonCycAdapter<T extends ViewDataBinding, D> extends RecyclerView.Adapter<CommonCycAdapter.CommonHolder> {
    public Context context;
    /**布局*/
    public int res;
    /**数据*/
    public List<D> datas;

    public CommonCycAdapter(Context context, int res, List<D> datas) {
        this.context = context;
        this.res = res;
        this.datas = datas;
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        T t = DataBindingUtil.inflate(LayoutInflater.from(context), res, parent, false);
        CommonHolder holder;
        holder = new CommonHolder(t.getRoot());
        holder.setT(t);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, final int position) {
        getItemBinding((T) holder.getT(), datas.get(position), position);
        holder.getT().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position <= datas.size() - 1 && position >= 0) {
                    onItemClickListener(datas.get(position), position);
                }
            }
        });
        getHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    protected abstract void getItemBinding(T binding, D bean, int position);

    protected abstract void onItemClickListener(D bean, int position);

    public void getHolder(CommonHolder holder, int position) {
    }

    public static class CommonHolder extends RecyclerView.ViewHolder {
        ViewDataBinding t;

        public CommonHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getT() {
            return t;
        }

        public void setT(ViewDataBinding t) {
            this.t = t;
        }
    }


    public void startFragment(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CommonActivity.FRAGMENT_CLASS, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    protected String initText(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return string;
    }

    public List<D> getDatas() {
        return datas;
    }

    public void setDatas(List<D> datas) {
        this.datas = datas;
    }

    /**
     * 更新数据
     *
     * @param datas
     */
    public void onRefreshData(List<D> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * 增加数据
     *
     * @param datas
     */
    public void addMoreData(List<D> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearData() {
        this.datas.clear();
        notifyDataSetChanged();
    }
}
