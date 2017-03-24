package com.zly.diycode.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zly.diycode.reply.AddReplyActivity;
import com.zly.diycode.reply.ReplyMessage;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.details.TopicsDetailsActivity;
import com.zly.diycode.web.WebActivity;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class Navigation {

    public static final int REQ_CODE_ADD_REPLY = 1;

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

    public void openAddReply(@NonNull Context context, @NonNull ReplyMessage replyMessage) {
        Intent intent = new Intent(context, AddReplyActivity.class);
        intent.putExtra("replyMessage", replyMessage);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, REQ_CODE_ADD_REPLY);
        }
    }

    public void openAddReply(@NonNull Fragment fragment, @NonNull ReplyMessage replyMessage) {
        Intent intent = new Intent(fragment.getActivity(), AddReplyActivity.class);
        intent.putExtra("replyMessage", replyMessage);
        fragment.startActivityForResult(intent, REQ_CODE_ADD_REPLY);
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

        public
        @Nullable
        String getTitle(@NonNull AddReplyActivity activity) {
            return activity.getIntent().getStringExtra("title");
        }

        public
        @Nullable
        ReplyMessage getCreateReplyMessage(@NonNull AddReplyActivity activity) {
            return activity.getIntent().getParcelableExtra("replyMessage");
        }

        public void setReplyResult(@NonNull AddReplyActivity context, EntitiesContract.Reply topics) {
            Intent intent = new Intent();
            intent.putExtra("replyResult", topics);
            context.setResult(Activity.RESULT_OK, intent);
        }

        public EntitiesContract.Reply getReplyResult(@NonNull Intent intent) {
            return intent.getParcelableExtra("replyResult");
        }
    }
}
