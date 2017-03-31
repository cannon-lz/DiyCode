package com.zly.diycode.topics;

import android.os.Bundle;
import android.support.annotation.Nullable;

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
    protected boolean isNeedInsertToolbar() {
        return false;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mDataBinding.setPresenter(this);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_topics_host, new TopicsDetailsFragment()).commit();
    }

    public void newReply() {
        TopicsDetailsFragment f = (TopicsDetailsFragment) getSupportFragmentManager().getFragments().get(0);
        f.reply(new EntitiesContract.Reply(), 0);
    }
}
