package com.zly.diycode.reply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.topics.TopicsRemoteData;
import com.zly.diycode.databinding.ActivityAddReplyBinding;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class AddReplyActivity extends BaseActivity<ActivityAddReplyBinding, VoidPresenter> {

    private String mReplyTargetUser;

    private TextWatcher mReplyWatcher = new TextWatcher() {
        private int mBeforeTextLength;
        private int mAfterTextLength;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            mBeforeTextLength = s.length();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mAfterTextLength = s.length();
            if (TextUtils.isEmpty(mReplyTargetUser)) {
                return;
            }
            if (mBeforeTextLength > mAfterTextLength) {
                if (mAfterTextLength <= mReplyTargetUser.length()) {
                    mDataBinding.etReply.getText().clear();
                    mReplyTargetUser = null;
                }
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_reply;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("评论");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ReplyMessage reply = Navigation.IntentReceiver.getInstance().getCreateReplyMessage(this);
        mReplyTargetUser = "#" + reply.getFloor() + "楼 " + "@" + reply.getReply().getLoginName() + " ";
        reply.setContent(mReplyTargetUser);
        mDataBinding.setReply(reply);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        EditText etReply = mDataBinding.etReply;
        etReply.addTextChangedListener(mReplyWatcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reply, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_send) {
            showLoading();
            sendReply();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        mDataBinding.etReply.removeTextChangedListener(mReplyWatcher);

        super.onDestroy();
    }

    private void sendReply() {
        String replyContent = mDataBinding.etReply.getText().toString();
        if (!TextUtils.isEmpty(mReplyTargetUser)) {
            replyContent = formatReplyContent();
        }
        ReplyMessage reply = Navigation.IntentReceiver.getInstance().getCreateReplyMessage(this);
        TopicsRemoteData.getInstance().addReplies(reply.getTopics().getId(), replyContent, new Callback<EntitiesContract.Reply>() {
            @Override
            public void onSuccess(EntitiesContract.Reply topics) {
                dismissLoading();
                Navigation.IntentReceiver.getInstance().setReplyResult(AddReplyActivity.this, topics);
                finish();
            }

            @Override
            public void onError(String messgae) {
                dismissLoading();
            }
        });
    }

    // 回复的报文是 markdown 格式
    private String formatReplyContent() {
        Editable text = mDataBinding.etReply.getText();
        String content = text.toString();
        String[] split = content.split(" ");
        String user = split[0] + " " + split[1] + " ";
        return "[" + user + "]" + "(" + user + ")" + split[2];
    }
}
