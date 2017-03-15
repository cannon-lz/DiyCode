package com.zly.diycode.data;

/**
 * Created by zhangluya on 2017/3/13.
 */

public interface UserData {

    interface LoginCallback {

        void onSuccess();

        void onFailed();
    }

    void login(String username, String password, LoginCallback callback);
}
