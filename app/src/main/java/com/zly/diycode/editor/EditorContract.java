package com.zly.diycode.editor;

import com.zly.diycode.common.feature.IPresenter;
import com.zly.diycode.common.feature.IView;

/**
 * Created by zhangluya on 2017/3/31.
 */

public interface EditorContract {

    interface Presenter extends IPresenter {

        void send(String id, String title, String content);

    }

    interface View<Result> extends IView {

        void success(Result result);
    }
}
