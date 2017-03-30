package com.zly.diycode.common.feature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.UserManager;
import com.zly.diycode.data.Callback;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class BaseFragment<DB extends ViewDataBinding, P extends IPresenter> extends ToolbarFragment<DB> implements IView {

    protected BaseActivity<DB, P> mHostActivity;
    @Nullable
    protected P mPresenter;
    private Callback<Void> mCheckLoginCallback;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Navigation.REQ_CODE_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                if (mCheckLoginCallback != null) {
                    mCheckLoginCallback.onSuccess(null);
                }
            } else {
                if (mCheckLoginCallback != null) {
                    mCheckLoginCallback.onError("取消登录");
                }
            }
        }
    }

    public boolean checkLogin(Callback<Void> callback) {
        mCheckLoginCallback = callback;
        if (!UserManager.getInstance().isLogin()) {
            Navigation.getInstance().openLogin(this);
            return false;
        }
        return true;
    }
}
