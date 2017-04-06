package com.zly.diycode.editor;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.zly.diycode.data.Callback;
import com.zly.diycode.data.topics.TopicsData;
import com.zly.diycode.data.topics.TopicsRemoteData;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.TopicsDetailsContract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangluya on 2017/4/6.
 */

public class NewTopicsPresenter implements NewTopicsContract.Presenter {

    private NewTopicsContract.View mView;
    private TopicsData mData;
    private Map<Node, List<Node>> mNodes = new ArrayMap<>();

    public NewTopicsPresenter(NewTopicsContract.View view) {
        this.mView = view;
        mData = TopicsRemoteData.getInstance();
    }

    @Override
    public void getNodes() {
        mView.showLoading();
        mData.getNodes(new Callback<List<Node>>() {
            @Override
            public void onSuccess(List<Node> nodes) {
                mView.dismissLoading();
                mNodes = group(nodes);
                mView.showNodes(mNodes);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
            }
        });
    }

    @Override
    public void publishTopics(String nodeId, EntitiesContract.Topics topics) {
        if (TextUtils.isEmpty(topics.getTitle()) || TextUtils.isEmpty(topics.getContent())) {
            mView.toast("不能发布空的文章哦！");
            return;
        }
        mView.showLoading();
        mData.create(nodeId, topics.getTitle(), topics.getContent(), new Callback<EntitiesContract.Topics>() {
            @Override
            public void onSuccess(EntitiesContract.Topics topics) {
                mView.dismissLoading();
                mView.publishComplete(topics);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
            }
        });
    }

    @Override
    public Map<Node, List<Node>> getNodesResult() {
        return mNodes;
    }

    private Map<Node, List<Node>> group(List<Node> nodes) {
        ArrayMap<Integer, List<Node>> sort = new ArrayMap<>();
        Map<Node, List<Node>> results = new ArrayMap<>();
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            Node order = nodes.get(i);
            if (sort.containsKey(order.getSectionId())) {
                List<Node> values = sort.get(order.getSectionId());
                values.add(order);
                sort.put(order.getSectionId(), values);
                results.put(order, values);
            } else {
                ArrayList<Node> initValues = new ArrayList<>();
                initValues.add(order);
                sort.put(order.getSectionId(), initValues);
                results.put(order, initValues);
            }
        }

        for (Node parent : results.keySet()) {
            parent.setParent(true);
        }
        return results;
    }
}
