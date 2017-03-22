package com.zly.diycode.main;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;

/**
 * Created by zhangluya on 2017/3/20.
 */

public class PaperDetails implements Item {

    private String photo;
    private String username;
    private String node;
    private String publishDate;
    private String title;
    private String content;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_topics_detail;
    }


}
