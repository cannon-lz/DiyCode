package com.zly.diycode.common.feature;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.UserManager;
import com.zly.diycode.data.Callback;
import com.zly.diycode.widget.LoadingDialog;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class BaseActivity<DB extends ViewDataBinding, P extends IPresenter> extends ToolbarActivity<DB> implements IView {

    @Nullable
    protected P mPresenter;

    private Callback<Void> mCheckLoginCallback;
    private LoadingDialog mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void showLoading() {
        if (mLoading == null) {
            mLoading = new LoadingDialog(this);
            mLoading.setCanceledOnTouchOutside(false);
            mLoading.setCancelable(false);
        }

        mLoading.show();
    }

    @Override
    public void dismissLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Navigation.REQ_CODE_LOGIN) {
            if (resultCode == RESULT_OK) {
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
        if (mCheckLoginCallback != null) {
            mCheckLoginCallback.onSuccess(null);
        }
        return true;
    }

}
