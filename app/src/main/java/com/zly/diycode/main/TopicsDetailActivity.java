package com.zly.diycode.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zly.diycode.R;
import com.zly.diycode.TestActivity;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.DataBindingViewHolder;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.ActivityTopicsDetailBinding;
import com.zly.diycode.databinding.ItemTopicsDetailBinding;
import com.zly.diycode.widget.AppWebView;

import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

/**
 * Created by zhangluya on 2017/3/13.
 */

public class TopicsDetailActivity extends BaseActivity<ActivityTopicsDetailBinding, VoidPresenter> {

    private static final String CONTENT = "";

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topics_detail;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        String newsId = Navigation.IntentReceiver.getInstance().getNewsId(this);
        Log.i("NewsId", String.format("news id %s", newsId));
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Topics");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DetailAndReplyAdapter adapter = new DetailAndReplyAdapter(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setAdapter(adapter);
        adapter.setDataList(getDatas());
    }

    private class DetailAndReplyAdapter extends BaseAdapter {

        DetailAndReplyAdapter(Context context) {
            super(context);
        }

        @Override
        protected void convert(DataBindingViewHolder holder, Item data, int position) {
            Item item = getDatas().get(position);
            if (item.getItemViewType() == R.layout.item_topics_detail) {
                ItemTopicsDetailBinding binding = getDataBindingByItemType(R.layout.item_topics_detail);
                ArticleDetails details = getItemByType(R.layout.item_topics_detail, position);
                AppWebView webView = binding.webView;
                webView.loadDataWithBaseURL(null, webView.addStyleAndHeader(details.getContent(), null), "text/html", "utf-8", null);
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

    private List<Item> getDatas() {
        ArrayList<Item> datas = new ArrayList<>();
        ArticleDetails details = new ArticleDetails();
        details.setPhoto("https://diycode.cc/system/letter_avatars/2/P/255_138_96/96.png");
        details.setTitle("No ViewHolder!!!一个非官方的纯java版databinding（拒绝xml配置）");
        details.setContent(TestActivity.content);
        details.setNode("Android");
        details.setPublishDate("24分钟前发布");
        details.setUsername("zhangly");
        datas.add(details);

        Reply reply = new Reply();
        reply.setPhoto("https://diycode.cc/system/letter_avatars/2/P/255_138_96/96.png");
        reply.setUsername("abcd");
        reply.setFloor("1");
        reply.setPublishDate("3分钟前发布");
        reply.setCount(2);
        reply.setContent("test testtesttesttesttesttesttesttesttesttesttest");
        datas.add(reply);
        reply = new Reply();
        reply.setFloor("2");
        reply.setPhoto("https://diycode.cc/system/letter_avatars/2/P/255_138_96/96.png");
        reply.setUsername("abcd");
        reply.setPublishDate("3分钟前发布");
        reply.setCount(2);
        reply.setContent("test testtesttesttesttesttesttesttesttesttesttest");
        datas.add(reply);

        return datas;
    }
}
