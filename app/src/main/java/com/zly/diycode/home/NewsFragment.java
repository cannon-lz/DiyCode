package com.zly.diycode.home;

import com.zly.diycode.common.Navigation;
import com.zly.diycode.data.news.NewsRemoteData;
import com.zly.diycode.list.BaseListFragment;
import com.zly.diycode.list.BaseListPresenter;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class NewsFragment extends BaseListFragment {

    @Override
    protected BaseListPresenter createPresenter() {
        return new BaseListPresenter<>(NewsRemoteData.getInstance(), this);
    }

    @Override
    public void onItemClick(EntitiesContract.Topics topics, int position) {
        Navigation.getInstance().openWebBrowser(getActivity(), topics.getAddress());
    }
}
