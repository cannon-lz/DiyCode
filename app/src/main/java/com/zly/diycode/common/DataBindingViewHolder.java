package com.zly.diycode.common;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zhangly on 2017/3/18.
 */

public class DataBindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mDataBinding;

    public DataBindingViewHolder(ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        mDataBinding = dataBinding;
    }

    public ViewDataBinding getDataBinding() {
        return mDataBinding;
    }
}
