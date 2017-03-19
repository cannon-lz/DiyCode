package com.zly.diycode.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zly.diycode.R;
import com.zly.diycode.common.BaseAdapter;
import com.zly.diycode.common.Item;
import com.zly.diycode.databinding.ItemTest1Binding;
import com.zly.diycode.databinding.ItemTopicsBinding;
import com.zly.diycode.databinding.RecyclerListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangly on 2017/3/18.
 */

public class TopicsFragment extends Fragment {

    private RecyclerListBinding mDataBinding;
    private BaseAdapter mTopicsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_list, container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.item_margin));
        mDataBinding.recyclerView.addItemDecoration(itemDecoration);
        mTopicsAdapter = new BaseAdapter(getActivity());
        mDataBinding.recyclerView.setAdapter(mTopicsAdapter);
        mTopicsAdapter.setDataList(getDatas());
        mTopicsAdapter.setPresenter(new OnItemClickListener());
        /*topicsAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter adapter, int position) {
                final List<Item> dataList = adapter.getDataList();
                final Item data = dataList.get(position);
                final int itemViewType = data.getItemViewType();
                if (itemViewType == R.layout.item_topics) {
                    Topics topics = topicsAdapter.getItemByType(itemViewType);
                    ItemTopicsBinding binding = topicsAdapter.getDataBindingByItemType(itemViewType);
                    Toast.makeText(getActivity(), topics.getContent() + " " + position + " dataBinding type " + binding.getClass().getSimpleName(), Toast.LENGTH_LONG).show();

                    final Topics topics1 = new Topics();
                    topics1.setUsername("admin");
                    topics1.setPulishDate("18天前");
                    topics1.setNote("IOS");
                    topics1.setContent("");
                    topics1.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/4260_1489765622.jpg");
                    topics1.setSubContent("FingerprintIdentify -- 安卓指纹识别库");
                    topics1.setContent("FingerprintIdentify -- 安卓指纹识别库");
                    adapter.update(topics1, position);
                }

                if (itemViewType == R.layout.item_test_1) {
                    Value value = topicsAdapter.getItemByType(itemViewType);
                    ItemTest1Binding binding = topicsAdapter.getDataBindingByItemType(itemViewType);
                    Toast.makeText(getActivity(), value.getValue() + " " + position + " dataBinding type " + binding.getClass().getSimpleName(), Toast.LENGTH_LONG).show();
                }

            }
        });*/
    }

    public class OnItemClickListener implements BaseAdapter.Presenter {


        public void onItemClick(Topics topics, int position) {
            Toast.makeText(getActivity(), topics.getContent() + " position " + position, Toast.LENGTH_LONG).show();
        }

        public void onItemClick(Value v, int position) {
            Toast.makeText(getActivity(), v.getValue() + " position " + position, Toast.LENGTH_LONG).show();
        }
    }

    private List<Item> getDatas() {
        Topics topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");

        List<Item> topicses = new ArrayList<>();
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);
        topics = new Topics();
        topics.setUserPhoto("https://diycode.b0.upaiyun.com/user/avatar/2.jpg");
        topics.setContent("2017年 Web 程序员技术发展路线图");
        topics.setNote("Android");
        topics.setPulishDate("24分钟前发布");
        topics.setUsername("zhangly");
        topicses.add(topics);

        final Value value = new Value();
        value.setValue("test");

        topicses.add(value);

        return topicses;
    }
}
