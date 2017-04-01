package com.zly.diycode.editor;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zly.diycode.databinding.ActivityEditorBinding;
import com.zly.diycode.topics.EntitiesContract;

import java.util.List;

/**
 * Created by zhangluya on 2017/3/31.
 */

public class CreatePaperViewBindingHelper extends AbsEditorDataBindingHelper<Void> {

    private CreatePaperPresenter mPresenter;

    public CreatePaperViewBindingHelper(Void aVoid, ActivityEditorBinding dataBinding) {
        super(aVoid, dataBinding);
        mPresenter = new CreatePaperPresenter((EditorContract.View<List<Node>, EntitiesContract.Topics>) dataBinding.getRoot().getContext());
        mPresenter.init();
    }

    @Override
    protected void handleView() {
        final AppCompatActivity context = (AppCompatActivity) mDataBinding.evTile.getContext();
        (context).setTitle("");
    }

    @Override
    protected void setPresenter(EditorContract.Presenter presenter) {
        mPresenter = (CreatePaperPresenter) presenter;
    }

    @Override
    protected void handleInitData(Object object) {
        final List<Node> nodes = (List<Node>) object;
        final String[] n = new String[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            n[i] = nodes.get(i).getName();
        }
        final AppCompatActivity context = (AppCompatActivity) mDataBinding.evTile.getContext();
        new AlertDialog.Builder(context).setSingleChoiceItems(n, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                (context).setTitle(n[which]);
                mPresenter.setNodeId(String.valueOf(nodes.get(which).getId()));
            }
        }).setTitle("选择节点").setCancelable(false).create().show();
    }

    @Override
    protected void send() {
        mPresenter.send("", mDataBinding.evTile.getText().toString(), mDataBinding.etReply.getText().toString());
    }
}
