package com.zly.diycode.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zly.diycode.main.TopicsDetailActivity;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class Navigation {

    private static final Navigation INSTANCE = new Navigation();

    private Navigation() {
    }

    public static Navigation getInstance() {
        return INSTANCE;
    }

    public void openDetails(@NonNull Context context, String newsId) {
        Intent intent = new Intent(context, TopicsDetailActivity.class);
        intent.putExtra("newsId", newsId);
        context.startActivity(intent);
    }

    public static class IntentReceiver {

        private static final IntentReceiver INSTANCE = new IntentReceiver();

        private IntentReceiver() {
        }

        public static IntentReceiver getInstance() {
            return INSTANCE;
        }

        public
        @Nullable
        String getNewsId(@NonNull Activity activity) {
            Intent intent = activity.getIntent();
            return intent.getStringExtra("newsId");
        }

    }
}
