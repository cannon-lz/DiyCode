package com.zly.diycode.http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangluya on 2017/3/13.
 */

public interface UserApi {

    @FormUrlEncoded
    @POST
    Call<?> login(@Field("username") String username, @Field("password") String password);
}
