package com.zly.diycode.topics;

import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.common.feature.IPresenter;
import com.zly.diycode.common.feature.IView;
import com.zly.diycode.list.BaseListView;
import com.zly.diycode.list.ListPresenter;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangluya on 2017/3/22.
 */

public interface TopicsDetailsContract {

    interface Presenter extends ListPresenter {

        void getDetails();

        void follow();

        void unFollow();

        void favorite();

        void unFavorite();

        void reply(String body);

    }

    interface View extends BaseListView<EntitiesContract.Reply> {

        void showDetails(EntitiesContract.Topics topics);

        void setFollowChecked(boolean checked);

        void setFavoriteChecked(boolean checked);

        void showNewReply(EntitiesContract.Reply reply);
    }
}
