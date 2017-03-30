package com.zly.diycode.data.user;

import com.zly.diycode.data.Callback;
import com.zly.diycode.http.Config;
import com.zly.diycode.user.User;

/**
 * Created by zhangluya on 2017/3/13.
 */

public interface UserData {

    void login(@Config.GrantType String grantType, String username, String password, Callback<User> callback);
}
