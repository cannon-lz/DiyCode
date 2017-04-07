package com.zly.diycode.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zly.diycode.data.project.Project;
import com.zly.diycode.project.ProjectDetailsActivity;
import com.zly.diycode.reply.NewTopicsActivity;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.TopicsDetailsActivity;
import com.zly.diycode.user.LoginActivity;
import com.zly.diycode.user.MeListActivity;
import com.zly.diycode.web.WebActivity;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class Navigation {

    public static final int REQ_CODE_ADD_REPLY = 1;
    public static final int REQ_CODE_LOGIN = 2;

    private static final Navigation INSTANCE = new Navigation();

    private Navigation() {
    }

    public static Navigation getInstance() {
        return INSTANCE;
    }

    public void openDetails(@NonNull Context context, @NonNull String newsId) {
        Intent intent = new Intent(context, TopicsDetailsActivity.class);
        intent.putExtra("newsId", newsId);
        context.startActivity(intent);
    }

    public void openWebBrowser(@NonNull Context context, @NonNull String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public void openLogin(@NonNull Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, REQ_CODE_LOGIN);
        }
    }

    public void openLogin(@NonNull Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), LoginActivity.class);
        fragment.startActivityForResult(intent, REQ_CODE_LOGIN);
    }

    public void openMeTopics(Context context, String loginName) {
        Intent intent = new Intent(context, MeListActivity.class);
        intent.putExtra("openType", MeListActivity.TOPICS);
        intent.putExtra("loginName", loginName);
        context.startActivity(intent);
    }

    public void openMeFavorites(Context context, String loginName) {
        Intent intent = new Intent(context, MeListActivity.class);
        intent.putExtra("openType", MeListActivity.FAVORITES);
        intent.putExtra("loginName", loginName);
        context.startActivity(intent);
    }

    public void openMeReplies(Context context, String loginName) {
        Intent intent = new Intent(context, MeListActivity.class);
        intent.putExtra("openType", MeListActivity.REPLIES);
        intent.putExtra("loginName", loginName);
        context.startActivity(intent);
    }

    public void openCreateTopic(Context context) {
        Intent intent = new Intent(context, NewTopicsActivity.class);
        context.startActivity(intent);
    }

    public void openProjectDetails(Context context, Project project) {
        Intent intent = new Intent(context, ProjectDetailsActivity.class);
        intent.putExtra("project", project);
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
        String getNewsId(@NonNull TopicsDetailsActivity activity) {
            Intent intent = activity.getIntent();
            return intent.getStringExtra("newsId");
        }


        public
        @Nullable
        String getUrl(@NonNull WebActivity activity) {
            return activity.getIntent().getStringExtra("url");
        }


        public EntitiesContract.Reply getReplyResult(@NonNull Intent intent) {
            return intent.getParcelableExtra("replyResult");
        }

    }
}
