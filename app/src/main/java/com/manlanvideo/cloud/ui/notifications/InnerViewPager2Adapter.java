package com.manlanvideo.cloud.ui.notifications;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.manlanvideo.cloud.R;

import java.util.LinkedList;

public class InnerViewPager2Adapter extends RecyclerView.Adapter<InnerViewPager2Adapter.BaseViewHolder>{

    LinkedList<String> datas;

    public InnerViewPager2Adapter(LinkedList<String> datas) {
        this.datas = datas;
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_viewpager2_item,parent,false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        initializePlayer(holder.playerView, datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public  class  BaseViewHolder extends RecyclerView.ViewHolder{
        PlayerView playerView;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.playerView = itemView.findViewById(R.id.playerview);
        }
    }

    private void initializePlayer(PlayerView playerView, String url) {
        ExoPlayer player = new SimpleExoPlayer.Builder(playerView.getContext()).build();
        playerView.setPlayer(player);
        player.setPlayWhenReady(false);
        Uri uri = Uri.parse(url);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer"))
                .createMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

}