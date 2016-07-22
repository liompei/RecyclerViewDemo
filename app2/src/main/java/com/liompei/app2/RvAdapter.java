package com.liompei.app2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by BLM on 2016/7/20.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder> {

    private Context context;
    private List<Map<String, String>> mapList;

    public RvAdapter(Context context, List<Map<String, String>> mapList) {
        this.context = context;
        this.mapList = mapList;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RvHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RvHolder holder, int position) {
        holder.textName.setText(mapList.get(position).get("name"));
        holder.textNum.setText(mapList.get(position).get("num"));

        //如果设置了回调，则设置点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击事件
                    onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition(), holder.textName);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //长按事件
                    onItemClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return false;
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return mapList.size();
    }

    class RvHolder extends RecyclerView.ViewHolder {
        private TextView textName, textNum;

        public RvHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textNum = (TextView) itemView.findViewById(R.id.textNum);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, TextView textName);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
