package com.zly.diycode.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.zly.diycode.R;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.ItemMeHeaderBinding;
import com.zly.diycode.home.MeModel;

/**
 * Created by zhangluya on 2017/4/13.
 */

public class MeActivity extends BaseActivity<ItemMeHeaderBinding, VoidPresenter> {

    public static Intent getIntent(Context context, MeModel me) {
        Intent intent = new Intent(context, MeActivity.class);
        intent.putExtra("me", me);

        return intent;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_me_header;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("我的");
        mDataBinding.setMe((MeModel) getIntent().getParcelableExtra("me"));
    }
}
