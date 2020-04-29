package com.manlanvideo.cloud.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.manlanvideo.cloud.R;
import com.squareup.picasso.Picasso;

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

        private String[][] testdata = { //
                {"http://s-dev.manlanvideo.com/clips/一分钟急救系列微课——吃东西被噎.mp4","http://s-dev.manlanvideo.com/covers/a1.jpg"},
                {"http://s-dev.manlanvideo.com/clips/一分钟急救系列微课——呼吸停止.mp4","http://s-dev.manlanvideo.com/covers/a2.jpg"},
                {"http://s-dev.manlanvideo.com/clips/一分钟急救系列微课——踝扭伤后不能走.mp4","http://s-dev.manlanvideo.com/covers/a3.jpg"},
                {"http://s-dev.manlanvideo.com/clips/一分钟急救系列微课——外伤出血.mp4","http://s-dev.manlanvideo.com/covers/a4.jpg"},
                {"http://s-dev.manlanvideo.com/clips/一分钟急救系列微课——心跳呼吸全无（心肺复苏）.mp4","http://s-dev.manlanvideo.com/covers/a5.jpg"},
                {"http://s-dev.manlanvideo.com/clips/一分钟急救系列微课——心跳停止（胸外按压）.mp4","http://s-dev.manlanvideo.com/covers/a6.jpg"}
        };

        private String[] lytestdata = {
                "Kaiser: 随便说说，欢迎探讨剧情。",
                "匿名: 人才济济的北宋-陈桥兵变后，赵匡胤一手杯酒释兵权，言与士大夫共天下，天下就并非赵家的天下了，而是赵家与士大夫的天下，然文臣位",
                "感梦！感想！敢拼: 又是湖南卫视。70集。何年何月才完",
                "冷情緒: 琅琊榜才是精品，也是经典",
                "Kaiser: 🔊 33\"",
                "匿名: 人才济济的北宋-陈桥兵变后，赵匡胤一手杯酒释兵权，言与士大夫共天下，天下就并非赵家的天下了，而是赵家与士大夫的天下，然文臣位",
                "感梦！感想！敢拼: 又是湖南卫视。70集。何年何月才完",
                "冷情緒: 🔊 1'53\"",
                "Kaiser: 随便说说，欢迎探讨剧情。",
                "匿名: 人才济济的北宋-陈桥兵变后，赵匡胤一手杯酒释兵权，言与士大夫共天下，天下就并非赵家的天下了，而是赵家与士大夫的天下，然文臣位",
                "感梦！感想！敢拼: 又是湖南卫视。70集。何年何月才完",
                "冷情緒: 琅琊榜才是精品，也是经典"
        };

        private ViewPager2 viewPager2;
        private TabLayout tabLayout;
        private LinkedList<Data> viewPager2Datas;

        private TextView titleTextView;

        private RecyclerView recyclerView;
        private LinkedList<String> recyclerViewDatas;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager2 = itemView.findViewById(R.id.inner_viewpager2);
            viewPager2Datas = new LinkedList<>();
            for(String[] d : testdata) {
                Data data = new Data();
                data.videoUrl = d[0];
                data.coverUrl = d[1];
                viewPager2Datas.add(data);
            }
            InnerViewPager2Adapter innerViewPager2Adapter = new InnerViewPager2Adapter(viewPager2Datas);
            viewPager2.setAdapter(innerViewPager2Adapter);
            viewPager2.setUserInputEnabled(true);
            tabLayout = itemView.findViewById(R.id.inner_tabs);
            new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    View itemView = LayoutInflater.from(viewPager2.getContext()).inflate(R.layout.inner_tabview, null,false);
                    ImageView imageView = itemView.findViewById(R.id.inner_tabview_imageview);
                    Picasso.get().load(viewPager2Datas.get(position).coverUrl).into(imageView);
                    tab.setCustomView(itemView);
                }
            }).attach();
            titleTextView = itemView.findViewById(R.id.playtitle);
            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    titleTextView.setText("播放中：" + viewPager2Datas.get(position).videoUrl.substring(35));
                    innerViewPager2Adapter.stop();
                }
            });

            recyclerView = itemView.findViewById(R.id.inner_recyclerview);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            recyclerViewDatas = new LinkedList<>();
            for(String s : lytestdata) {
                recyclerViewDatas.add(s);
            }
            InnerRecyclerViewAdapter innerRecyclerViewAdapter = new InnerRecyclerViewAdapter(recyclerViewDatas);
            recyclerView.setAdapter(innerRecyclerViewAdapter);
        }

        public class Data {
            public String videoUrl;
            public String coverUrl;
        }
    }
}