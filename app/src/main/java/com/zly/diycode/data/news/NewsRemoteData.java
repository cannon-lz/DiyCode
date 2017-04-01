package com.zly.diycode.data.news;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;
import com.zly.diycode.http.ApiConfig;
import com.zly.diycode.http.NewsApi;
import com.zly.diycode.http.RetrofitCallback;
import com.zly.diycode.http.entities.RespPaper;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class NewsRemoteData implements NewsData, AbsListData<EntitiesContract.Topics> {

    private static NewsRemoteData sInstance = new NewsRemoteData();

    public static NewsRemoteData getInstance() {
        return sInstance;
    }

    @Override
    public void getNews(String nodeId, String offset, Callback<List<EntitiesContract.Topics>> callback) {
        NewsApi api = ApiConfig.getInstance().getApi(NewsApi.class);
        Map<String, Object> params = new ArrayMap<>();
        params.put("node_id", nodeId);
        params.put("offset", offset);
        Call<List<RespPaper>> news = api.getNews(params);
        news.enqueue(new RetrofitCallback<List<RespPaper>, List<EntitiesContract.Topics>>(callback));
    }

    @Override
    public void getList(int offset, Callback<List<EntitiesContract.Topics>> callback, Map<String, Object> params) {
        String nodeId = String.valueOf(params.get("node_id"));
        getNews(nodeId, String.valueOf(offset), callback);
    }
}
