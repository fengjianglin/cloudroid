package com.manlanvideo.cloud.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manlanvideo.cloud.R;

import java.util.LinkedList;

public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.BaseViewHolder>{

    LinkedList<String> datas;

    public InnerRecyclerViewAdapter(LinkedList<String> datas) {
        this.datas = datas;
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_recyclerview_item, parent,false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public  class  BaseViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.inner_recyclerview_textview);
        }
    }
}