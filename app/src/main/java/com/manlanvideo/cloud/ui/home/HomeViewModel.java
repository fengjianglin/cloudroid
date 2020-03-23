package com.manlanvideo.cloud.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manlanvideo.cloud.api.ApiHttp;
import com.manlanvideo.cloud.api.ApiResult;
import com.manlanvideo.cloud.api.entity.Clip;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<List<Clip>> clipList = null;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Clip>> getClipList() {
        if (clipList == null) {
            clipList = new MutableLiveData<List<Clip>>();
            loadClips();
        }
        return clipList;
    }

    private void loadClips() {
        // Do an asyncronous operation to fetch users.
        new Thread(){
            public void run() {
                ApiResult<List<Clip>> list =  ApiHttp.getForList("/clip/list", Clip.class);

                clipList.postValue(list.getData());
            }
        }.start();
    }

}