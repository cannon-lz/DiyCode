package com.zly.diycode.http;

import com.zly.diycode.http.entities.RespLogin;
import com.zly.diycode.http.entities.RespMe;
import com.zly.diycode.http.entities.RespPaper;
import com.zly.diycode.http.entities.RespResult;
import com.zly.diycode.http.entities.UserBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by zhangluya on 2017/3/13.
 */

public interface UserApi {

    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    Call<RespLogin> login(@FieldMap Map<String, Object> params);

    @GET("users/{login}/favorites.json")
    Call<List<RespPaper>> favorites(@Path("login") String login, @QueryMap Map<String, Object> params);

    @POST("users/{login}/follow.json")
    Call<RespResult> follow(@Path("login") String login);

    @GET("users/{login}/followers.json")
    Call<List<UserBean>> followers(@Path("login") String login, @QueryMap Map<String, Object> params);

    @GET("users/{login}/following.json")
    Call<List<UserBean>> following(@Path("login") String login, @QueryMap Map<String, Object> params);

    @GET("users/{login}/replies.json")
    Call<List<RespPaper>> replies(@Path("login") String login, @QueryMap Map<String, Object> params);

    @GET("users/{login}/topics.json")
    Call<List<RespPaper>> topics(@Path("login") String login, @QueryMap Map<String, Object> params);

    @GET("users/me.json")
    Call<RespMe> getMe();
}
