package com.zly.diycode.data;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangluya on 2017/3/31.
 */

public interface AbsListData<T> {

    void getList(String nodeId, int offset, Callback<List<T>> callback);
}
