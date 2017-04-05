package com.zly.diycode.topics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.DataBindingViewHolder;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.databinding.ItemTopicsDetailBinding;
import com.zly.diycode.editor.ReplyDialog;
import com.zly.diycode.list.AppListFragment;
import com.zly.diycode.editor.EditRequester;
import com.zly.diycode.widget.AppWebView;
import com.zly.diycode.widget.ToggleActionLayout;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zhangluya on 2017/3/23.
 */

public class TopicsDetailsFragment extends AppListFragment<TopicsDetailsPresenter>
        implements TopicsDetailsContract.View, BaseAdapter.Presenter {

    private ToggleActionLayout mTalFollow;
    private ToggleActionLayout mTalFavorite;

    @Override
    protected TopicsDetailsPresenter createPresenter() {
        return new TopicsDetailsPresenter(this, Navigation.IntentReceiver.getInstance().getNewsId((TopicsDetailsActivity) getActivity()));
    }

    @Override
    protected void initView(android.view.View root, @Nullable Bundle savedInstanceState) {
        super.initView(root, savedInstanceState);
        setHasOptionsMenu(true);
        ActionBar actionBar = mHostActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.title_topics);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mAdapter.addConverter(R.layout.item_topics_detail, new TopicsDetailsConverter());
        mAdapter.addConverter(R.layout.item_reply, new RepliesConverter());
        onRefresh();
    }

    @Override
    protected BaseAdapter.Presenter createOnItemClickListener() {
        return this;
    }

    @Override
    protected boolean isNeedInsertToolbar() {
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_topics_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        int size = menu.size();
        mTalFollow = (ToggleActionLayout) menu.findItem(R.id.action_follow).getActionView();
        mTalFavorite = (ToggleActionLayout) menu.findItem(R.id.action_favorite).getActionView();
        for (int i = 0; i < size; i++) {
            final MenuItem menuItem = menu.getItem(i);
            android.view.View actionView = menuItem.getActionView();
            if (actionView != null) {
                actionView.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        onOptionsItemSelected(menuItem);
                    }
                });
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (!checkLogin(null)) {
            return super.onOptionsItemSelected(item);
        }
        if (itemId == R.id.action_follow) {
            if (mTalFollow.isChecked()) {
                mPresenter.unFollow();
            } else {
                mPresenter.follow();

            }
            return true;
        }

        if (itemId == R.id.action_favorite) {
            if (mTalFavorite.isChecked()) {
                mPresenter.unFavorite();
            } else {
                mPresenter.favorite();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDetails(List<Item> datas, boolean isHeaderLoadComplete) {
        mDataBinding.srlRefreshControl.setRefreshing(false);
        mAdapter.setDataList(datas);
        setItemProgress();
        EntitiesContract.Topics topics = mAdapter.getItemByType(R.layout.item_topics_detail, 0);
        if (topics != null) {
            mTalFavorite.setChecked(topics.isFavorited());
            mTalFollow.setChecked(topics.isFollowed());
        }
    }

    @Override
    public void setFollowChecked(boolean isFollow) {
        mTalFollow.setChecked(isFollow);
    }

    @Override
    public void setFavoriteChecked(boolean checked) {
        mTalFavorite.setChecked(checked);
    }

    @Override
    public void showEmptyView() {
        setLoadMoreComplete();
        mDataBinding.srlRefreshControl.setRefreshing(false);
        mDataBinding.tvEnpty.setVisibility(View.VISIBLE);
        mDataBinding.rcvList.setVisibility(View.GONE);
    }

    @Override
    public void loadedComplete() {
        setLoadMoreComplete();
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
        if (checkLogin(null)) {
            EntitiesContract.Topics topics = mAdapter.getItemByType(R.layout.item_topics_detail, 0);
            EditRequester editRequester = new EditRequester();
            editRequester.setType(EditRequester.TYPE_REPLY);
            editRequester.setPaperTitle(topics.getTitle());
            editRequester.setPaperId(topics.getId());
            editRequester.setFloor(String.valueOf(position));
            editRequester.setLoginName(reply.getLoginName());
            //Navigation.getInstance().openEditor(this, editRequester);

            final ReplyDialog replyDialog = new ReplyDialog(getActivity(), topics);
            replyDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
