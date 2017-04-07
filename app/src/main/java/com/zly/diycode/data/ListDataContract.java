package com.zly.diycode.data;

import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.topics.TopicsRemoteData;
import com.zly.diycode.data.user.UserRemoteData;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangluya on 2017/4/1.
 */

public interface ListDataContract {

    class UserTopicsGetter implements AbsListData<EntitiesContract.Topics> {

        @Override
        public void getList(int offset, Callback<List<EntitiesContract.Topics>> callback, Map<String, Object> params) {
            String order = String.valueOf(params.get("order"));
            String login = String.valueOf(params.get("login"));
            UserRemoteData.getInstance().topics(login, order, String.valueOf(offset), callback);
        }
    }

    class UserFavoritesGetter implements AbsListData<EntitiesContract.Topics> {

        @Override
        public void getList(int offset, Callback<List<EntitiesContract.Topics>> callback, Map<String, Object> params) {
            String login = String.valueOf(params.get("login"));
            UserRemoteData.getInstance().favorites(login, String.valueOf(offset), callback);
        }
    }

    class UserRepliesGetter implements AbsListData<EntitiesContract.Topics> {

        @Override
        public void getList(int offset, Callback<List<EntitiesContract.Topics>> callback, Map<String, Object> params) {
            String order = String.valueOf(params.get("order"));
            String login = String.valueOf(params.get("login"));
            UserRemoteData.getInstance().replies(login, order, String.valueOf(offset), callback);
        }
    }

    class RepliesGetter implements AbsListData<EntitiesContract.Reply> {

        @Override
        public void getList(int offset, Callback<List<EntitiesContract.Reply>> callback, Map<String, Object> params) {
            String id = String.valueOf(params.get("id"));
            TopicsRemoteData.getInstance().getReplies(id, offset, callback);
        }
    }
}
