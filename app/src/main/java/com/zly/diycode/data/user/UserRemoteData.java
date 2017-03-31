package com.zly.diycode.data.user;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.common.UserManager;
import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;
import com.zly.diycode.home.MeModel;
import com.zly.diycode.http.ApiConfig;
import com.zly.diycode.http.Config;
import com.zly.diycode.http.RetrofitCallback;
import com.zly.diycode.http.UserApi;
import com.zly.diycode.http.entities.RespLogin;
import com.zly.diycode.http.entities.RespMe;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.user.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhangluya on 2017/3/30.
 */

public class UserRemoteData implements UserData {

    private static UserRemoteData sInstance = new UserRemoteData();

    public static UserData getInstance() {
        return sInstance;
    }

    @Override
    public void login(@Config.GrantType String grantType, String username, String password, Callback<User> callback) {
        UserApi userApi = ApiConfig.getInstance().getApi(UserApi.class);
        Map<String, Object> params = new ArrayMap<>();
        params.put("client_id", Config.CLIENT_ID);
        params.put("client_secret", Config.CLIENT_SECRET);
        params.put("grant_type", grantType);
        params.put("username", username);
        params.put("password", password);
        Call<RespLogin> login = userApi.login(params);
        login.enqueue(new RetrofitCallback<RespLogin, User>(callback) {
            @Override
            public void onResponse(Call<RespLogin> call, Response<RespLogin> response) {
                if (response.isSuccessful()) {
                    RespLogin body = response.body();
                    UserManager.getInstance().setToken(body.getAccessToken());
                    UserManager.getInstance().setRefreshToken(body.getRefreshToken());
                }
                super.onResponse(call, response);
            }
        });

    }

    @Override
    public void favorites(String login, String offset, Callback<List<EntitiesContract.Topics>> callback) {

    }

    @Override
    public void getMeInfo(Callback<MeModel> callback) {
        final UserApi api = ApiConfig.getInstance().getApi(UserApi.class);
        final Call<RespMe> me = api.getMe();
        me.enqueue(new RetrofitCallback<RespMe, MeModel>(callback));
    }
}
