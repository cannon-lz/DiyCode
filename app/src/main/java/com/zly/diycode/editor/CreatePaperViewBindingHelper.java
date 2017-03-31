package com.zly.diycode.editor;

import android.support.v7.app.AppCompatActivity;

import com.zly.diycode.databinding.ActivityEditorBinding;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class CreatePaperViewBindingHelper extends AbsEditorDataBindingHelper<Void> {

    public CreatePaperViewBindingHelper(Void aVoid, ActivityEditorBinding dataBinding) {
        super(aVoid, dataBinding);
    }

    @Override
    protected void handleView() {
        ((AppCompatActivity)mDataBinding.evTile.getContext()).setTitle("");
    }

    @Override
    protected void setPresenter(EditorContract.Presenter presenter) {

    }

    @Override
    protected void send() {

    }
}
