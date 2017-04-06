package com.zly.diycode.http;

import com.zly.diycode.http.entities.RespProject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhangluya on 2017/4/6.
 */

public interface ProjectApi {

    @GET("projects.json")
    Call<List<RespProject>> getProjects(@QueryMap Map<String, Object> params);
}
