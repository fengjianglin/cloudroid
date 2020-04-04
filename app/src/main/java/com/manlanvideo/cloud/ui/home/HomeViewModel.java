package com.manlanvideo.cloud.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manlanvideo.cloud.api.ApiCallBack;
import com.manlanvideo.cloud.api.ApiHandler;
import com.manlanvideo.cloud.api.ApiHttp;
import com.manlanvideo.cloud.api.ApiResult;
import com.manlanvideo.cloud.api.entity.Clip;
import com.manlanvideo.cloud.api.services.ClipService;

import java.util.List;

import io.reactivex.Observable;

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
        ClipService clipService = ApiHandler.getInstance().createService(ClipService.class);
        Observable<ApiResult<List<Clip>>> observable = clipService.getForList();
        ApiHandler.getInstance().toSubscribe(observable, new ApiCallBack<List<Clip>>() {
            @Override
            public void success(String status, String msg, List<Clip> data) {
                clipList.postValue(data);
            }

            @Override
            public void failure(String status, String msg) {
                Log.e("LLF","Status : " + status + " msg : " + msg);
            }
        });
        // Do an asyncronous operation to fetch users.
//        new Thread(){
//            public void run() {
//                ApiResult<List<Clip>> list =  ApiHttp.getForList("/clip/list", Clip.class);
//
//                clipList.postValue(list.getData());
//            }
//        }.start();
    }

}