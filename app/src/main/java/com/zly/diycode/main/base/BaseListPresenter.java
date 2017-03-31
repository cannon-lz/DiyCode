package com.zly.diycode.main.base;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class BaseListPresenter<T> implements ListPresenter {

    private AbsListData<T> mListData;
    private BaseListView<T> mBaseListView;
    private int mOffset = 0;
    private Map<String, Object> mParams = new ArrayMap<>();

    public BaseListPresenter(AbsListData<T> listData, BaseListView<T> baseListView) {
        this.mListData = listData;
        this.mBaseListView = baseListView;
    }

    @Override
    public void getTopics() {
        mOffset = 0;
        mListData.getList("", mOffset, new Callback<List<T>>() {
            @Override
            public void onSuccess(List<T> topicses) {
                mOffset = topicses.size();
                mBaseListView.showTopics(topicses);
            }

            @Override
            public void onError(String messgae) {
                mBaseListView.showEmptyView();
            }
        });
    }

    @Override
    public void nextPage() {
        mListData.getList("", mOffset, new Callback<List<T>>() {
            @Override
            public void onSuccess(List<T> topicses) {
                int size = topicses.size();
                if (size <= 0) {
                    mBaseListView.loadMoreError();
                } else {
                    mOffset += size;
                    mBaseListView.addTopics(topicses);
                }
            }

            @Override
            public void onError(String messgae) {
                mBaseListView.loadMoreError();
            }
        });
    }
}
