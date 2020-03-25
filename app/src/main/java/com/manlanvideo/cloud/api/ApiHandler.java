package com.manlanvideo.cloud.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {
    private static String BASE_URL = "http://192.168.0.104:7002/api/";
    private static final int CONNECT_TIMEOUT = 5;
    private static final int WRITE_TIMEOUT = 5;
    private static final int READ_TIMEOUT = 5;
    private final static int HTTP_RETRY_NUM = 3; //请求失败重连次数
    private Map<String, Disposable> disposableMap = new HashMap<>();
    private Retrofit mRetrofit;
    private ApiHandler(){
        OkHttpClient.Builder okHBuilder = new OkHttpClient.Builder();
        okHBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        mRetrofit = new Retrofit.Builder()
                .client(okHBuilder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final ApiHandler INSTANCE = new ApiHandler();

    public static ApiHandler getInstance() {
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

    public <X> void toSubscribe(Observable<ApiResult<X>> o, final ApiCallBack<X> l) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(HTTP_RETRY_NUM)
                .subscribe(new Observer<ApiResult<X>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposableMap.put(getClass().getName(), d);
                    }

                    @Override
                    public void onNext(ApiResult<X> x) {
                        try{
                            if (ApiResult.OK.equalsIgnoreCase(x.getStatus())) {
                                if (l != null) l.success(x.getStatus(), x.getMsg(), x.getData());
                            } else {
                                if (l != null) l.failure(x.getStatus(), x.getMsg());
                            }
                        }catch (Exception e){
                            if (l != null) l.failure(ApiResult.ERROR, e.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (l != null) l.failure(ApiResult.ERROR, e.getMessage());
                        disposableMap.remove(getClass().getName());
                    }

                    @Override
                    public void onComplete() {
                        disposableMap.remove(getClass().getName());
                    }
                });
    }


}
