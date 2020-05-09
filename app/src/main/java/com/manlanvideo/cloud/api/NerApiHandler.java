package com.manlanvideo.cloud.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NerApiHandler {

    private static String BASE_URL = "https://nerapp.xxicp.cn";

    private static final int CONNECT_TIMEOUT = 5;
    private static final int WRITE_TIMEOUT = 5;
    private static final int READ_TIMEOUT = 5;
    private final static int HTTP_RETRY_NUM = 3; //请求失败重连次数
    private Retrofit mRetrofit;

    private NerApiHandler(Context context) {
        OkHttpClient.Builder okHBuilder = new OkHttpClient.Builder();
        okHBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(SSL.getSSLSocketFactory(context), SSL.getX509TrustManager())
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        mRetrofit = new Retrofit.Builder()
                .client(okHBuilder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static NerApiHandler INSTANCE = null;

    public static NerApiHandler getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new NerApiHandler(context);
        }
        return INSTANCE;
    }

    public <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }

    public <X> void toSubscribe(Observable<X> o, Observer<X> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(HTTP_RETRY_NUM)
                .subscribe(s);
    }
}
