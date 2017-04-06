package com.zly.diycode.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangly on 2017/3/18.
 */

public class BaseListFragment<DataType extends Item> extends AppListFragment<BaseListPresenter>
        implements BaseListView<DataType>, BaseAdapter.Presenter {

    @Override
    protected void initView(View root, @Nullable Bundle savedInstanceState) {
        super.initView(root, savedInstanceState);
        onRefresh();
    }

    @Override
    public void showTopics(List<DataType> datas) {
        mDataBinding.rcvList.setVisibility(View.VISIBLE);
        mDataBinding.tvEnpty.setVisibility(View.GONE);
        mDataBinding.srlRefreshControl.setRefreshing(false);
        mAdapter.setDataList(datas);
        loadMoreComplete();
    }

    @Override
    public void addTopics(List<DataType> datas) {
        mDataBinding.srlRefreshControl.setRefreshing(false);
        setLoadMoreComplete();
        mAdapter.addAll(datas);
        loadMoreComplete();
    }

    @Override
    public void showEmptyView() {
        mDataBinding.srlRefreshControl.setRefreshing(false);
        mDataBinding.rcvList.setVisibility(View.GONE);
        mDataBinding.tvEnpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadMoreComplete() {
        setLoadMoreComplete();
        int lastPosition = mAdapter.getItemCount() - 1;
        EntitiesContract.ItemProgress itemProgress = mAdapter.getItemByType(R.layout.item_progress, lastPosition);
        itemProgress.setStatus(EntitiesContract.ItemProgress.STATUS_COMPLETE);
        mAdapter.update(itemProgress, lastPosition);
    }

    @Override
    public void onRefresh() {
        mPresenter.getTopics();
    }

    @Override
    protected void onDragBottom() {
        int lastPosition = mAdapter.getItemCount() - 1;
        EntitiesContract.ItemProgress itemProgress = mAdapter.getItemByType(R.layout.item_progress, lastPosition);
        if (itemProgress.isLoadComplete()) {
            itemProgress.setStatus(EntitiesContract.ItemProgress.STATUS_LOADING);
            mAdapter.update(itemProgress, lastPosition);
        }
        mPresenter.nextPage();
    }

    @Override
    protected BaseAdapter.Presenter createOnItemClickListener() {
        return this;
    }

    public void onItemClick(EntitiesContract.Topics topics, int position) {
    }
}
