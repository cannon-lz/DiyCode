package com.zly.diycode.data.project;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.data.AbsListData;
import com.zly.diycode.data.Callback;
import com.zly.diycode.http.ApiConfig;
import com.zly.diycode.http.ProjectApi;
import com.zly.diycode.http.RetrofitCallback;
import com.zly.diycode.http.entities.RespProject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by zhangluya on 2017/4/6.
 */

public class ProjectRemoteData implements ProjectData, AbsListData<Project> {


    public void getProject(int offset, Callback<List<Project>> callback) {
        Map<String, Object> params = new ArrayMap<>();
        params.put("offset", offset);
        ProjectApi api = ApiConfig.getInstance().getApi(ProjectApi.class);
        Call<List<RespProject>> call = api.getProjects(params);
        call.enqueue(new RetrofitCallback<List<RespProject>, List<Project>>(callback));
    }

    @Override
    public void getList(int offset, Callback<List<Project>> callback, Map<String, Object> params) {
        getProject(offset, callback);
    }
}
