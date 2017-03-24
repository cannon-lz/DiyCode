package com.zly.diycode.common.feature;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class BaseFragment<DB extends ViewDataBinding, P extends IPresenter> extends ToolbarFragment<DB> implements IView {

    protected BaseActivity<DB, P> mHostActivity;
    @Nullable
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHostActivity = (BaseActivity<DB, P>) context;
    }

    @Override
    public void showLoading() {
        mHostActivity.showLoading();
    }

    @Override
    public void dismissLoading() {
        mHostActivity.dismissLoading();
    }

    @Override
    public void toast(String message) {
        mHostActivity.toast(message);
    }

    @Override
    public void toast(@StringRes int stringRes) {
        mHostActivity.toast(stringRes);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHostActivity = null;
    }

    protected P createPresenter() {
        return null;
    }
}
