package com.manlanvideo.cloud.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.manlanvideo.cloud.R;

import java.util.LinkedList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private ViewPager2 viewPager2;
    private LinkedList<Integer> datas;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context =  inflater.getContext();
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        viewPager2 = root.findViewById(R.id.wrap_viewpager2);

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



        WrapViewPager2Adapter baseAdapter =new WrapViewPager2Adapter(datas);
        viewPager2.setAdapter(baseAdapter);
        // 用户手势滑动
        viewPager2.setUserInputEnabled(true);

        return root;
    }

}