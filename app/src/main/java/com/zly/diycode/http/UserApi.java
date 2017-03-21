package com.zly.diycode.http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by zhangluya on 2017/3/13.
 */

public interface UserApi {

    @FormUrlEncoded
    @POST
    Call<?> login(@Field("username") String username, @Field("password") String password);

    @GET("topics/{id}")
    Call<Art> getNews(@Path("id") String id);


}
