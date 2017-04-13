package com.zly.diycode.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangly on 2017/3/31.
 */

public class MeModel implements Parcelable {

    private int id;
    private String login;
    private String name;
    private String avatarUrl;
    private Object location;
    private Object company;
    private Object twitter;
    private Object website;
    private Object bio;
    private Object tagline;
    private Object github;
    private String createdAt;
    private String email;
    private int topicsCount;
    private int repliesCount;
    private int followingCount;
    private int followersCount;
    private int favoritesCount;
    private String level;
    private String levelName;

    public MeModel() {
    }

    protected MeModel(Parcel in) {
        id = in.readInt();
        login = in.readString();
        name = in.readString();
        avatarUrl = in.readString();
        createdAt = in.readString();
        email = in.readString();
        topicsCount = in.readInt();
        repliesCount = in.readInt();
        followingCount = in.readInt();
        followersCount = in.readInt();
        favoritesCount = in.readInt();
        level = in.readString();
        levelName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeString(createdAt);
        dest.writeString(email);
        dest.writeInt(topicsCount);
        dest.writeInt(repliesCount);
        dest.writeInt(followingCount);
        dest.writeInt(followersCount);
        dest.writeInt(favoritesCount);
        dest.writeString(level);
        dest.writeString(levelName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MeModel> CREATOR = new Creator<MeModel>() {
        @Override
        public MeModel createFromParcel(Parcel in) {
            return new MeModel(in);
        }

        @Override
        public MeModel[] newArray(int size) {
            return new MeModel[size];
        }
    };

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
}
