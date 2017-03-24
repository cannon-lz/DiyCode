package com.zly.diycode.topics.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.DataBindingViewHolder;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.databinding.ItemTopicsDetailBinding;
import com.zly.diycode.main.AppListFragment;
import com.zly.diycode.reply.ReplyMessage;
import com.zly.diycode.topics.EntitiesContract;
import com.zly.diycode.topics.list.TopicsContract;
import com.zly.diycode.widget.AppWebView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zhangluya on 2017/3/23.
 */

public class TopicsDetailsFragment extends AppListFragment<TopicsDetailsPresenter>
        implements TopicsContract.DetailsView, BaseAdapter.Presenter {

    @Override
    protected TopicsDetailsPresenter createPresenter() {
        return new TopicsDetailsPresenter(this, Navigation.IntentReceiver.getInstance().getNewsId((TopicsDetailsActivity) getActivity()));
    }

    @Override
    protected void initView(View root, @Nullable Bundle savedInstanceState) {
        super.initView(root, savedInstanceState);
        mDataBinding.rcvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter.addConverter(R.layout.item_topics_detail, new TopicsDetailsConverter());
        mAdapter.addConverter(R.layout.item_reply, new RepliesConverter());
        onRefresh();
    }

    @Override
    protected BaseAdapter.Presenter createOnItemClickListener() {
        return this;
    }

    @Override
    public void showDetails(List<Item> datas) {
        mDataBinding.srlRefreshControl.setRefreshing(false);
        mAdapter.setDataList(datas);
        setItemProgress();
    }

    @Override
    public void onRefresh() {
        mPresenter.getDetailsAndReplies();
    }

    /**
     * 回复评论
     *
     * @param reply
     */
    public void reply(EntitiesContract.Reply reply, int position) {
        Log.i("Reply", "click reply");
        EntitiesContract.Topics topics = mAdapter.getItemByType(R.layout.item_topics_detail, 0);
        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setReply(reply);
        replyMessage.setTopics(topics);
        replyMessage.setFloor(String.valueOf(position));
        Navigation.getInstance().openAddReply(this, replyMessage);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Navigation.REQ_CODE_ADD_REPLY && resultCode == RESULT_OK) {
            EntitiesContract.Reply topics = Navigation.IntentReceiver.getInstance().getReplyResult(data);
            mAdapter.add(topics);
        }
    }

    private void setItemProgress() {
        int lastPosition = mAdapter.getItemCount() - 1;
        EntitiesContract.ItemProgress itemProgress = mAdapter.getItemByType(R.layout.item_progress, lastPosition);
        itemProgress.setStatus(EntitiesContract.ItemProgress.STATUS_COMPLETE);
        mAdapter.update(itemProgress, lastPosition);
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
                    Navigation.getInstance().openWebBrowser(getActivity(), url);
                    return true;
                }
            });
        }
    }
}
