package com.zly.diycode.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by zhangluya on 2017/3/30.
 */

public class UserManager {

    private static final String TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refreshToken";

    private volatile static UserManager sInstance;

    private SharedPreferences mPreferences;
    private String mToken;
    private String mRefreshToken;

    private UserManager(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public static void initInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserManager(context);
        }
    }

    public static UserManager getInstance() throws NullPointerException {
        if (sInstance == null) {
            throw new NullPointerException("Don't call the method. #initInstance(Context) ");
        }

        return sInstance;
    }

    public void setToken(String token) {
        setValue(mToken, TOKEN, token);
    }

    public String getToken() {
        return getValue(mToken, TOKEN);
    }

    public void setRefreshToken(String refreshToken) {
        setValue(mRefreshToken, REFRESH_TOKEN, refreshToken);
    }

    public String getRefreshToken() {
        return getValue(mRefreshToken, REFRESH_TOKEN);
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }

    public void logout() {
        setToken("");
        setRefreshToken("");
    }

    private void setValue(String source, String key, String newValue) {
        if (!TextUtils.equals(source, newValue)) {
            mPreferences.edit().putString(key, newValue).apply();
            source = newValue;
        }
    }

    private String getValue(String source, String key) {
        if (TextUtils.isEmpty(source)) {
            source = mPreferences.getString(key, "");
            setValue(source, key, source);
        }
        return source;
    }
}
