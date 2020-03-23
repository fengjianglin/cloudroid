package com.manlanvideo.cloud.ui.home.adapter.items;

import android.app.Activity;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;

import java.io.IOException;

public class ItemFactory {

    public static BaseVideoItem createItemFromAsset(String assetName, int imageResource, Activity activity, VideoPlayerManager<MetaData> videoPlayerManager) throws IOException {
        return new AssetVideoItem(assetName, activity.getAssets().openFd(assetName), videoPlayerManager, Picasso.get(), imageResource);
    }


    public static BaseVideoItem createItemFromDirectLink(String title, String url, VideoPlayerManager<MetaData> videoPlayerManager,int imageResource) throws IOException {
        Log.e("mp4.url", url);
        return new DirectLinkVideoItem(title, url, videoPlayerManager, Picasso.get(), imageResource);
    }
}
