package com.zly.diycode.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zly.diycode.R;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.user.UserRemoteData;
import com.zly.diycode.databinding.ActivityLoginBinding;
import com.zly.diycode.http.Config;

/**
 * Created by zhangly on 2017/3/11.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, VoidPresenter> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mDataBinding.setClickHandler(this);
        mDataBinding.setUser(new User());
    }

    @Override
    protected boolean isNeedInsertToolbar() {
        return false;
    }

    public void clickLogin(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            toast("帐号或密码不能为空");
            return;
        }
        showLoading();
        UserRemoteData.getInstance().login(Config.GRANT_TYPE_PASSWORD, username, password, new Callback<User>() {
            @Override
            public void onSuccess(User user) {
                dismissLoading();
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onError(String messgae) {
                dismissLoading();
                toast("帐号或密码错误");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
