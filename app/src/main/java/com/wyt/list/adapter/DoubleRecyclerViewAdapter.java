package com.wyt.list.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyt.list.R;
import com.wyt.list.assist.GridDividerItemDecoration;
import com.wyt.list.assist.IOnItemClickListener;
import com.wyt.list.model.DoubleRecyclerViewModel;

import java.util.ArrayList;

/**
 * Created by Won on 2016/12/22.
 */

public class DoubleRecyclerViewAdapter extends RecyclerView.Adapter<DoubleRecyclerViewAdapter.MyViewHolder> implements IOnItemClickListener {

    private static final String TAG = "DoubleRecyclerViewAdapt";

    private Context context;
    private ArrayList<DoubleRecyclerViewModel> doubleRecyclerViewModels;

    public DoubleRecyclerViewAdapter(Context context, ArrayList<DoubleRecyclerViewModel> doubleRecyclerViewModels) {
        this.context = context;
        this.doubleRecyclerViewModels = doubleRecyclerViewModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.double_recycler_view_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(doubleRecyclerViewModels.get(position).getInfo());
        DoubleRecyclerViewItemAdapter doubleRecyclerViewItemAdapter = new DoubleRecyclerViewItemAdapter(context, doubleRecyclerViewModels.get(position).getInfos());
        holder.recyclerView.setAdapter(doubleRecyclerViewItemAdapter);

        doubleRecyclerViewItemAdapter.setOnItemClickListener(this);

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
        return doubleRecyclerViewModels.size();
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e(TAG, "position: " + position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_double_recyclerView_info);
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_double_recyclerView_item);
            //recyclerView设置属性
            recyclerView.setLayoutManager(new LinearLayoutManager(context));//list类型
            recyclerView.addItemDecoration(new GridDividerItemDecoration(context));//下划线
        }
    }

    private IOnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(IOnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
