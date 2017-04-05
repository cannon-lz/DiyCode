package com.zly.diycode.editor;

import android.app.Dialog;
import android.content.Context;
import android.database.DatabaseUtils;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.zly.diycode.R;
import com.zly.diycode.databinding.ActivityReplyBinding;
import com.zly.diycode.databinding.DialogTestBinding;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/4/5.
 */

public class ReplyDialog extends Dialog {

    private EntitiesContract.Topics mReplay;

    public ReplyDialog(@NonNull Context context, EntitiesContract.Topics reply) {
        super(context, R.style.DialogNoTitle);
        mReplay = reply;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReplyBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.activity_reply, null, false);
        setContentView(inflate.getRoot());
        inflate.setReply(mReplay);

        final Window window = getWindow();
        final WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = 500;
        Log.i("ReplyDialog", String.format("window width %s", attributes.width));
    }

    public void reply() {

    }
}
