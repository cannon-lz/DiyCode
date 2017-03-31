package com.zly.diycode.editor;

import com.zly.diycode.data.Callback;
import com.zly.diycode.data.topics.TopicsRemoteData;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class ReplyPresenter implements EditorContract.Presenter {

    private EditorContract.View<EntitiesContract.Reply> mView;

    public ReplyPresenter(EditorContract.View<EntitiesContract.Reply> view) {
        this.mView = view;
    }

    @Override
    public void send(String id, String title, String content) {
        mView.showLoading();
        TopicsRemoteData.getInstance().addReplies(id, content, new Callback<EntitiesContract.Reply>() {
            @Override
            public void onSuccess(EntitiesContract.Reply reply) {
                mView.dismissLoading();
                mView.success(reply);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
                mView.toast(messgae);
            }
        });
    }
}
