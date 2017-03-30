package com.zly.diycode.topics.list;

import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.common.feature.IPresenter;
import com.zly.diycode.common.feature.IView;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangluya on 2017/3/22.
 */

public interface TopicsContract {

    interface ListPresenter extends IPresenter {

        void getTopics();

        void nextPage();
    }

    interface ListView extends IView {

        void showTopics(List<EntitiesContract.Topics> datas);

        void addTopics(List<EntitiesContract.Topics> datas);

        void showEmptyView();

        void loadMoreError();
    }

    interface DetailsPresenter extends IPresenter {

        void getDetailsAndReplies();

        void follow();

        void unFollow();

        void favorite();

        void unFavorite();

    }

    interface DetailsView extends IView {

        void showDetails(List<Item> datas, boolean isHeaderLoadComplete);

        void setFollowChecked(boolean checked);

        void setFavoriteChecked(boolean checked);
    }
}
