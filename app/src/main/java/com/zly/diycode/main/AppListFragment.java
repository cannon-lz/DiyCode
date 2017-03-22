package com.zly.diycode.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.FooterAdapter;
import com.zly.diycode.common.feature.BaseFragment;
import com.zly.diycode.common.feature.IPresenter;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.FragmentListBinding;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class AppListFragment<Presenter extends IPresenter> extends BaseFragment<FragmentListBinding, Presenter>
        implements SwipeRefreshLayout.OnRefreshListener {

    protected BaseAdapter mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View root, @Nullable Bundle savedInstanceState) {
        mDataBinding.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new FooterAdapter(getActivity(), new EntitiesContract.ItemProgress());
        mAdapter.setPresenter(createOnItemClickListener());
        mDataBinding.setAdapter(mAdapter);
        mDataBinding.srlRefreshControl.setOnRefreshListener(this);
        mDataBinding.rcvList.addOnScrollListener(new OnBottomScrollListener());
    }

    protected BaseAdapter.Presenter createOnItemClickListener() {
        return null;
    }

    @Override
    protected boolean isNeedInsertToolbar() {
        return false;
    }

    @Override
    public void onRefresh() {

    }

    protected void onDragBottom() {

    }

    private class OnBottomScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (visibleItemPosition == adapter.getItemCount() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                Log.i("RecyclerViewScroll", "Bottom");
                onDragBottom();
            }
        }
    }
}
