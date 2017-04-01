package com.zly.diycode.editor;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.zly.diycode.databinding.ActivityEditorBinding;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class ReplyViewBindingHelper extends AbsEditorDataBindingHelper<EditRequester> {

    private String mReplyTargetUser;
    private EditorContract.Presenter mPresenter;

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

    public ReplyViewBindingHelper(EditRequester editRequester, ActivityEditorBinding dataBinding) {
        super(editRequester, dataBinding);
        mPresenter = new ReplyPresenter(((EditorContract.View) dataBinding.getRoot().getContext()));
        mPresenter.init();
    }

    @Override
    protected void handleView() {
        ((AppCompatActivity) mDataBinding.evTile.getContext()).setTitle("评论");
        String loginName = data.getLoginName();
        if (!TextUtils.isEmpty(loginName)) {
            mReplyTargetUser = "#" + data.getFloor() + "楼 " + "@" + loginName + " ";
            data.setContent(mReplyTargetUser);
        }
        mDataBinding.setReply(data);
        mDataBinding.etReply.addTextChangedListener(mReplyWatcher);
    }

    @Override
    protected void setPresenter(EditorContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void send() {
        String replyContent = formatReplyContent();
        mPresenter.send(data.getPaperId(), null, replyContent);
    }

    @Override
    protected void handleInitData(Object object) {

    }

    // 回复的报文是 markdown 格式
    private String formatReplyContent() {
        Editable text = mDataBinding.etReply.getText();
        if (!TextUtils.isEmpty(mReplyTargetUser)) {
            String content = text.toString();
            String[] split = content.split(" ");
            String user = split[0] + " " + split[1] + " ";
            return "[" + user + "]" + "(" + user + ")" + split[2];
        }

        return text.toString();
    }
}
