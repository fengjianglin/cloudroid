package com.manlanvideo.cloud.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private String[][] urls = {
            {"https://uzshare.com/wujiandao1.mp4","https://uzshare.com/wujiandao2.mp4","https://uzshare.com/wujiandao3.mp4"},
            {"https://uzshare.com/wujiandao4.mp4","https://uzshare.com/wujiandao5.mp4","https://uzshare.com/wujiandao6.mp4"},
            {"https://uzshare.com/wujiandao7.mp4","https://uzshare.com/wujiandao8.mp4","https://uzshare.com/wujiandao9.mp4"}};

    private MutableLiveData<String> mText;

    private MutableLiveData<String[][]> mVideoUrls;

    public HomeViewModel() {

        mVideoUrls = new MutableLiveData<>();
        mVideoUrls.setValue(urls);

        mText = new MutableLiveData<>();
        mText.setValue(urls[0][0]);
    }

    public LiveData<String> getText() {
        return mText;
    }


    public LiveData<String[][]> getVideoUrls() {
        return mVideoUrls;
    }
}