package com.zly.diycode.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.data.user.ListDataContract;
import com.zly.diycode.list.BaseListFragment;
import com.zly.diycode.list.BaseListPresenter;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangluya on 2017/4/1.
 */

public class MeRepliesFragment extends BaseListFragment {

    @Override
    protected BaseListPresenter createPresenter() {
        String loginName = "";
        if (mHostActivity instanceof MeListActivity) {
            loginName = mHostActivity.getIntent().getStringExtra("loginName");
        }
        Map<String, Object> params = new ArrayMap<>();
        params.put("order", "recent");
        params.put("login", loginName);
        return new BaseListPresenter<>(new ListDataContract.UserRepliesGetter(), this, params);
    }

    @Override
    protected boolean isNeedInsertToolbar() {
        return true;
    }

    @Override
    protected void initView(View root, @Nullable Bundle savedInstanceState) {
        super.initView(root, savedInstanceState);
        mDataBinding.rcvList.removeItemDecoration(getDefaultDividerItemDecoration());
        mDataBinding.rcvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        ActionBar actionBar = mHostActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.title_me_replies);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showTopics(List<EntitiesContract.Topics> datas) {
        fullItemLayout(datas);
        super.showTopics(datas);
    }

    @Override
    public void addTopics(List<EntitiesContract.Topics> datas) {
        fullItemLayout(datas);
        super.addTopics(datas);
    }

    private void fullItemLayout(List<EntitiesContract.Topics> datas) {
        for (EntitiesContract.Topics t : datas) {
            t.setItemViewType(R.layout.item_me_replies);
        }
    }

    @Override
    public void onItemClick(EntitiesContract.Topics topics, int position) {
        Navigation.getInstance().openDetails(mHostActivity, topics.getTopicsId());
    }
}
