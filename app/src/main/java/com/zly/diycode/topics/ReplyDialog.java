package com.zly.diycode.topics;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.zly.diycode.R;
import com.zly.diycode.databinding.ActivityReplyBinding;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/4/5.
 */

public class ReplyDialog extends Dialog {

    public interface OnReplyClickListener {

        void onClickReply(String body);
    }

    private EntitiesContract.Topics mReplay;
    private ActivityReplyBinding mDataBinding;
    private String mFloor;
    private String mReplyUser;
    private String mReplyTargetUser;
    private OnReplyClickListener mOnReplayClickListener;

    public ReplyDialog(@NonNull Context context, EntitiesContract.Topics reply, String floor, String replyUser) {
        super(context, R.style.DialogNoTitle);
        mReplay = reply;
        mFloor = floor;
        mReplyUser = replyUser;
    }

    public void setOnReplayClickListener(OnReplyClickListener onReplayClickListener) {
        this.mOnReplayClickListener = onReplayClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.activity_reply, null, false);
        setContentView(mDataBinding.getRoot());
        setReplyEditor(mFloor, mReplyUser);
        setWindowWidth();
        mDataBinding.setReply(mReplay);
        mDataBinding.setPresenter(this);
    }

    private void setReplyEditor(String floor, String replyUser) {
        if (!TextUtils.isEmpty(replyUser) && !TextUtils.isEmpty(floor)) {
            mReplyTargetUser = "#" + floor + "楼 " + "@" + replyUser + " ";
            mDataBinding.etReplyToContent.setText(mReplyTargetUser);
            mDataBinding.etReplyToContent.addTextChangedListener(mReplyWatcher);
        }
    }


    private void setWindowWidth() {
        final Window window = getWindow();
        final WindowManager.LayoutParams attributes = window.getAttributes();
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        attributes.width = dm.widthPixels - 130;
    }

    public void reply() {
        if (mOnReplayClickListener != null) {
            mOnReplayClickListener.onClickReply(formatReplyContent());
        }
    }

    // 回复的报文是 markdown 格式
    private String formatReplyContent() {
        Editable text = mDataBinding.etReplyToContent.getText();
        if (!TextUtils.isEmpty(mReplyTargetUser)) {
            String content = text.toString();
            String[] split = content.split(" ");
            String user = split[0] + " " + split[1] + " ";
            return "[" + user + "]" + "(" + user + ")" + split[2];
        }

        return text.toString();
    }

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
                    mDataBinding.etReplyToContent.getText().clear();
                    mReplyTargetUser = null;
                }
            }
        }
    };
}
