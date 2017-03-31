package com.zly.diycode.editor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.databinding.ActivityEditorBinding;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class EditorActivity extends BaseActivity<ActivityEditorBinding, EditorContract.Presenter> implements EditorContract.View {

    private AbsEditorDataBindingHelper<?> mViewHelper;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_editor;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditRequester reqBody = Navigation.IntentReceiver.getInstance().getEditorType(this);
        mViewHelper = HelperFactory.create(reqBody, mDataBinding);
        mViewHelper.handleView();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
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
            mViewHelper.send();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(Object o) {
        if (o instanceof EntitiesContract.Reply) {
            Navigation.IntentReceiver.getInstance().setReplyResult(this, (EntitiesContract.Reply) o);
        }
    }
}
