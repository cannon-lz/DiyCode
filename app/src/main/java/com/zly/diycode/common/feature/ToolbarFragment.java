package com.zly.diycode.common.feature;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class ToolbarFragment<DB extends ViewDataBinding> extends Fragment {

    @Nullable
    protected DB mDataBinding;
    @Nullable
    protected ToolbarHelper mToolbarHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutRes = getLayoutRes();
        if (layoutRes != -1) {
            mDataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false);
            View root = mDataBinding.getRoot();
            if (isNeedInsertToolbar()) {
                mToolbarHelper = ToolbarHelper.insert(getActivity(), root);
            }
            return root;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData(view, savedInstanceState);
        initView(view, savedInstanceState);
    }

    protected
    @LayoutRes
    int getLayoutRes() {
        return -1;
    }

    protected boolean isNeedInsertToolbar() {
        return true;
    }

    protected void initData(View root, @Nullable Bundle savedInstanceState) {

    }

    protected void initView(View root, @Nullable Bundle savedInstanceState) {

    }
}
