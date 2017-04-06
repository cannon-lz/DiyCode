package com.zly.diycode.data.project;

import com.zly.diycode.data.Callback;

import java.util.List;

/**
 * Created by zhangluya on 2017/4/6.
 */

public interface ProjectData {

    void getProject(int offset, Callback<List<Project>> callback);
}
