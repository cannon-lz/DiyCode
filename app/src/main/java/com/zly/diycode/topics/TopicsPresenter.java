package com.zly.diycode.topics;

import com.zly.diycode.data.Callback;
import com.zly.diycode.data.TopicsData;
import com.zly.diycode.data.TopicsRemoteData;

import java.util.List;

import static com.zly.diycode.topics.EntitiesContract.*;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class TopicsPresenter implements TopicsContract.Presenter {

    private TopicsContract.View mView;
    private TopicsData mDataRe;
    private int mPageNo = 1;

    public TopicsPresenter(TopicsContract.View view) {
        mDataRe = TopicsRemoteData.getInstance();
        mView = view;
    }

    @Override
    public void getTopics() {
        mDataRe.getTopics("", String.valueOf(mPageNo), new Callback<List<EntitiesContract.Topics>>() {
            @Override
            public void onSuccess(List<Topics> topicses) {
                mView.showTopics(topicses);
            }

            @Override
            public void onError(String messgae) {
                mView.showEmptyView();
            }
        });
    }

    @Override
    public void nextPage() {
        mPageNo++;
        mDataRe.getTopics("", String.valueOf(mPageNo), new Callback<List<Topics>>() {
            @Override
            public void onSuccess(List<Topics> topicses) {
                mView.addTopics(topicses);
            }

            @Override
            public void onError(String messgae) {
                mView.showEmptyView();
            }
        });
    }
}
