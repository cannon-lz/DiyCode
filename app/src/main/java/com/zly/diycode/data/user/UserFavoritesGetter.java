package com.zly.diycode.data.user;

import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangly on 2017/4/1.
 */

public class UserFavoritesGetter implements AbsListData<EntitiesContract.Topics> {



    @Override
    public void getList(String nodeId, int offset, Callback<List<EntitiesContract.Topics>> callback) {
        UserRemoteData.getInstance().favorites(nodeId, String.valueOf(offset), callback);
    }
}
