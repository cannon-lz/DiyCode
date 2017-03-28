package com.zly.diycode.topics.details;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.TopicsData;
import com.zly.diycode.data.TopicsRemoteData;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.list.TopicsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangly on 2017/3/22.
 */

public class TopicsDetailsPresenter implements TopicsContract.DetailsPresenter {

    private TopicsData mData;
    private TopicsContract.DetailsView mView;
    private String mTopicsId;
    private List<Item> mDetailsAndReplies = new ArrayList<>();

    public TopicsDetailsPresenter(TopicsContract.DetailsView view, String topicsId) {
        this.mView = view;
        mTopicsId = topicsId;
        mData = TopicsRemoteData.getInstance();
    }

    @Override
    public void getDetailsAndReplies() {
        mDetailsAndReplies.clear();
        getDetails();
        getReplies();
    }

    private void getDetails() {
        mData.getById(mTopicsId, new Callback<EntitiesContract.Topics>() {
            @Override
            public void onSuccess(EntitiesContract.Topics topics) {
                topics.setItemViewType(R.layout.item_topics_detail);
                mDetailsAndReplies.add(0, topics);
                mView.showDetails(mDetailsAndReplies, true);
            }

            @Override
            public void onError(String messgae) {

            }
        });
    }

    private void getReplies() {
        Map<String, Object> params = new ArrayMap<>();
        mData.getReplies(mTopicsId, params, new Callback<List<EntitiesContract.Reply>>() {

            @Override
            public void onSuccess(List<EntitiesContract.Reply> replies) {
                mDetailsAndReplies.addAll(replies);
                mView.showDetails(mDetailsAndReplies, false);
            }

            @Override
            public void onError(String messgae) {

            }
        });
    }
}
