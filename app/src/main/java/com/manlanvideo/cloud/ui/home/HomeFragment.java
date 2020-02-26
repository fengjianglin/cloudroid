package com.manlanvideo.cloud.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.manlanvideo.cloud.R;

import java.net.URI;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final int FLING_MIN_DISTANCE = 50;

    private  GestureDetector.SimpleOnGestureListener myGestureListener;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final FrameLayout videoFrame = root.findViewById(R.id.videoFrame);
        final VideoView videoView1 = requestVideoView(inflater.getContext(), root, R.id.videoView1);
//        final VideoView videoView2 = requestVideoView(inflater.getContext(), root, R.id.videoView2);
//        final VideoView videoView3 = requestVideoView(inflater.getContext(), root, R.id.videoView3);
//        final VideoView videoView4 = requestVideoView(inflater.getContext(), root, R.id.videoView4);
//        final VideoView videoView5 = requestVideoView(inflater.getContext(), root, R.id.videoView5);

//        videoFrame.bringChildToFront(videoView3);

        homeViewModel.getVideoUrls().observe(this, new Observer<String[][]>() {
            @Override
            public void onChanged(String[][] urls) {
                videoView1.setVideoURI(Uri.parse(urls[0][0]));
//                videoView2.setVideoURI(Uri.parse(urls[0][1]));
//                videoView3.setVideoURI(Uri.parse(urls[1][0]));

            }
        });

        final GestureDetector detector =  new GestureDetector(inflater.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float x = e1.getX() - e2.getX();
                float x2 = e2.getX() - e1.getX();
                float y = e1.getY() - e2.getY();
                float y2 = e2.getY() - e1.getY();
                // 判断滑动距离是否大于所设置的最小滑动距离
                if (x > FLING_MIN_DISTANCE) {
                    Toast.makeText(inflater.getContext(), "向左手势", Toast.LENGTH_SHORT).show();
//                    videoFrame.bringChildToFront(videoView2);
                } else if (x2 > FLING_MIN_DISTANCE) {
                    Toast.makeText(inflater.getContext(), "向右手势", Toast.LENGTH_SHORT).show();
                } else if (y > FLING_MIN_DISTANCE) {
                    Toast.makeText(inflater.getContext(), "向上手势", Toast.LENGTH_SHORT).show();
//                    videoFrame.bringChildToFront(videoView3);
                } else if (y2 > FLING_MIN_DISTANCE) {
                    Toast.makeText(inflater.getContext(), "向下手势", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

        });

        videoFrame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });
        videoFrame.setClickable(true);
        videoFrame.setLongClickable(true);

        return root;
    }



    private VideoView requestVideoView(Context context,  View root, int id) {
        final VideoView videoView = root.findViewById(id);
        MediaController mc = new MediaController(context);
        mc.setAnchorView(videoView);
        videoView.setMediaController(mc);
        return videoView;
    }


}