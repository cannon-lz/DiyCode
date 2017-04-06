package com.zly.diycode.topics;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.topics.TopicsData;
import com.zly.diycode.data.topics.TopicsRemoteData;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.TopicsDetailsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangly on 2017/3/22.
 */

public class TopicsDetailsPresenter implements TopicsDetailsContract.Presenter {

    private TopicsData mData;
    private TopicsDetailsContract.View mView;
    private String mTopicsId;
    private int mOffset;
    private List<Item> mDetailsAndReplies = new ArrayList<>();

    public TopicsDetailsPresenter(TopicsDetailsContract.View view, String topicsId) {
        this.mView = view;
        mTopicsId = topicsId;
        mData = TopicsRemoteData.getInstance();
    }

    @Override
    public void getDetailsAndReplies() {
        mDetailsAndReplies.clear();
        mOffset = 0;
        getDetails();
        getReplies();
    }

    @Override
    public void follow() {
        mView.showLoading();
        mData.follow(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mView.dismissLoading();
                mView.toast("关注成功");
                mView.setFollowChecked(true);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
                mView.toast("网络错误");
            }
        });
    }

    @Override
    public void unFollow() {
        mView.showLoading();
        mData.unFollow(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mView.dismissLoading();
                mView.toast("取消关注");
                mView.setFollowChecked(false);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
                mView.toast("网络错误");
            }
        });
    }

    @Override
    public void favorite() {
        mView.showLoading();
        mData.favorite(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mView.dismissLoading();
                mView.toast("收藏成功");
                mView.setFavoriteChecked(true);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
                mView.toast("网络错误");
            }
        });
    }

    @Override
    public void unFavorite() {
        mView.showLoading();
        mData.unFavorite(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mView.dismissLoading();
                mView.toast("取消收藏");
                mView.setFavoriteChecked(false);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
                mView.toast("网络错误");
            }
        });
    }

    @Override
    public void reply(String body) {
        mView.showLoading();
        mData.addReplies(mTopicsId, body, new Callback<EntitiesContract.Reply>() {
            @Override
            public void onSuccess(EntitiesContract.Reply reply) {
                mView.dismissLoading();
                mView.showNewReply(reply);
            }

            @Override
            public void onError(String messgae) {
                mView.dismissLoading();
                mView.toast("网络错误");
            }
        });
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
                mView.showEmptyView();
            }
        });
    }

    private void getReplies() {
        Map<String, Object> params = new ArrayMap<>();
        mData.getReplies(mTopicsId, mOffset, new Callback<List<EntitiesContract.Reply>>() {

            @Override
            public void onSuccess(List<EntitiesContract.Reply> replies) {
                mOffset += replies.size();
                mDetailsAndReplies.addAll(replies);
                mView.showDetails(mDetailsAndReplies, false);
            }

            @Override
            public void onError(String messgae) {
                mView.loadedComplete();
            }
        });
    }
}
