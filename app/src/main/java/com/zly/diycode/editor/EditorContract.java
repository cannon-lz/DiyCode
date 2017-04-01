package com.zly.diycode.editor;

import com.zly.diycode.common.feature.IPresenter;
import com.zly.diycode.common.feature.IView;

/**
 * Created by zhangluya on 2017/3/31.
 */

public interface EditorContract {

    interface Presenter extends IPresenter {

        void init();

        void send(String id, String title, String content);

    }

    interface View<InitData, Result> extends IView {

        void setInitData(InitData data);

        void success(Result result);
    }
}
