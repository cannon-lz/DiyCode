package com.zly.diycode.http.entities;

import com.google.gson.annotations.SerializedName;
import com.zly.diycode.common.DateUtils;
import com.zly.diycode.data.Mapper;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class RespReply implements Mapper<EntitiesContract.Reply> {


    /**
     * id : 2022
     * body_html : <p>深夜回复一发，大赞！ </p>
     * created_at : 2016-11-08T00:08:34.937+08:00
     * updated_at : 2017-03-20T09:58:30.620+08:00
     * deleted : false
     * topic_id : 411
     * likes_count : 3
     */

    private int id;
    @SerializedName("body_html")
    private String bodyHtml;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private boolean deleted;
    @SerializedName("topic_id")
    private int topicId;
    @SerializedName("likes_count")
    private int likesCount;
    private UserBean user;
    private AbilitiesBean abilities;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public AbilitiesBean getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesBean abilities) {
        this.abilities = abilities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public EntitiesContract.Reply map() {
        EntitiesContract.Reply reply = new EntitiesContract.Reply();
        reply.setContent(bodyHtml);
        reply.setCount(likesCount);
        reply.setPhoto(getUser().getUserPhoto());
        reply.setFloor("1");
        reply.setPublishDate(DateUtils.computePastTime(updatedAt));
        reply.setUsername(getUser().getName());
        return reply;
    }
}
