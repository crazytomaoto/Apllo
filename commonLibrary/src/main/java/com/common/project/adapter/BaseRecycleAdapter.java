package com.common.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecycleView基类适配器
 * 没有应用dataBinding
 * @param <T>
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    protected List<T> mDatas;
    protected Context mContext;
    private int mLayoutId;

    public BaseRecycleAdapter(Context context,int layoutId, List<T> datas) {
        this.mDatas = datas;
        this.mLayoutId = layoutId;
        this.mContext = context;
    }

    public void setData(List<T> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public final void onBindViewHolder(RecyclerHolder holder, final int position) {

        //设置item点击事件
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.setOnItemClickListener(v, mDatas.get(position), position);
                }
            }
        });
        //设置item长按点击事件
        holder.mRootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mLongListener == null || mLongListener.setOnItemLongClickListener(v, position);
            }
        });

        T t = mDatas.get(position);
        onBindViewHolder(holder, t, position);
    }

    public abstract void onBindViewHolder(RecyclerHolder recyclerHolder, T itemBean, int position);

    @Override
    public int getItemCount() {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.size();
        }
        return 0;
    }

    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongListener;

    public BaseRecycleAdapter setItemCliskListener(OnItemClickListener listener) {
        if (listener != null) {
            this.mListener = listener;
        }
        return this;
    }

    public void setItemLongClickListener(OnItemLongClickListener listener) {
        if (listener != null) {
            this.mLongListener = listener;
        }
    }

    public interface OnItemClickListener<T> {
        void setOnItemClickListener(View v, T t, int position);
    }

    public interface OnItemLongClickListener {
        boolean setOnItemLongClickListener(View v, int position);
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
    }

    /**
     * 更新数据
     *
     * @param datas 数据
     */
    public void onRefreshData(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    /**
     * 增加数据
     *
     * @param datas 数据
     */
    public void addMoreData(List<T> datas) {
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearData() {
        this.mDatas.clear();
        notifyDataSetChanged();
    }
}

