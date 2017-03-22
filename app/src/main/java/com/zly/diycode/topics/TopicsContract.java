package com.zly.diycode.topics;

import com.zly.diycode.common.feature.IPresenter;
import com.zly.diycode.common.feature.IView;

import java.util.List;

/**
 * Created by zhangluya on 2017/3/22.
 */

public interface TopicsContract {

    interface Presenter extends IPresenter {

        void getTopics();

        void nextPage();
    }

    interface View extends IView {

        void showTopics(List<EntitiesContract.Topics> datas);

        void addTopics(List<EntitiesContract.Topics> datas);

        void showEmptyView();
    }
}
