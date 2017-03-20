package com.zly.diycode.main;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class ArticleDetails implements Item {

    private String username;
    private String node;
    private String publishDate;
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_topics_detail;
    }
}
