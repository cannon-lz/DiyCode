package com.zly.diycode.topics.list;

import com.zly.diycode.data.Callback;
import com.zly.diycode.data.TopicsData;
import com.zly.diycode.data.TopicsRemoteData;

import java.util.List;

import static com.zly.diycode.topics.EntitiesContract.*;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class TopicsListPresenter implements TopicsContract.ListPresenter {

    private TopicsContract.ListView mListView;
    private TopicsData mDataRe;
    private int mOffset = 0;

    public TopicsListPresenter(TopicsContract.ListView listView) {
        mDataRe = TopicsRemoteData.getInstance();
        mListView = listView;
    }

    @Override
    public void getTopics() {
        mOffset = 0;
        mDataRe.getTopics("", String.valueOf(mOffset), new Callback<List<Topics>>() {
            @Override
            public void onSuccess(List<Topics> topicses) {
                mOffset = topicses.size();
                mListView.showTopics(topicses);
            }

            @Override
            public void onError(String messgae) {
                mListView.showEmptyView();
            }
        });
    }

    @Override
    public void nextPage() {
        mDataRe.getTopics("", String.valueOf(mOffset), new Callback<List<Topics>>() {
            @Override
            public void onSuccess(List<Topics> topicses) {
                int size = topicses.size();
                if (size <= 0) {
                    mListView.loadMoreError();
                } else {
                    mOffset += size;
                    mListView.addTopics(topicses);
                }
            }

            @Override
            public void onError(String messgae) {
                mListView.loadMoreError();
            }
        });
    }
}
