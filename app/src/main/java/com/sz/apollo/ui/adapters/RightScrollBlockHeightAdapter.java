package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.ui.models.RecordBlockHashBean;

import java.util.List;

/**
 *
 */
public class RightScrollBlockHeightAdapter extends RecyclerView.Adapter<RightScrollBlockHeightAdapter.ScrollViewHolder> {


    private Context context;
    private List<RecordBlockHashBean.DataBeanX.ListBean> rightDatas;

    public RightScrollBlockHeightAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<RecordBlockHashBean.DataBeanX.ListBean> rightDatas) {
        this.rightDatas = rightDatas;
        notifyDataSetChanged();
    }

    public interface OnRawClickListener {
        void onRawItemClick(View view, int position);
    }

    private OnRawClickListener onRawClickListener;

    public OnRawClickListener getOnRawClickListener() {
        return onRawClickListener;
    }

    public void setOnRawClickListener(OnRawClickListener onRawClickListener) {
        this.onRawClickListener = onRawClickListener;
    }

    @NonNull
    @Override
    public ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_scroll_block_height, viewGroup, false);
        return new ScrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder scrollViewHolder, int i) {
        if (null != rightDatas) {
            RecordBlockHashBean.DataBeanX.ListBean listBean = rightDatas.get(i);
            ScrollViewHolder vh = scrollViewHolder;
            vh.tv_check.setTag(i);
            if (null != listBean) {
                scrollViewHolder.txHash.setText(StringUtils.hideMiddleString(listBean.getHash(), 13, 5));
                scrollViewHolder.tv_state.setText(listBean.getStatus());
                scrollViewHolder.tv_action_number.setText(listBean.getActionNum() + "");
                scrollViewHolder.tv_time.setText(listBean.getTime());
//                scrollViewHolder.tv_check.setText(listBean.getActions());
            }
        }
    }

    @Override
    public int getItemCount() {
        return null == rightDatas ? 0 : rightDatas.size();
    }

    class ScrollViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txHash, tv_state, tv_action_number, tv_time, tv_check;

        public ScrollViewHolder(@NonNull View itemView) {
            super(itemView);
            txHash = itemView.findViewById(R.id.tv_txHash);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_action_number = itemView.findViewById(R.id.tv_action_number);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_check = itemView.findViewById(R.id.tv_check);
            tv_check.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRawClickListener != null) {
                // 使用getTag方法获取position
                int position = (int) view.getTag();
                switch (view.getId()) {
                    case R.id.tv_check:
                        onRawClickListener.onRawItemClick(view, position);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
