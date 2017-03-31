package com.zly.diycode.editor;

import com.zly.diycode.databinding.ActivityEditorBinding;

import static com.zly.diycode.editor.EditRequester.TYPE_CREATE_PAPER;
import static com.zly.diycode.editor.EditRequester.TYPE_REPLY;

/**
 * Created by zhangluya on 2017/3/31.
 */

final class HelperFactory {

    static AbsEditorDataBindingHelper create(EditRequester message, ActivityEditorBinding binding) {
        AbsEditorDataBindingHelper helper = null;
        switch (message.getType()) {
            case TYPE_CREATE_PAPER:
                helper = new CreatePaperViewBindingHelper(null, binding);
                break;
            case TYPE_REPLY:
                helper = new ReplyViewBindingHelper(message, binding);
                break;
        }

        return helper;
    }

    static EditorContract.Presenter create(EditRequester message, EditorContract.View view) {
        switch (message.getType()) {
            case EditRequester.TYPE_REPLY:
                return new ReplyPresenter(view);
            case EditRequester.TYPE_CREATE_PAPER:
                return null;
        }
        return null;
    }
}
