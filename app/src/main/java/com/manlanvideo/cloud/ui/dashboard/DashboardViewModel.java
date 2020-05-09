package com.manlanvideo.cloud.ui.dashboard;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manlanvideo.cloud.api.ApiCallBack;
import com.manlanvideo.cloud.api.ApiHandler;
import com.manlanvideo.cloud.api.ApiResult;
import com.manlanvideo.cloud.api.NerApiHandler;
import com.manlanvideo.cloud.api.entity.Clip;
import com.manlanvideo.cloud.api.services.ClipService;
import com.manlanvideo.cloud.api.services.NerService;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText = null;

    public DashboardViewModel() {
    }

    public LiveData<String> getText(Context context) {
        if (mText == null) {
            mText = new MutableLiveData<String>();
            try {
                loadString(context);
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return mText;
    }

    private void loadString(Context context) throws KeyStoreException, NoSuchAlgorithmException {

        NerService nerService = NerApiHandler.getInstance(context).createService(NerService.class);
        Observable<String> observable = nerService.get();
        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String x) {
                mText.postValue(x);
            }

            @Override
            public void onError(Throwable e) {
                if(e instanceof HttpException){
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    try {
                        mText.postValue(body.string());
                        return;
                    } catch (IOException IOe) {
                        IOe.printStackTrace();
                    }
                }
                mText.postValue(e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        };

        NerApiHandler.getInstance(context).toSubscribe(observable, observer);

    }
}