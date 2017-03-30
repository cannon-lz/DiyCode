package com.zly.diycode.http;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zhangluya on 2017/3/30.
 */

public class Config {

    public static final String CLIENT_ID = "ff74edc6";
    public static final String CLIENT_SECRET = "25cd5c4b14e71b54fb784ba3c941c18db27266f3bfedb54f50ab89a0fe299f6a";

    public static final String GRANT_TYPE_PASSWORD = "password";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GRANT_TYPE_PASSWORD})
    public @interface GrantType {
    }
}
