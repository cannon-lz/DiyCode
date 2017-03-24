package com.zly.diycode.topics.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.zly.diycode.R;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.ActivityTopicsDetalsBinding;

/**
 * Created by zhangluya on 2017/3/23.
 */

public class TopicsDetailsActivity extends BaseActivity<ActivityTopicsDetalsBinding, VoidPresenter> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topics_detals;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        getSupportActionBar().setTitle("Topics");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().add(R.id.ll_topics_host, new TopicsDetailsFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_topics_details, menu);
        return true;
    }
}
