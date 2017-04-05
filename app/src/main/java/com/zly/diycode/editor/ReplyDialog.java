package com.zly.diycode.editor;

import android.app.Dialog;
import android.content.Context;
import android.database.DatabaseUtils;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.zly.diycode.R;
import com.zly.diycode.databinding.ActivityReplyBinding;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/4/5.
 */

public class ReplyDialog extends Dialog {


    public ReplyDialog(@NonNull Context context) {
        super(context, R.style.DialogNoTitle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReplyBinding inflate = ActivityReplyBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(inflate.getRoot());
        EntitiesContract.Reply reply = new EntitiesContract.Reply();
        reply.setUsername("ZTE_CODEr");
        reply.setPhoto("https://diycode.b0.upaiyun.com/user/avatar/2571_1491357330.jpg");
        reply.setContent("技术人赚钱的机会真的非常多，聊聊那些年我实践过和见过的各种赚钱方式");

        inflate.setReply(reply);
    }
}
