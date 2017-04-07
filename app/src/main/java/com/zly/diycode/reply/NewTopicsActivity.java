package com.zly.diycode.reply;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zly.diycode.R;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.databinding.ActivityNewTopicsBinding;
import com.zly.diycode.topics.EntitiesContract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangluya on 2017/4/6.
 */

public class NewTopicsActivity extends BaseActivity<ActivityNewTopicsBinding, NewTopicsContract.Presenter> implements NewTopicsContract.View {

    private boolean mYetChooseParentNode;
    private NodeChooseDialog mChooseParentDialog;
    private NodeChooseDialog mChooseChildDialog;
    private Node mParentNode;
    private Node mChildNode;

    @Override
    protected NewTopicsContract.Presenter createPresenter() {
        return new NewTopicsPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_new_topics;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.pulish_paper);
        mDataBinding.setClickHandler(this);
        mPresenter.getNodes();
    }

    public void chooseParentNode() {
        if (mChooseParentDialog == null) {
            mChooseParentDialog = new NodeChooseDialog(this);
            mChooseParentDialog.setOnCheckedListener(new NodeChooseDialog.OnCheckedListener() {
                @Override
                public void onChecked(Node node) {
                    mParentNode = node;
                    mChildNode = mPresenter.getNodesResult().get(node).get(0);
                    mDataBinding.setParentNode(mParentNode);
                    mDataBinding.setChildNode(mChildNode);
                }
            });
        }
        mChooseParentDialog.setNodes(new ArrayList<Node>(mPresenter.getNodesResult().keySet()));
        mChooseParentDialog.show();
    }

    public void chooseChildNode() {
        if (mChooseChildDialog == null) {
            mChooseChildDialog = new NodeChooseDialog(this);
            mChooseChildDialog.setOnCheckedListener(new NodeChooseDialog.OnCheckedListener() {
                @Override
                public void onChecked(Node node) {
                    mChildNode = node;
                    mDataBinding.setChildNode(mChildNode);
                }
            });
        }
        mChooseChildDialog.setNodes(mPresenter.getNodesResult().get(mParentNode));
        mChooseChildDialog.show();
    }

    public void publish() {
        String title = mDataBinding.etTopicsTitle.getText().toString();
        String content = mDataBinding.etTopicsContent.getText().toString();
        EntitiesContract.Topics topic = new EntitiesContract.Topics();
        topic.setTitle(title);
        topic.setContent(content);
        mPresenter.publishTopics(String.valueOf(mChildNode.getId()), topic);
    }

    @Override
    public void showNodes(Map<Node, List<Node>> nodes) {
        Iterator<Map.Entry<Node, List<Node>>> iterator = nodes.entrySet().iterator();
        Map.Entry<Node, List<Node>> next = iterator.next();
        mParentNode = next.getKey();
        mChildNode = mPresenter.getNodesResult().get(mParentNode).get(0);
        mDataBinding.setParentNode(mParentNode);
        mDataBinding.setChildNode(mChildNode);
    }

    @Override
    public void publishComplete(EntitiesContract.Topics topics) {
        toast("发布成功！");
        finish();
    }
}
