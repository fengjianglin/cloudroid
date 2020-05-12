package com.manlanvideo.cloud.api.services;

import com.manlanvideo.cloud.api.ApiResult;
import com.manlanvideo.cloud.api.UrlConstants;
import com.manlanvideo.cloud.api.entity.Clip;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NerService {

    @Headers("Cache-Control: public, max-age=5")
    @GET("/nerapi")
    Observable<String> get();

    @Headers("Cache-Control: public, max-age=5")
    @GET("/nerapi/image/priview/ec354d5fed3c48229722a4aeebaba35d.jpg")
    Observable<ResponseBody> getImage();

    @Headers("Cache-Control: public, max-age=5")
    @GET("/nerapi/app/about/aboutNer")
    Observable<String> aboutNer();

    @Headers("Cache-Control: public, max-age=5")
    @GET("/nerapi/app/news/index.html?id=c0310c0851364a7d84f1d3d2f47e1432")
    Observable<String> newsDetail();

    @Headers("Cookie: token=1a4e901beaf6444dae1444f1731eba72")
    @POST("/nerapi/app/message/list")
    Observable<String> newsList(Object o);

}
