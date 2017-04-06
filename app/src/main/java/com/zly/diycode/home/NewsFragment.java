package com.zly.diycode.home;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.common.Navigation;
import com.zly.diycode.data.news.NewsRemoteData;
import com.zly.diycode.list.BaseListFragment;
import com.zly.diycode.list.BaseListPresenter;
import com.zly.diycode.topics.EntitiesContract;

import java.util.Map;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class NewsFragment extends BaseListFragment<EntitiesContract.Topics> {

    @Override
    protected BaseListPresenter createPresenter() {
        ArrayMap<String, Object> params = new ArrayMap<>();
        params.put("node_id", "");
        return new BaseListPresenter<>(NewsRemoteData.getInstance(), this, params);
    }

    @Override
    public void onItemClick(EntitiesContract.Topics topics, int position) {
        Navigation.getInstance().openWebBrowser(getActivity(), topics.getAddress());
    }
}
