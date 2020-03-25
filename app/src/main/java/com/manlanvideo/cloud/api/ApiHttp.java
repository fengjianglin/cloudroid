package com.manlanvideo.cloud.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ApiHttp {

//    private static final String host = "http://192.168.0.104:7002/api";
//
//    private static final ApiHttp apiHttp = new ApiHttp();
//
//    private DefaultHttpClient httpClient;
//
//    private ApiHttp() {
//        httpClient = new DefaultHttpClient();
//    }
//
//    public static <T> ApiResult<T> getForItem(String url, Class<T> clazz) {
//        url = host + url;
//        try {
//            HttpGet httpGet=  new HttpGet(url);
//            HttpResponse httpResponse = apiHttp.httpClient.execute(httpGet);
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.SC_OK) {
//                String str = EntityUtils.toString(httpResponse.getEntity());
//                ApiResult<T> res = fromJsonObject(str, clazz);
//                return res;
//            } else {
//                throw new RuntimeException("Http Status Code Error: " + statusCode);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public static  <T> ApiResult<List<T>> getForList(String url, Class<T> clazz) {
//        url = host + url;
//        try {
//            HttpGet httpGet=  new HttpGet(url);
//            HttpResponse httpResponse = apiHttp.httpClient.execute(httpGet);
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.SC_OK) {
//                String str = EntityUtils.toString(httpResponse.getEntity());
//                ApiResult<List<T>> res = fromJsonArray(str, clazz);
//                return res;
//            } else {
//                throw new RuntimeException("Http Status Code Error: " + statusCode);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public static <T> ApiResult<T> postForItem(String url, Class<T> clazz) {
//        url = host + url;
//        try {
//            HttpPost httpPost=  new HttpPost(url);
//            HttpResponse httpResponse = apiHttp.httpClient.execute(httpPost);
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.SC_OK) {
//                String str = EntityUtils.toString(httpResponse.getEntity());
//                ApiResult<T> res = fromJsonObject(str, clazz);
//                return res;
//            } else {
//                throw new RuntimeException("Http Status Code Error: " + statusCode);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//
//    private static <T> ApiResult<T> fromJsonObject(String string, Class<T> clazz) {
//        Type type = new ParameterizedTypeImpl(ApiResult.class, new Class[]{clazz});
//        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(string, type);
//    }
//
//    public static <T> ApiResult<List<T>> fromJsonArray(String string, Class<T> clazz) {
//        // 生成List<T> 中的 List<T>
//        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
//        // 根据List<T>生成完整的Result<List<T>>
//        Type type = new ParameterizedTypeImpl(ApiResult.class, new Type[]{listType});
//        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(string, type);
//    }
}

class ParameterizedTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;
    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }
    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }
    @Override
    public Type getRawType() {
        return raw;
    }
    @Override
    public Type getOwnerType() {return null;}
}
