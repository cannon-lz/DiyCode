package com.zly.diycode.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.feature.BaseFragment;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.FragmentListBinding;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class AppListFragment extends BaseFragment<FragmentListBinding, VoidPresenter> implements SwipeRefreshLayout.OnRefreshListener {

    protected BaseAdapter mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View root, @Nullable Bundle savedInstanceState) {
        mDataBinding.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseAdapter(getActivity());
        mAdapter.setPresenter(createOnItemClickListener());
        mDataBinding.setAdapter(mAdapter);
        mDataBinding.srlRefreshControl.setOnRefreshListener(this);
        onRefresh();
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataBinding.srlRefreshControl.setRefreshing(false);
            }
        }, 2000);
    }
}
