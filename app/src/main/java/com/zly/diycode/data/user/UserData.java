package com.zly.diycode.data.user;

import com.zly.diycode.data.Callback;
import com.zly.diycode.home.MeModel;
import com.zly.diycode.http.Config;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.user.User;

import java.util.List;

/**
 * Created by zhangluya on 2017/3/13.
 */

public interface UserData {

    void login(@Config.GrantType String grantType, String username, String password, Callback<User> callback);

    void favorites(String login, String offset, Callback<List<EntitiesContract.Topics>> callback);

    void getMeInfo(Callback<MeModel> callback);
}
