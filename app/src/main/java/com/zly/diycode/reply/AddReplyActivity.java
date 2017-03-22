package com.zly.diycode.reply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.BaseFragment;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.ActivityAddReplyBinding;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class AddReplyActivity extends BaseActivity<ActivityAddReplyBinding, VoidPresenter> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_reply;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("评论");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title = Navigation.IntentReceiver.getInstance().getTitle(this);
        mDataBinding.tvTile.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reply, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_send) {
            toast(item.getTitle().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
