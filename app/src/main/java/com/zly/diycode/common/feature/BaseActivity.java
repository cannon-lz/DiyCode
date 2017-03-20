package com.zly.diycode.common.feature;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(@StringRes int stringRes) {
        toast(getString(stringRes));
    }

    protected P createPresenter() {
        return null;
    }

}
