package com.zly.diycode.topics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.webkit.WebView;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.DataBindingViewHolder;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.TopicsRemoteData;
import com.zly.diycode.databinding.ActivityTopicsDetailBinding;
import com.zly.diycode.databinding.ItemTopicsDetailBinding;
import com.zly.diycode.main.PaperDetails;
import com.zly.diycode.widget.AppWebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zhangluya on 2017/3/13.
 */

public class TopicsDetailActivity extends BaseActivity<ActivityTopicsDetailBinding, VoidPresenter> {

    private static final String CONTENT = "";
    private BaseAdapter mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topics_detail;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        String newsId = Navigation.IntentReceiver.getInstance().getNewsId(this);
        Log.i("NewsId", String.format("news id %s", newsId));
        TopicsRemoteData.getInstance().getById(newsId, new Callback<EntitiesContract.Topics>() {
            @Override
            public void onSuccess(EntitiesContract.Topics topics) {
                topics.setItemViewType(R.layout.item_topics_detail);
                mAdapter.add(topics);
                //mAdapter.setDataList(details);
            }

            @Override
            public void onError(String messgae) {

            }
        });
        Map<String, Object> p = new ArrayMap<>();
        TopicsRemoteData.getInstance().getReplies(newsId, p, new Callback<List<EntitiesContract.Reply>>() {
            @Override
            public void onSuccess(List<EntitiesContract.Reply> replies) {
                mAdapter.addAll(replies);
            }

            @Override
            public void onError(String messgae) {

            }
        });
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Topics");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAdapter = new BaseAdapter(this);
        mAdapter.addConverter(R.layout.item_topics_detail, new TopicsDetailsConverter());
        mDataBinding.setPresenter(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {

        ((BaseAdapter) mDataBinding.recyclerView.getAdapter()).removeAllConverters();

        super.onDestroy();
    }

    public void addReplyClick() {
        PaperDetails paperDetails = ((BaseAdapter) mDataBinding.recyclerView.getAdapter()).getItemByType(R.layout.item_topics_detail, 0);
        Navigation.getInstance().openAddReply(this, paperDetails.getTitle());
    }

    private class TopicsDetailsConverter implements BaseAdapter.Converter<EntitiesContract.Topics> {

        @Override
        public void convert(BaseAdapter adapter, DataBindingViewHolder viewHolder, EntitiesContract.Topics item, int position) {
            Log.i("BaseAdapterType", String.format("details position %s", position));
            ItemTopicsDetailBinding binding = adapter.getDataBindingByItemType(R.layout.item_topics_detail);
            AppWebView webView = binding.webView;
            webView.loadDataWithBaseURL(null, webView.addStyleAndHeader(item.getContent(), null), "text/html", "utf-8", null);
            webView.setWebViewClient(new AppWebView.AppWebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Navigation.getInstance().openWebBrowser(TopicsDetailActivity.this, url);
                    return true;
                }
            });
        }
    }
}
