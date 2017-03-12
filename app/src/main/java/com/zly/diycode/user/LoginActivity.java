package com.zly.diycode.user;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zly.diycode.main.MainActivity;
import com.zly.diycode.R;
import com.zly.diycode.databinding.LoginActivityBinding;

/**
 * Created by zhangly on 2017/3/11.
 */

public class LoginActivity extends AppCompatActivity {

    private LoginActivityBinding mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        mDataBinding.setClickHandler(new ClickHandler());
        mDataBinding.setUser(new User());
    }

    public class ClickHandler {

        public void clickLogin(String username, String password) {
            Toast.makeText(LoginActivity.this, "username " + username + "password " + password, Toast.LENGTH_LONG).show();

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }
}
