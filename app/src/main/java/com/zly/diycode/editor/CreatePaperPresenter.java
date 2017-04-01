package com.zly.diycode.editor;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.data.Callback;
import com.zly.diycode.data.topics.TopicsRemoteData;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangluya on 2017/4/1.
 */

public class CreatePaperPresenter implements EditorContract.Presenter {

    private EditorContract.View<List<Node>, EntitiesContract.Topics> mView;
    private String nodeId;

    public CreatePaperPresenter(EditorContract.View<List<Node>, EntitiesContract.Topics> view) {
        this.mView = view;
    }

    public void init() {
        TopicsRemoteData.getInstance().getNodes(new Callback<List<Node>>() {
            @Override
            public void onSuccess(List<Node> nodes) {
                mView.setInitData(nodes);
            }

            @Override
            public void onError(String messgae) {

            }
        });
    }

    @Override
    public void send(String id, String title, String content) {
        TopicsRemoteData.getInstance().create(nodeId, title, content, new Callback<EntitiesContract.Topics>() {
            @Override
            public void onSuccess(EntitiesContract.Topics topics) {
                mView.toast("发表成功");
            }

            @Override
            public void onError(String messgae) {

            }
        });

    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
