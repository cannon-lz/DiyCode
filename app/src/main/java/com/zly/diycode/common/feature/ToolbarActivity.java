package com.zly.diycode.common.feature;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class ToolbarActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    @Nullable
    protected ToolbarHelper mToolbarHelper;
    @Nullable
    protected DB mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutRes = getLayoutRes();
        if (layoutRes != -1) {
            mDataBinding = DataBindingUtil.setContentView(this, layoutRes);
        }
        if (isNeedInsertToolbar()) {
            mToolbarHelper = ToolbarHelper.insert(this, ((ViewGroup) getWindow().getDecorView()).getChildAt(0));
            getSupportActionBar().setTitle("");
        }
        initData(savedInstanceState);
        initView(savedInstanceState);
    }

    @LayoutRes
    protected int getLayoutRes() {
        return -1;
    }

    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    protected boolean isNeedInsertToolbar() {
        return true;
    }
}
