package com.zly.diycode.http.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class UserBean {

    /**
     * id : 48
     * login : jonsnow
     * name : 囧恩
     * avatar_url : https://diycode.b0.upaiyun.com/user/large_avatar/48.jpg
     */

    private int id;
    private String login;
    private String name;
    @SerializedName("avatar_url")
    private String userPhoto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
