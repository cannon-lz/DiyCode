package com.zly.diycode.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.Toast;

import com.zly.diycode.R;
import com.zly.diycode.TestActivity;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangly on 2017/3/18.
 */

public class TopicsFragment extends AppListFragment {

    public class OnItemClickListener implements BaseAdapter.Presenter {


        public void onItemClick(Topics topics, int position) {
            Navigation.getInstance().openDetails(getActivity(), String.valueOf(position));
            //startActivity(new Intent(mHostActivity, TestActivity.class));
        }

        public void onItemClick(Value v, int position) {
            Toast.makeText(getActivity(), v.getValue() + " position " + position, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void initView(View root, @Nullable Bundle savedInstanceState) {
        super.initView(root, savedInstanceState);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.item_margin));
        mDataBinding.rcvList.addItemDecoration(decoration);
        mAdapter.setDataList(getDatas());

    }

    @Override
    protected BaseAdapter.Presenter createOnItemClickListener() {
        return new OnItemClickListener();
    }

    private List<Item> getDatas() {
        Topics topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");

        List<Item> topicses = new ArrayList<>();
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPublishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);

        final Value value = new Value();
        value.setValue("test");

        topicses.add(value);

        return topicses;
    }
}
