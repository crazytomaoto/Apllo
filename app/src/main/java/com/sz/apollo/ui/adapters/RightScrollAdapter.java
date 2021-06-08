package com.sz.apollo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;

import java.util.List;

/**
 * @author：JianFeng
 * @date：2019/3/26 16:17
 * @description：
 */
public class RightScrollAdapter extends RecyclerView.Adapter<RightScrollAdapter.ScrollViewHolder> {


    private Context context;
    private List<JSONObject> rightDatas;
    private OnContentScrollListener onContentScrollListener;

    public RightScrollAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<JSONObject> rightDatas) {
        this.rightDatas = rightDatas;
        notifyDataSetChanged();
    }

    public interface OnContentScrollListener {
        void onScroll(int offestX);
    }

    public void setOnContentScrollListener(OnContentScrollListener onContentScrollListener) {
        this.onContentScrollListener = onContentScrollListener;
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_scroll, viewGroup, false);
        return new ScrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder scrollViewHolder, int i) {
        if (null != rightDatas) {
            JSONObject listBean = rightDatas.get(i);
            ScrollViewHolder vh = scrollViewHolder;
            vh.tv_check.setTag(i);
            if (null != listBean) {
                scrollViewHolder.tvTxHash.setText(StringUtils.hideMiddleString(listBean.getString("blockHash"), 13, 5));
                scrollViewHolder.tvTransType.setText(listBean.getString("type"));
                scrollViewHolder.tv_block_height.setText(listBean.getInteger("height") + "");
                scrollViewHolder.tv_from.setText(listBean.getString("from"));
                scrollViewHolder.tv_to.setText(listBean.getString("to"));
                scrollViewHolder.tv_amount.setText(listBean.getString("quantity"));
                scrollViewHolder.tvTokenType.setText(listBean.getString("token"));
                JSONObject jsonObject = listBean.getJSONObject("data");
                if (null != jsonObject) {
                    String data = "";
                    data = data + "{\n" +
                            "from:\"" + jsonObject.getString("from") + "\",\n" +
                            "to:\"" + jsonObject.getString("to") + "\",\n" +
                            "quantity:\"" + jsonObject.getString("quantity") + "\",\n" +
                            "gas:\"" + jsonObject.getString("gas") + "\",\n" +
                            "memo:\"" + jsonObject.getString("memo") + "\",\n" + "}";
                    scrollViewHolder.tv_data.setText(data);
                }
                scrollViewHolder.tv_date.setText(listBean.getString("blockTime"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return null == rightDatas ? 0 : rightDatas.size();
    }

    class ScrollViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_from, tvTxHash, tvTransType, tv_block_height, tv_to, tvTokenType, tv_amount, tv_data, tv_date, tv_check;

        public ScrollViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTxHash = itemView.findViewById(R.id.tvTxHash);
            tvTransType = itemView.findViewById(R.id.tvTransType);
            tv_block_height = itemView.findViewById(R.id.tv_block_height);
            tv_from = itemView.findViewById(R.id.tv_from);
            tv_to = itemView.findViewById(R.id.tv_to);
            tvTokenType = itemView.findViewById(R.id.tvTokenType);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            tv_data = itemView.findViewById(R.id.tv_data);
            tv_date = itemView.findViewById(R.id.tv_date);
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
