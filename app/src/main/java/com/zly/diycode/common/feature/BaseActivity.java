package com.zly.diycode.common.feature;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class BaseActivity<DB extends ViewDataBinding, P extends IPresenter> extends ToolbarActivity<DB> implements IView {

    @Nullable
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void toast(String message) {
        Snackbar.make(getWindow().getDecorView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void toast(@StringRes int stringRes) {
        Snackbar.make(getWindow().getDecorView(), stringRes, Snackbar.LENGTH_SHORT).show();
    }

    protected P createPresenter() {
        return null;
    }

}
