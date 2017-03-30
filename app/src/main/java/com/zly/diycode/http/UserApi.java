package com.zly.diycode.http;

import com.zly.diycode.http.entities.RespLogin;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangluya on 2017/3/13.
 */

public interface UserApi {

    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    Call<RespLogin> login(@FieldMap Map<String, Object> params);

}
