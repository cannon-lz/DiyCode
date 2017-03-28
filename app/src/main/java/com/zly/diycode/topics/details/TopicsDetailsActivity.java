package com.zly.diycode.topics.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.ActivityTopicsDetalsBinding;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.list.TopicsFragment;
import com.zly.diycode.widget.ToggleActionLayout;

/**
 * Created by zhangluya on 2017/3/23.
 */

public class TopicsDetailsActivity extends BaseActivity<ActivityTopicsDetalsBinding, VoidPresenter> {

    private ToggleActionLayout mLayout;
    private String mLikeCount;
    private TopicsDetailsFragment mTopicsDetailsFragment;

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
        mTopicsDetailsFragment = new TopicsDetailsFragment();
        mTopicsDetailsFragment.setOnLoadCompleteListener(new TopicsDetailsFragment.OnLoadDataCompleteListener() {
            @Override
            public void onComplete(EntitiesContract.Topics topics) {
                mLikeCount = topics.getLikeCount();
                mLayout.setBadger(mLikeCount);
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.ll_topics_host, mTopicsDetailsFragment).commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem item = menu.getItem(0);
        mLayout = (ToggleActionLayout) item.getActionView();
        mLayout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onOptionsItemSelected(item);
            }
        });
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_topics_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_collection) {
            toast("collection");
            return true;
        }
        if (itemId == R.id.action_favorite) {
            toast("favorite");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
