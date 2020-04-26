package com.manlanvideo.cloud.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.manlanvideo.cloud.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;


    private ViewFlipper flipper;
    private GestureDetector mDetector; //手势检测

    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context =  inflater.getContext();
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        flipper = root.findViewById(R.id.flipper);
        flipper.addView(getImageView(context, R.drawable.a1));
        flipper.addView(getImageView(context, R.drawable.a2));
        flipper.addView(getImageView(context, R.drawable.a3));
        flipper.addView(getImageView(context, R.drawable.a4));
        flipper.addView(getImageView(context, R.drawable.a5));
        flipper.addView(getImageView(context, R.drawable.a6));
        flipper.addView(getImageView(context, R.drawable.a7));
        flipper.addView(getImageView(context, R.drawable.a8));
        flipper.addView(getImageView(context, R.drawable.a9));
        
        mDetector = new GestureDetector(inflater.getContext(), touchListener);
        flipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        flipper.setClickable(true);
        flipper.setLongClickable(true);

        return root;
    }

    private ImageView getImageView(Context context , int id){
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(id);
        return imageView;
    }


    private GestureDetector.SimpleOnGestureListener touchListener  = new GestureDetector.SimpleOnGestureListener() {

        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

        @Override
        public boolean onDown(MotionEvent e) {
            return true; // 这里需要返回true才能监听手势
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // Fling left  从右向左划
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                flipper.setOutAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_left_out));
                flipper.setInAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_left_in));
                flipper.showNext();//显示下一个视图
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // Fling right 从左向右划
                flipper.setOutAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_right_out));
                flipper.setInAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_right_in));
                flipper.showPrevious();//显示上一个视图
            }else if (e1.getY()-e2.getY()>FLING_MIN_DISTANCE&& Math.abs(velocityY) > FLING_MIN_VELOCITY){
                //从下向上划
                flipper.setOutAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_up_out));
                flipper.setInAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_up_in));
                flipper.showNext();//显示下一个视图
            }else if (e2.getY()-e1.getY()>FLING_MIN_DISTANCE&& Math.abs(velocityY) > FLING_MIN_VELOCITY){
                //从上向下划
                flipper.setOutAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_down_out));
                flipper.setInAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.push_down_in));
                flipper.showPrevious();//显示上一个视图
            }
            flipper.setEnabled(true);
            return true;
        }
    };

}