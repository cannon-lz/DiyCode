package com.zly.diycode.reply;

import com.zly.diycode.common.feature.IPresenter;
import com.zly.diycode.common.feature.IView;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangluya on 2017/4/6.
 */

public interface NewTopicsContract {

    interface Presenter extends IPresenter {

        void getNodes();

        void publishTopics(String nodeId, EntitiesContract.Topics topics);

        Map<Node, List<Node>> getNodesResult();
    }

    interface View extends IView {

        void showNodes(Map<Node, List<Node>> nodes);

        void publishComplete(EntitiesContract.Topics topics);
    }
}
