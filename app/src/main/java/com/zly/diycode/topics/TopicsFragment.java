package com.zly.diycode.topics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.main.AppListFragment;
import com.zly.diycode.main.Value;

import java.util.List;

/**
 * Created by zhangly on 2017/3/18.
 */

public class TopicsFragment extends AppListFragment<TopicsContract.ListPresenter> implements TopicsContract.ListView {

    public class OnItemClickListener implements BaseAdapter.Presenter {


        public void onItemClick(EntitiesContract.Topics topics, int position) {
            Navigation.getInstance().openDetails(getActivity(), topics.getId());
        }

        public void onItemClick(Value v, int position) {
            Toast.makeText(getActivity(), v.getValue() + " position " + position, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected TopicsContract.ListPresenter createPresenter() {
        return new TopicsListPresenter(this);
    }

    @Override
    protected void initView(View root, @Nullable Bundle savedInstanceState) {
        super.initView(root, savedInstanceState);
        mPresenter.getTopics();
    }

    @Override
    public void showTopics(List<EntitiesContract.Topics> datas) {
        mDataBinding.rcvList.setVisibility(View.VISIBLE);
        mDataBinding.tvEnpty.setVisibility(View.GONE);
        mDataBinding.srlRefreshControl.setRefreshing(false);
        mAdapter.setDataList(datas);
    }

    @Override
    public void addTopics(List<EntitiesContract.Topics> datas) {
        mDataBinding.srlRefreshControl.setRefreshing(false);
        mAdapter.addAll(datas);
    }

    @Override
    public void showEmptyView() {
        mDataBinding.rcvList.setVisibility(View.GONE);
        mDataBinding.tvEnpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        mPresenter.getTopics();
    }

    @Override
    protected void onDragBottom() {
        mPresenter.nextPage();
    }

    @Override
    protected BaseAdapter.Presenter createOnItemClickListener() {
        return new OnItemClickListener();
    }
}
