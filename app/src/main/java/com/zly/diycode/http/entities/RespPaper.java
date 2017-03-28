package com.zly.diycode.http.entities;

import com.google.gson.annotations.SerializedName;
import com.zly.diycode.common.DateUtils;
import com.zly.diycode.data.Mapper;

import static com.zly.diycode.topics.EntitiesContract.*;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class RespPaper implements Mapper<Topics> {


    /**
     * id : 685
     * title : Android O 发布了第一个开发者预览版
     * created_at : 2017-03-22T12:31:53.591+08:00
     * updated_at : 2017-03-22T14:21:16.689+08:00
     * replied_at : null
     * replies_count : 0
     * node_name : Android
     * node_id : 1
     * last_reply_user_id : null
     * last_reply_user_login : null
     * user : {"id":48,"login":"jonsnow","name":"囧恩 ","avatar_url":"https://diycode.b0.upaiyun.com/user/large_avatar/48.jpg"}
     * deleted : false
     * excellent : false
     * abilities : {"update":false,"destroy":false}
     */

    private int id;
    private String title;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("replied_at")
    private Object repliedAt;
    @SerializedName("replies_count")
    private int repliesCount;
    @SerializedName("node_name")
    private String nodeName;
    @SerializedName("node_id")
    private int nodeId;
    @SerializedName("last_reply_user_id")
    private String lastReplyUserId;
    @SerializedName("last_reply_user_login")
    private String lastReplyUSerLogin;
    private UserBean user;
    private boolean deleted;
    private boolean excellent;
    private AbilitiesBean abilities;
    private String body;
    @SerializedName("body_html")
    private String bodyHtml;
    private int hits;
    @SerializedName("likes_count")
    private int likeCount;
    @SerializedName("suggested_at")
    private Object suggestedAt;
    private Object followed;
    private Object liked;
    private Object favorited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Object getRepliedAt() {
        return repliedAt;
    }

    public void setRepliedAt(Object repliedAt) {
        this.repliedAt = repliedAt;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(int repliesCount) {
        this.repliesCount = repliesCount;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getLastReplyUserId() {
        return lastReplyUserId;
    }

    public void setLastReplyUserId(String lastReplyUserId) {
        this.lastReplyUserId = lastReplyUserId;
    }

    public String getLastReplyUSerLogin() {
        return lastReplyUSerLogin;
    }

    public void setLastReplyUSerLogin(String lastReplyUSerLogin) {
        this.lastReplyUSerLogin = lastReplyUSerLogin;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Object getSuggestedAt() {
        return suggestedAt;
    }

    public void setSuggestedAt(Object suggestedAt) {
        this.suggestedAt = suggestedAt;
    }

    public Object getFollowed() {
        return followed;
    }

    public void setFollowed(Object followed) {
        this.followed = followed;
    }

    public Object getLiked() {
        return liked;
    }

    public void setLiked(Object liked) {
        this.liked = liked;
    }

    public Object getFavorited() {
        return favorited;
    }

    public void setFavorited(Object favorited) {
        this.favorited = favorited;
    }

    public AbilitiesBean getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesBean abilities) {
        this.abilities = abilities;
    }

    @Override
    public Topics map() {
        Topics topics = new Topics();
        topics.setPublishDate(DateUtils.computePastTime(createdAt));
        topics.setUsername(getUser().getName());
        topics.setTitle(title);
        topics.setSubTitle(null);
        topics.setNote(nodeName);
        topics.setId(String.valueOf(id));
        topics.setUserPhoto(getUser().getUserPhoto());
        topics.setContent(bodyHtml);
        topics.setReplyCount(String.valueOf(repliesCount));
        topics.setLikeCount(String.valueOf(likeCount));
        return topics;
    }
}
