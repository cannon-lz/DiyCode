package com.zly.diycode.http;

import com.zly.diycode.http.entities.RespPaper;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhangluya on 2017/3/31.
 */

public interface NewsApi {

    @GET("news.json")
    Call<List<RespPaper>> getNews(@QueryMap Map<String, Object> params);
}
