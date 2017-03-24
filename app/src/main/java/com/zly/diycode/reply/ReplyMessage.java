package com.zly.diycode.reply;

import android.os.Parcel;
import android.os.Parcelable;

import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/24.
 */

public class ReplyMessage implements Parcelable {

    private String floor;
    private EntitiesContract.Topics topics;
    private EntitiesContract.Reply reply;
    private String content;

    public ReplyMessage() {
    }

    public EntitiesContract.Reply getReply() {
        return reply;
    }

    public void setReply(EntitiesContract.Reply reply) {
        this.reply = reply;
    }

    public EntitiesContract.Topics getTopics() {
        return topics;
    }

    public void setTopics(EntitiesContract.Topics topics) {
        this.topics = topics;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    protected ReplyMessage(Parcel in) {
        floor = in.readString();
        topics = in.readParcelable(EntitiesContract.Topics.class.getClassLoader());
        reply = in.readParcelable(EntitiesContract.Reply.class.getClassLoader());
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(floor);
        dest.writeParcelable(topics, flags);
        dest.writeParcelable(reply, flags);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReplyMessage> CREATOR = new Creator<ReplyMessage>() {
        @Override
        public ReplyMessage createFromParcel(Parcel in) {
            return new ReplyMessage(in);
        }

        @Override
        public ReplyMessage[] newArray(int size) {
            return new ReplyMessage[size];
        }
    };
}
