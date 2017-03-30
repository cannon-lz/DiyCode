package com.zly.diycode.http;

import com.zly.diycode.http.entities.RespReply;
import com.zly.diycode.http.entities.RespResult;
import com.zly.diycode.http.entities.RespPaper;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by zhangluya on 2017/3/22.
 */

public interface TopicsApi {

    @GET("topics.json")
    Call<List<RespPaper>> getTopics(@QueryMap Map<String, Object> params);

    @POST("topics.json")
    @FormUrlEncoded
    Call<RespPaper> create(@FieldMap Map<String, Object> params);

    @POST("topics/{id}.json")
    @FormUrlEncoded
    Call<RespPaper> update(@Path("id") String id, @FieldMap Map<String, Object> params);

    @DELETE("topics/{id}.json")
    Call<RespResult> delete(@Path("id") String id);

    @GET("topics/{id}.json")
    Call<RespPaper> getById(@Path("id") String id);

    @POST("topics/{id}/ban.json")
    Call<RespResult> ban(@Path("id") String id);

    @POST("topics/{id}/favorite.json")
    Call<RespResult> favorite(@Path("id") String id);

    @POST("topics/{id}/unfavorite.json")
    Call<RespResult> unFavorite(@Path("id") String id);

    @POST("topics/{id}/follow.json")
    Call<RespResult> follow(@Path("id") String id);

    @POST("topics/{id}/unfollow.json")
    Call<RespResult> unFollow(@Path("id") String id);

    @GET("topics/{id}/replies.json")
    Call<List<RespReply>> getReplies(@Path("id") String id, @QueryMap Map<String, Object> params);

    @POST("topics/{id}/replies.json")
    @FormUrlEncoded
    Call<RespReply> addReplies(@Path("id") String id, @FieldMap Map<String, Object> params);
}
