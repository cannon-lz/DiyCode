package com.zly.diycode.http.entities;

import com.google.gson.annotations.SerializedName;
import com.zly.diycode.data.Mapper;
import com.zly.diycode.home.MeModel;

/**
 * Created by zhangly on 2017/3/31.
 */

public class RespMe implements Mapper<MeModel> {


    /**
     * id : 4151
     * login : zhangluya168
     * name : zhangly
     * avatar_url : https://diycode.cc/system/letter_avatars/2/Z/173_214_125/240.png
     * location : null
     * company : null
     * twitter : null
     * website : null
     * bio : null
     * tagline : null
     * github : null
     * created_at : 2017-03-08T15:49:37.005+08:00
     * email : yonggan_shoanian@yeah.net
     * topics_count : 3
     * replies_count : 17
     * following_count : 1
     * followers_count : 0
     * favorites_count : 3
     * level : normal
     * level_name : 会员
     */

    private int id;
    private String login;
    private String name;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private Object location;
    private Object company;
    private Object twitter;
    private Object website;
    private Object bio;
    private Object tagline;
    private Object github;
    @SerializedName("created_at")
    private String createdAt;
    private String email;
    @SerializedName("topics_count")
    private int topicsCount;
    @SerializedName("replies_count")
    private int repliesCount;
    @SerializedName("following_count")
    private int followingCount;
    @SerializedName("followers_count")
    private int followersCount;
    @SerializedName("favorites_count")
    private int favoritesCount;
    private String level;
    @SerializedName("level_name")
    private String levelName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public Object getTagline() {
        return tagline;
    }

    public void setTagline(Object tagline) {
        this.tagline = tagline;
    }

    public Object getGithub() {
        return github;
    }

    public void setGithub(Object github) {
        this.github = github;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTopicsCount() {
        return topicsCount;
    }

    public void setTopicsCount(int topicsCount) {
        this.topicsCount = topicsCount;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(int repliesCount) {
        this.repliesCount = repliesCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public MeModel map() {
        final MeModel meModel = new MeModel();
        meModel.setAvatarUrl(avatarUrl);
        meModel.setEmail(email);
        meModel.setLogin(login);
        meModel.setBio(bio);
        meModel.setCompany(company);
        meModel.setCreatedAt(createdAt);
        meModel.setFavoritesCount(favoritesCount);
        meModel.setFollowersCount(followersCount);
        meModel.setFollowingCount(followingCount);
        meModel.setGithub(github);
        meModel.setId(id);
        meModel.setLevel(level);
        meModel.setLevelName(levelName);
        meModel.setLocation(location);
        meModel.setRepliesCount(repliesCount);
        meModel.setTagline(tagline);
        meModel.setTwitter(twitter);
        meModel.setWebsite(website);
        return meModel;
    }
}
