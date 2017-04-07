package com.zly.diycode.topics;

import android.support.annotation.NonNull;

import com.zly.diycode.R;
import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.topics.TopicsData;
import com.zly.diycode.data.topics.TopicsRemoteData;
import com.zly.diycode.list.BaseListPresenter;
import com.zly.diycode.list.BaseListView;

import java.util.Map;

/**
 * Created by zhangly on 2017/3/22.
 */

public class TopicsDetailsPresenter extends BaseListPresenter<EntitiesContract.Reply> implements TopicsDetailsContract.Presenter {

    private TopicsData mData;
    private TopicsDetailsContract.View mThisView;
    private String mTopicsId;

    public TopicsDetailsPresenter(AbsListData<EntitiesContract.Reply> listData, BaseListView<EntitiesContract.Reply> baseListView, @NonNull Map<String, Object> params, String topicsId) {
        super(listData, baseListView, params);
        mTopicsId = topicsId;
        mThisView = getView(TopicsDetailsContract.View.class);
        mData = TopicsRemoteData.getInstance();
    }


    @Override
    public void getDetails() {
        mData.getById(mTopicsId, new Callback<EntitiesContract.Topics>() {
            @Override
            public void onSuccess(EntitiesContract.Topics topics) {
                topics.setItemViewType(R.layout.item_topics_detail);
                mThisView.showDetails(topics);
            }

            @Override
            public void onError(String messgae) {
                mThisView.showEmptyView();
            }
        });

    }

    @Override
    public void follow() {
        mThisView.showLoading();
        mData.follow(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mThisView.dismissLoading();
                mThisView.toast("关注成功");
                mThisView.setFollowChecked(true);
            }

            @Override
            public void onError(String messgae) {
                mThisView.dismissLoading();
                mThisView.toast("网络错误");
            }
        });
    }

    @Override
    public void unFollow() {
        mThisView.showLoading();
        mData.unFollow(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mThisView.dismissLoading();
                mThisView.toast("取消关注");
                mThisView.setFollowChecked(false);
            }

            @Override
            public void onError(String messgae) {
                mThisView.dismissLoading();
                mThisView.toast("网络错误");
            }
        });
    }

    @Override
    public void favorite() {
        mThisView.showLoading();
        mData.favorite(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mThisView.dismissLoading();
                mThisView.toast("收藏成功");
                mThisView.setFavoriteChecked(true);
            }

            @Override
            public void onError(String messgae) {
                mThisView.dismissLoading();
                mThisView.toast("网络错误");
            }
        });
    }

    @Override
    public void unFavorite() {
        mThisView.showLoading();
        mData.unFavorite(mTopicsId, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                mThisView.dismissLoading();
                mThisView.toast("取消收藏");
                mThisView.setFavoriteChecked(false);
            }

            @Override
            public void onError(String messgae) {
                mThisView.dismissLoading();
                mThisView.toast("网络错误");
            }
        });
    }

    @Override
    public void reply(String body) {
        mThisView.showLoading();
        mData.addReplies(mTopicsId, body, new Callback<EntitiesContract.Reply>() {
            @Override
            public void onSuccess(EntitiesContract.Reply reply) {
                mThisView.dismissLoading();
                mThisView.showNewReply(reply);
            }

            @Override
            public void onError(String messgae) {
                mThisView.dismissLoading();
                mThisView.toast("网络错误");
            }
        });
    }

}
