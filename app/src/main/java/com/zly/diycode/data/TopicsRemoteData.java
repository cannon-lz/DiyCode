package com.zly.diycode.data;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.http.ApiConfig;
import com.zly.diycode.http.RetrofitCallback;
import com.zly.diycode.http.TopicsApi;
import com.zly.diycode.http.entities.RespPaper;
import com.zly.diycode.http.entities.RespReply;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.zly.diycode.topics.EntitiesContract.*;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class TopicsRemoteData implements TopicsData {

    private static TopicsRemoteData sRemoteData = new TopicsRemoteData();

    public static TopicsData getInstance() {
        return sRemoteData;
    }

    @Override
    public void getTopics(Map<String, Object> params, Callback<List<Topics>> callback) {
        TopicsApi api = ApiConfig.getInstance().getApi(TopicsApi.class);
        Call<List<RespPaper>> topics = api.getTopics(params);
        topics.enqueue(new RetrofitCallback<List<RespPaper>, List<Topics>>(callback));
    }

    @Override
    public void getTopics(String nodeId, String offset, Callback<List<Topics>> callback) {
        Map<String, Object> params = new ArrayMap<>();
        params.put("node_id", nodeId);
        params.put("offset", offset);
        getTopics(params, callback);
    }

    @Override
    public void create(Map<String, Object> params, Callback<Topics> callback) {

    }

    @Override
    public void update(String id, Map<String, Object> params, Callback<Topics> callback) {

    }

    @Override
    public void delete(String id, Callback<Boolean> callback) {

    }

    @Override
    public void getById(String id, Callback<Topics> callback) {
        TopicsApi api = ApiConfig.getInstance().getApi(TopicsApi.class);
        Call<RespPaper> call = api.getById(id);
        call.enqueue(new RetrofitCallback<RespPaper, Topics>(callback));
    }

    @Override
    public void ban(String id, Callback<Boolean> callback) {

    }

    @Override
    public void favorite(String id, Callback<Boolean> callback) {

    }

    @Override
    public void unFavorite(String id, Callback<Boolean> callback) {

    }

    @Override
    public void follow(String id, Callback<Boolean> callback) {

    }

    @Override
    public void unFollow(String id, Callback<Boolean> callback) {

    }

    @Override
    public void getReplies(String id, Map<String, Object> params, Callback<List<Reply>> callback) {
        TopicsApi api = ApiConfig.getInstance().getApi(TopicsApi.class);
        Map<String, Object> p = new ArrayMap<>();
        Call<List<RespReply>> call = api.getReplies(id, p);
        call.enqueue(new RetrofitCallback<List<RespReply>, List<Reply>>(callback));

    }

    @Override
    public void addReplies(String topicsId, String body, Callback<Reply> callback) {
        TopicsApi api = ApiConfig.getInstance().getApi(TopicsApi.class);
        Map<String, Object> params = new ArrayMap<>();
        params.put("access_token", "0717b67273cb897ac2a9b7e5f0f4b78a2837cd14befc4bb3105516a8514275eb");
        params.put("body", body);
        Call<RespReply> call = api.addReplies(topicsId, params);
        call.enqueue(new RetrofitCallback<RespReply, Reply>(callback));
    }
}
