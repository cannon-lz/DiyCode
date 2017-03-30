package com.zly.diycode;

import android.app.Application;

import com.zly.diycode.common.UserManager;

/**
 * Created by zhangluya on 2017/3/30.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UserManager.initInstance(this);
    }
}
