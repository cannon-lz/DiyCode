package com.zly.diycode.editor;

import com.zly.diycode.databinding.ActivityEditorBinding;

/**
 * Created by zhangluya on 2017/3/31.
 */

public abstract class AbsEditorDataBindingHelper<Data> {

    protected Data data;
    protected ActivityEditorBinding mDataBinding;

    public AbsEditorDataBindingHelper(Data data, ActivityEditorBinding dataBinding) {
        this.data = data;
        mDataBinding = dataBinding;
    }

    protected abstract void handleView();

    protected abstract void setPresenter(EditorContract.Presenter presenter);

    protected abstract void send();

}
