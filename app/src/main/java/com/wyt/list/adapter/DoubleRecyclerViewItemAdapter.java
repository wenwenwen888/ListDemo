package com.wyt.list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyt.list.R;
import com.wyt.list.assist.IOnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Won on 2016/12/22.
 */

public class DoubleRecyclerViewItemAdapter extends RecyclerView.Adapter<DoubleRecyclerViewItemAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> infos = new ArrayList<>();

    public DoubleRecyclerViewItemAdapter(Context context, ArrayList<String> infos) {
        this.context = context;
        this.infos = infos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.double_recycler_view_item_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(infos.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_double_recyclerView_info_item);
        }
    }

    private IOnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(IOnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
