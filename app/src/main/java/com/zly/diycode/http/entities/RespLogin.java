package com.zly.diycode.http.entities;

import com.google.gson.annotations.SerializedName;
import com.zly.diycode.common.DateUtils;
import com.zly.diycode.data.Mapper;
import com.zly.diycode.user.User;

/**
 * Created by zhangluya on 2017/3/30.
 */

public class RespLogin implements Mapper<User> {


    /**
     * access_token : 5fe910ec53684a4fa4fe8d1a2763113edc6e31122ba7ee1d4e34a511210a1f68
     * token_type : bearer
     * expires_in : 5184000
     * refresh_token : 9429fb6eef941e375f1b4c2abf9304c76aca771344f865618814f537c34a728e
     * created_at : 1490863328
     */

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("created_at")
    private int createdAt;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public User map() {
        User user = new User();
        user.setRefreshToken(refreshToken);
        user.setAccessToken(accessToken);
        user.setCreatedAt(DateUtils.format(createdAt));
        user.setExpiresIn(DateUtils.formatIn(expiresIn));
        user.setTokenType(tokenType);
        return user;
    }
}
