package com.zly.diycode.project;

import android.support.v4.util.ArrayMap;

import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.feature.BaseFragment;
import com.zly.diycode.data.project.Project;
import com.zly.diycode.data.project.ProjectRemoteData;
import com.zly.diycode.list.BaseListFragment;
import com.zly.diycode.list.BaseListPresenter;
import com.zly.diycode.list.BaseListView;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangluya on 2017/4/6.
 */

public class ProjectFragment extends BaseListFragment<Project> {

    @Override
    protected BaseListPresenter createPresenter() {
        return new BaseListPresenter<>(new ProjectRemoteData(), this, new ArrayMap<String, Object>());
    }

    @Override
    public void onItemClick(Project data, int position) {
        Navigation.getInstance().openProjectDetails(getActivity(), data);
    }
}
