package com.manlanvideo.cloud.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public  class  BaseViewHolder extends RecyclerView.ViewHolder{

        private ViewPager2 viewPager2;
        private LinkedList<Integer> viewPager2Datas;

        private RecyclerView recyclerView;
        private LinkedList<Integer> recyclerViewDatas;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager2 = itemView.findViewById(R.id.inner_viewpager2);
            viewPager2Datas =new LinkedList<>();
            viewPager2Datas.add(R.drawable.a1);
            viewPager2Datas.add(R.drawable.a2);
            viewPager2Datas.add(R.drawable.a3);
            viewPager2Datas.add(R.drawable.a4);
            viewPager2Datas.add(R.drawable.a5);
            viewPager2Datas.add(R.drawable.a6);
            viewPager2Datas.add(R.drawable.a7);
            viewPager2Datas.add(R.drawable.a8);
            viewPager2Datas.add(R.drawable.a9);
            InnerViewPager2Adapter innerViewPager2Adapter = new InnerViewPager2Adapter(viewPager2Datas);
            viewPager2.setAdapter(innerViewPager2Adapter);
            viewPager2.setUserInputEnabled(true);


            recyclerView = itemView.findViewById(R.id.inner_recyclerview);
            // 设置布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            recyclerViewDatas =new LinkedList<>();
            recyclerViewDatas.add(R.drawable.a1);
            recyclerViewDatas.add(R.drawable.a2);
            recyclerViewDatas.add(R.drawable.a3);
            recyclerViewDatas.add(R.drawable.a4);
            recyclerViewDatas.add(R.drawable.a5);
            recyclerViewDatas.add(R.drawable.a6);
            recyclerViewDatas.add(R.drawable.a7);
            recyclerViewDatas.add(R.drawable.a8);
            recyclerViewDatas.add(R.drawable.a9);
            InnerRecyclerViewAdapter innerRecyclerViewAdapter = new InnerRecyclerViewAdapter(recyclerViewDatas);
            recyclerView.setAdapter(innerRecyclerViewAdapter);
        }
    }
}