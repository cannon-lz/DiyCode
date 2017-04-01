package com.zly.diycode.data.news;

import com.zly.diycode.data.Callback;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangluya on 2017/3/31.
 */

public interface NewsData {

    void getNews(String nodeId, String limit, Callback<List<EntitiesContract.Topics>> callback);
}
