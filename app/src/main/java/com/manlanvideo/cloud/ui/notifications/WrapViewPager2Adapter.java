package com.manlanvideo.cloud.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.manlanvideo.cloud.R;

import java.util.LinkedList;

public class WrapViewPager2Adapter extends RecyclerView.Adapter<WrapViewPager2Adapter.BaseViewHolder>{

    LinkedList<Integer> datas;

    public WrapViewPager2Adapter(LinkedList<Integer> datas) {
        this.datas = datas;
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrap_viewpager2_item,parent,false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.imageView.setImageResource(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public  class  BaseViewHolder extends RecyclerView.ViewHolder{

        ViewPager2 viewPager2;
        private LinkedList<Integer> datas;

        ImageView imageView;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageview);
            viewPager2 = itemView.findViewById(R.id.inner_viewpager2);
            datas =new LinkedList<>();
            datas.add(R.drawable.a1);
            datas.add(R.drawable.a2);
            datas.add(R.drawable.a3);
            datas.add(R.drawable.a4);
            datas.add(R.drawable.a5);
            datas.add(R.drawable.a6);
            datas.add(R.drawable.a7);
            datas.add(R.drawable.a8);
            datas.add(R.drawable.a9);
            InnerViewPager2Adapter baseAdapter =new InnerViewPager2Adapter(datas);
            viewPager2.setAdapter(baseAdapter);
            viewPager2.setUserInputEnabled(true);
        }
    }
}