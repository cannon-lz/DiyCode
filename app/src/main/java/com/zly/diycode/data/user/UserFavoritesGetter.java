package com.zly.diycode.data.user;

import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangly on 2017/4/1.
 */

public class UserFavoritesGetter implements AbsListData<EntitiesContract.Topics> {

    @Override
    public void getList(int offset, Callback<List<EntitiesContract.Topics>> callback, Map<String, Object> params) {
        String login = String.valueOf(params.get("login"));
        UserRemoteData.getInstance().favorites(login, String.valueOf(offset), callback);
    }
}
