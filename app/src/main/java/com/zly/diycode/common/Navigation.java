package com.zly.diycode.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zly.diycode.editor.EditorActivity;
import com.zly.diycode.editor.EditRequester;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.TopicsDetailsActivity;
import com.zly.diycode.user.LoginActivity;
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

    public void openAddReply(@NonNull Context context, @NonNull EditRequester editRequester) {
        Intent intent = new Intent(context, EditorActivity.class);
        intent.putExtra("editRequester", editRequester);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, REQ_CODE_ADD_REPLY);
        }
    }

    public void openAddReply(@NonNull Fragment fragment, @NonNull EditRequester editRequester) {
        Intent intent = new Intent(fragment.getActivity(), EditorActivity.class);
        intent.putExtra("editRequester", editRequester);
        fragment.startActivityForResult(intent, REQ_CODE_ADD_REPLY);
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

    public void openEditor(Context context, EditRequester message) {
        Intent intent = new Intent(context, EditorActivity.class);
        intent.putExtra("requester", message);
        context.startActivity(intent);
    }

    public void openEditor(Fragment context, EditRequester message) {
        Intent intent = new Intent(context.getActivity(), EditorActivity.class);
        intent.putExtra("requester", message);
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

        public
        @Nullable
        String getTitle(@NonNull EditorActivity activity) {
            return activity.getIntent().getStringExtra("title");
        }

        public
        @Nullable
        EditRequester getCreateReplyMessage(@NonNull EditorActivity activity) {
            return activity.getIntent().getParcelableExtra("replyMessage");
        }

        public void setReplyResult(@NonNull EditorActivity context, EntitiesContract.Reply topics) {
            Intent intent = new Intent();
            intent.putExtra("replyResult", topics);
            context.setResult(Activity.RESULT_OK, intent);
        }

        public EntitiesContract.Reply getReplyResult(@NonNull Intent intent) {
            return intent.getParcelableExtra("replyResult");
        }

        public EditRequester getEditorType(EditorActivity intent) {
            return intent.getIntent().getParcelableExtra("requester");
        }
    }
}
