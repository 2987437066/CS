package com.example.day01;

import com.example.day01.bean.RecBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiserVice {
//    http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    String url = "http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<RecBean> getData();
}
