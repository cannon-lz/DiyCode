package com.zly.diycode.main;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;

/**
 * Created by zhangly on 2017/3/12.
 */

public class Topics implements Item {

    private String userPhoto;
    private String username;
    private String note;
    private String publishDate;
    private String content;
    private String id;
    private String subContent;

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_topics;
    }

}
