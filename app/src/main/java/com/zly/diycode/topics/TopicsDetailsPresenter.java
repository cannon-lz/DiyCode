package com.zly.diycode.topics;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.TopicsData;
import com.zly.diycode.data.TopicsRemoteData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        getDetails();
        getReplies();
    }

    private void getDetails() {
        mData.getById(mTopicsId, new Callback<EntitiesContract.Topics>() {
            @Override
            public void onSuccess(EntitiesContract.Topics topics) {
                topics.setItemViewType(R.layout.item_topics_detail);
                mDetailsAndReplies.add(0, topics);
                mView.showDetails(mDetailsAndReplies);
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
                mView.showDetails(mDetailsAndReplies);
            }

            @Override
            public void onError(String messgae) {

            }
        });
    }
}
