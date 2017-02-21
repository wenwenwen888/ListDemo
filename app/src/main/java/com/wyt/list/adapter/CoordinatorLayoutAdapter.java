package com.wyt.list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyt.list.R;

import java.util.ArrayList;

/**
 * Created by Won on 2017/2/13.
 */

public class CoordinatorLayoutAdapter extends RecyclerView.Adapter<CoordinatorLayoutAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> datas = new ArrayList<>();

    public CoordinatorLayoutAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.coordinatorlayout_list_item, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_coordinator);
        }
    }

}
