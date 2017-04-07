package com.zly.diycode.reply;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by zhangluya on 2017/4/6.
 */

public class NodeChooseDialog extends Dialog implements BaseAdapter.Presenter {

    public interface OnCheckedListener {

        void onChecked(Node node);
    }

    private OnCheckedListener mOnCheckedListener;
    private BaseAdapter mBaseAdapter;

    public NodeChooseDialog(@NonNull Context context) {
        super(context, R.style.DialogNoTitle);
        mBaseAdapter = new BaseAdapter(context);
    }

    public void setNodes(List<Node> nodes) {
        mBaseAdapter.setDataList(nodes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBaseAdapter.setPresenter(this);
        recyclerView.setAdapter(mBaseAdapter);
        setContentView(recyclerView);
    }

    public void check(Node node) {
        dismiss();
        if (mOnCheckedListener != null) {
            mOnCheckedListener.onChecked(node);
        }
    }

    public void setOnCheckedListener(OnCheckedListener onCheckedListener) {
        this.mOnCheckedListener = onCheckedListener;
    }
}
