package com.zly.diycode.topics.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.zly.diycode.R;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.ActivityTopicsDetalsBinding;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.widget.ToggleActionLayout;

/**
 * Created by zhangluya on 2017/3/23.
 */

public class TopicsDetailsActivity extends BaseActivity<ActivityTopicsDetalsBinding, VoidPresenter> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topics_detals;
    }

    @Override
    protected boolean isNeedInsertToolbar() {
        return false;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_topics_host, new TopicsDetailsFragment()).commit();
    }


}
