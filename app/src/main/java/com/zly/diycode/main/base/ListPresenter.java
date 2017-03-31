package com.zly.diycode.main.base;

import com.zly.diycode.common.feature.IPresenter;

/**
 * Created by zhangluya on 2017/3/31.
 */

public interface ListPresenter extends IPresenter {

    void getTopics();

    void nextPage();
}
