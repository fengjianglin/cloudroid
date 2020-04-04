package com.manlanvideo.cloud.api;

public interface ApiCallBack<D> {
    void success(String status, String msg, D data);
    void failure(String status, String msg);
}
