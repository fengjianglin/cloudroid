package com.manlanvideo.cloud.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manlanvideo.cloud.api.NerApiHandler;
import com.manlanvideo.cloud.api.services.NerService;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText = null;

    private MutableLiveData<Bitmap> mBitmap = null;

    public DashboardViewModel() {
    }

    public LiveData<String> getText(Context context) {
        if (mText == null) {
            mText = new MutableLiveData<String>();
            loadString(context);
        }
        return mText;
    }

    public LiveData<Bitmap> getImage(Context context) {
        if (mBitmap == null) {
            mBitmap = new MutableLiveData<Bitmap>();
            loadImage(context);
        }
        return mBitmap;
    }

    private void loadString(Context context) {

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

    private void loadImage(Context context) {

        NerService nerService = NerApiHandler.getInstance(context).createService(NerService.class);
        Observable<ResponseBody> observable = nerService.getImage();
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
                mBitmap.postValue(bitmap);
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