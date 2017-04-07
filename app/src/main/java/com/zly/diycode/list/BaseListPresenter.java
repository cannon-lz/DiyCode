package com.zly.diycode.list;

import android.support.annotation.NonNull;

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
    private Map<String, Object> mParams;

    public BaseListPresenter(AbsListData<T> listData, BaseListView<T> baseListView, @NonNull Map<String, Object> params) {
        this.mListData = listData;
        this.mBaseListView = baseListView;
        mParams = params;
    }

    @Override
    public void getTopics() {
        mOffset = 0;
        mListData.getList(mOffset, new Callback<List<T>>() {
            @Override
            public void onSuccess(List<T> topicses) {
                int size = topicses.size();
                if (topicses.isEmpty()) {
                    mBaseListView.showEmptyView();
                } else {
                    mOffset = size + 1;
                    mBaseListView.show(topicses);
                }

            }

            @Override
            public void onError(String messgae) {
                mBaseListView.showEmptyView();
            }
        }, mParams);
    }

    @Override
    public void nextPage() {
        mListData.getList(mOffset, new Callback<List<T>>() {
            @Override
            public void onSuccess(List<T> topicses) {
                int size = topicses.size();
                if (size <= 0) {
                    mBaseListView.loadMoreComplete();
                } else {
                    mOffset += size;
                    mBaseListView.add(topicses);
                }
            }

            @Override
            public void onError(String messgae) {
                mBaseListView.loadMoreComplete();
            }
        }, mParams);
    }

    @Override
    public int getOffset() {
        return mOffset;
    }

    public <R extends BaseListView<T>> R getView(Class<R> clazz) {
        return clazz.cast(mBaseListView);
    }
}
