package com.manlanvideo.cloud.api.services;

import com.manlanvideo.cloud.api.ApiResult;
import com.manlanvideo.cloud.api.UrlConstants;
import com.manlanvideo.cloud.api.entity.Clip;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NerService {

    @Headers("Cache-Control: public, max-age=5")
    @GET("/nerapi")
    Observable<String> get();

}
