package com.zly.diycode.user;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;

import com.zly.diycode.common.feature.BaseActivity;

/**
 * Created by zhangluya on 2017/4/1.
 */

public class MeListActivity extends BaseActivity {

    public static final int TOPICS = 1;
    public static final int FAVORITES = 2;
    public static final int REPLIES = 3;
    public static final int SHARED = 4;

    @IntDef({TOPICS, FAVORITES, REPLIES, SHARED})
    @interface OpenType {
    }

    private SparseArrayCompat<Fragment> mListFragment = new SparseArrayCompat<>();

    {
        mListFragment.put(TOPICS, new MeTopicsFragment());
        mListFragment.put(FAVORITES, new MeFavoritesFragment());
        mListFragment.put(REPLIES, new MeRepliesFragment());
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        @OpenType
        int openType = getIntent().getIntExtra("openType", 0);
        chooseListFragment(openType);
    }

    @Override
    protected boolean isNeedInsertToolbar() {
        return false;
    }

    private void chooseListFragment(@OpenType int type) {
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, mListFragment.get(type)).commit();
    }
}
